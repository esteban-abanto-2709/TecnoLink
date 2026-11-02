package com.tecnolink.tecnolink.catalog.application.commands;

import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;

public record CreateCategoryCommand(
        String name,
        CategoryKind kind
) {
}