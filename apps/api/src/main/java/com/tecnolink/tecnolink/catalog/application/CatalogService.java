package com.tecnolink.tecnolink.catalog.application;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.repositories.CategoryRepository;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import com.tecnolink.tecnolink.catalog.domain.repositories.TechServiceRepository;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

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
        return categoryRepository.findAll();
    }

    public Product getProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product " + id + " not found"));
    }

    public TechService getService(String id) {
        return techServiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service " + id + " not found"));
    }
}
