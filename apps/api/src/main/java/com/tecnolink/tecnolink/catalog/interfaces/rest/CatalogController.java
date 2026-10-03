package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.CatalogService;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;
import com.tecnolink.tecnolink.catalog.domain.model.valueobjects.CatalogItem;
import com.tecnolink.tecnolink.suppliers.application.SupplierService;
import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CatalogController {

    private final CatalogService catalogService;
    private final SupplierService supplierService;

    public CatalogController(CatalogService catalogService, SupplierService supplierService) {
        this.catalogService = catalogService;
        this.supplierService = supplierService;
    }

    @GetMapping("/categories")
    public List<Category> categories() {
        return catalogService.getCategories();
    }

    @GetMapping("/products/{id}")
    public Product product(@PathVariable String id) {
        return catalogService.getProduct(id);
    }

    @GetMapping("/products")
    public List<Product> productsToCompare(@RequestParam List<String> ids) {
        return catalogService.getProductsToCompare(ids);
    }

    @GetMapping("/services/{id}")
    public TechService service(@PathVariable String id) {
        return catalogService.getService(id);
    }

    @GetMapping("/catalog")
    public List<CatalogItem> search(@RequestParam(required = false) String query,
                                    @RequestParam(required = false) String categoryId,
                                    @RequestParam(required = false) CategoryKind kind,
                                    @RequestParam(required = false) Double minPrice,
                                    @RequestParam(required = false) Double maxPrice) {
        Map<String, String> supplierNames = new HashMap<>();
        for (Supplier supplier : supplierService.getSuppliers()) {
            supplierNames.put(supplier.getId(), supplier.getName());
        }
        return catalogService.search(query, categoryId, kind, minPrice, maxPrice, supplierNames);
    }
}
