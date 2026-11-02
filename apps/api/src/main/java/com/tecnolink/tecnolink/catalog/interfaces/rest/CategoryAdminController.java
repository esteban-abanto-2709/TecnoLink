package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.dtos.CategoryDto;
import com.tecnolink.tecnolink.catalog.application.handlers.commands.CategoryCommandHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.GetCategoriesQueryHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.GetCategoryQueryHandler;
import com.tecnolink.tecnolink.catalog.application.queries.GetCategoriesQuery;
import com.tecnolink.tecnolink.catalog.application.queries.GetCategoryQuery;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CategoryStatusRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateCategoryRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.RenameCategoryRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.mappers.CategoryRestMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/categories")
public class CategoryAdminController {

    private final CategoryCommandHandler categoryCommandHandler;
    private final GetCategoriesQueryHandler getCategoriesQueryHandler;
    private final GetCategoryQueryHandler getCategoryQueryHandler;

    public CategoryAdminController(
            CategoryCommandHandler categoryCommandHandler,
            GetCategoriesQueryHandler getCategoriesQueryHandler,
            GetCategoryQueryHandler getCategoryQueryHandler
    ) {
        this.categoryCommandHandler = categoryCommandHandler;
        this.getCategoriesQueryHandler = getCategoriesQueryHandler;
        this.getCategoryQueryHandler = getCategoryQueryHandler;
    }

    @GetMapping
    public List<CategoryDto> getCategories() {

        GetCategoriesQuery query = new GetCategoriesQuery();

        return getCategoriesQueryHandler.handle(query);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable String id) {

        GetCategoryQuery query = new GetCategoryQuery(id);

        return getCategoryQueryHandler.handle(query);
    }

    @PostMapping
    public CategoryDto createCategory(
            @Valid @RequestBody CreateCategoryRequest request
    ) {

        return categoryCommandHandler.handle(
                CategoryRestMapper.toCommand(request)
        );
    }

    @PatchMapping("/{id}")
    public CategoryDto renameCategory(
            @PathVariable String id,
            @Valid @RequestBody RenameCategoryRequest request
    ) {

        return categoryCommandHandler.handle(
                CategoryRestMapper.toCommand(id, request)
        );
    }

    @PatchMapping("/{id}/status")
    public CategoryDto setCategoryStatus(
            @PathVariable String id,
            @Valid @RequestBody CategoryStatusRequest request
    ) {

        return categoryCommandHandler.handle(
                CategoryRestMapper.toCommand(id, request)
        );
    }
}