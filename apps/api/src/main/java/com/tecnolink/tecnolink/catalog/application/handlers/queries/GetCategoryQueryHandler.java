package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.dtos.CategoryDto;
import com.tecnolink.tecnolink.catalog.application.queries.GetCategoryQuery;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class GetCategoryQueryHandler {

    private final CategoryRepository categoryRepository;

    public GetCategoryQueryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto handle(GetCategoryQuery query) {

        Category category = categoryRepository.findById(query.categoryId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Category not found")
                );

        return toDto(category);
    }

    private CategoryDto toDto(Category category) {

        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getKind(),
                category.isActive(),
                category.getDescription()
        );
    }
}