package com.tecnolink.tecnolink.catalog.domain.model.aggregates;

import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;

public class Category {
    private final String id;
    private final String name;
    private final CategoryKind kind;

    public Category(String id, String name, CategoryKind kind) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Category id is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
        if (kind == null) {
            throw new IllegalArgumentException("Category kind is required");
        }
        this.id = id;
        this.name = name;
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryKind getKind() {
        return kind;
    }
}
