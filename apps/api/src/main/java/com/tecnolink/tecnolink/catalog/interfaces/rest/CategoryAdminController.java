package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.CatalogService;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CategoryStatusRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateCategoryRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.RenameCategoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Category Administration",
        description = "Operaciones administrativas para la gestión de categorías"
)
public class CategoryAdminController {

    private final CatalogService catalogService;

    public CategoryAdminController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    @Operation(
            summary = "Listar categorías",
            description = "Obtiene todas las categorías registradas en el catálogo."
    )
    public List<Category> all() {
        return catalogService.getAllCategories();
    }

    @PostMapping
    @Operation(
            summary = "Crear categoría",
            description = "Registra una nueva categoría en el catálogo."
    )
    public ResponseEntity<Category> create(
            @Valid @RequestBody CreateCategoryRequest request
    ) {
        Category created = catalogService.createCategory(
                request.name(),
                request.kind()
        );

        return ResponseEntity
                .status(201)
                .body(created);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Renombrar categoría",
            description = "Actualiza el nombre de una categoría existente."
    )
    public Category rename(
            @Parameter(description = "Identificador de la categoría")
            @PathVariable String id,

            @Valid @RequestBody RenameCategoryRequest request
    ) {
        return catalogService.renameCategory(
                id,
                request.name()
        );
    }

    @PatchMapping("/{id}/status")
    @Operation(
            summary = "Activar o desactivar categoría",
            description = "Permite cambiar el estado activo de una categoría."
    )
    public Category changeStatus(
            @Parameter(description = "Identificador de la categoría")
            @PathVariable String id,

            @Valid @RequestBody CategoryStatusRequest request
    ) {
        return catalogService.setCategoryActive(
                id,
                request.active()
        );
    }
}