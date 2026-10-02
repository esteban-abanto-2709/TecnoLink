package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.CatalogService;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/categories")
    public List<Category> categories() {
        return catalogService.getCategories();
    }

    @GetMapping("/products/{id}")
    public Product product(@PathVariable String id) {
        return catalogService.getProduct(id);
    }

    @GetMapping("/services/{id}")
    public TechService service(@PathVariable String id) {
        return catalogService.getService(id);
    }
}
