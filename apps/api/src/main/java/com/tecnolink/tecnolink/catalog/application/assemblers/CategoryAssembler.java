package com.tecnolink.tecnolink.catalog.application.assemblers;

import com.tecnolink.tecnolink.catalog.application.dtos.CategoryDto;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;

public final class CategoryAssembler {

    private CategoryAssembler() {
    }

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getKind(),
                category.isActive(),
                category.getDescription()
        );
    }
}