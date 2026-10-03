package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.CatalogService;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CategoryStatusRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateCategoryRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.RenameCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/categories")
public class CategoryAdminController {

    private final CatalogService catalogService;

    public CategoryAdminController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<Category> all() {
        return catalogService.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody CreateCategoryRequest request) {
        Category created = catalogService.createCategory(request.name(), request.kind());
        return ResponseEntity.status(201).body(created);
    }

    @PatchMapping("/{id}")
    public Category rename(@PathVariable String id, @Valid @RequestBody RenameCategoryRequest request) {
        return catalogService.renameCategory(id, request.name());
    }

    @PatchMapping("/{id}/status")
    public Category changeStatus(@PathVariable String id, @Valid @RequestBody CategoryStatusRequest request) {
        return catalogService.setCategoryActive(id, request.active());
    }
}
