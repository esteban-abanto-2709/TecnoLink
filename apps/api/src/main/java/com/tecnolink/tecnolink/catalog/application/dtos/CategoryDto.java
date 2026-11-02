package com.tecnolink.tecnolink.catalog.application.dtos;

import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;

public record CategoryDto(
        String id,
        String name,
        CategoryKind kind,
        boolean active,
        String description
) {
}