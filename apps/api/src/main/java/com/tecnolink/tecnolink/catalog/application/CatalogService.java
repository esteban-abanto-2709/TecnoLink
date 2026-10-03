package com.tecnolink.tecnolink.catalog.application;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;
import com.tecnolink.tecnolink.catalog.domain.model.valueobjects.CatalogItem;
import com.tecnolink.tecnolink.catalog.domain.repositories.CategoryRepository;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import com.tecnolink.tecnolink.catalog.domain.repositories.TechServiceRepository;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {

    private static final int MAX_COMPARE = 4;

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final TechServiceRepository techServiceRepository;

    public CatalogService(CategoryRepository categoryRepository,
                          ProductRepository productRepository,
                          TechServiceRepository techServiceRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.techServiceRepository = techServiceRepository;
    }

    public List<Category> getCategories() {
        List<Category> active = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            if (category.isActive()) active.add(category);
        }
        return active;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category " + id + " not found"));
    }

    public Category createCategory(String name, CategoryKind kind) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
        checkCategoryNameIsFree(name, null);
        String id = slugify(name);
        if (id.isEmpty()) {
            id = "categoria-" + System.currentTimeMillis();
        }
        if (categoryRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Category " + id + " already exists");
        }
        return categoryRepository.save(new Category(id, name, kind));
    }

    public Category renameCategory(String id, String name) {
        Category category = getCategory(id);
        checkCategoryNameIsFree(name, id);
        category.rename(name);
        return categoryRepository.save(category);
    }

    public Category setCategoryActive(String id, boolean active) {
        Category category = getCategory(id);
        if (active) {
            category.activate();
        } else {
            category.deactivate();
        }
        return categoryRepository.save(category);
    }

    private void checkCategoryNameIsFree(String name, String exceptId) {
        String target = normalize(name == null ? "" : name.trim());
        for (Category category : categoryRepository.findAll()) {
            if (!category.getId().equals(exceptId) && normalize(category.getName()).equals(target)) {
                throw new IllegalArgumentException("Category " + name.trim() + " already exists");
            }
        }
    }

    public Product getProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product " + id + " not found"));
    }

    public List<Product> getProductsToCompare(List<String> ids) {
        if (ids.size() > MAX_COMPARE) {
            throw new IllegalArgumentException("You can compare up to " + MAX_COMPARE + " products");
        }
        List<Product> products = new ArrayList<>();
        for (String id : ids) {
            productRepository.findById(id).ifPresent(products::add);
        }
        return products;
    }

    public TechService getService(String id) {
        return techServiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service " + id + " not found"));
    }

    public List<CatalogItem> search(String query, String categoryId, CategoryKind kind,
                                    Double minPrice, Double maxPrice) {
        List<String> terms = words(query);
        List<CatalogItem> results = new ArrayList<>();

        for (Product product : productRepository.findAll()) {
            CatalogItem item = new CatalogItem(product.getId(), CategoryKind.PRODUCT, product.getName(),
                    product.getCategoryId(), product.getSupplierId(), product.getPrice(), null,
                    product.getDescription());
            if (matches(item, product.getBrand(), terms, categoryId, kind, minPrice, maxPrice)) {
                results.add(item);
            }
        }

        for (TechService service : techServiceRepository.findAll()) {
            CatalogItem item = new CatalogItem(service.getId(), CategoryKind.SERVICE, service.getName(),
                    service.getCategoryId(), service.getSupplierId(), service.getPrice(), service.getPricing(),
                    service.getDescription());
            if (matches(item, "", terms, categoryId, kind, minPrice, maxPrice)) {
                results.add(item);
            }
        }

        return results;
    }

    private boolean matches(CatalogItem item, String brand, List<String> terms, String categoryId,
                            CategoryKind kind, Double minPrice, Double maxPrice) {
        if (categoryId != null && !item.categoryId().equals(categoryId)) return false;
        if (kind != null && item.kind() != kind) return false;
        if (minPrice != null && item.price() < minPrice) return false;
        if (maxPrice != null && item.price() > maxPrice) return false;
        if (terms.isEmpty()) return true;

        String categoryName = categoryRepository.findById(item.categoryId())
                .map(Category::getName)
                .orElse("");
        List<String> found = words(item.name() + " " + item.description() + " " + brand + " " + categoryName);

        for (String term : terms) {
            boolean termFound = false;
            for (String word : found) {
                if (word.startsWith(term)) {
                    termFound = true;
                    break;
                }
            }
            if (!termFound) return false;
        }
        return true;
    }

    private List<String> words(String text) {
        if (text == null) return List.of();
        List<String> words = new ArrayList<>();
        for (String word : normalize(text).split("[^a-z0-9]+")) {
            if (!word.isEmpty()) words.add(word);
        }
        return words;
    }

    private String slugify(String text) {
        return normalize(text.trim())
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "");
    }

    private String normalize(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }
}
