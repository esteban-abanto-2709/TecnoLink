package com.tecnolink.tecnolink.catalog.domain.model.aggregates;

import com.tecnolink.tecnolink.catalog.domain.events.CategoryCreatedEvent;
import com.tecnolink.tecnolink.catalog.domain.exceptions.DomainException;
import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;
import com.tecnolink.tecnolink.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

public class Category extends AbstractDomainAggregateRoot<Category> {

    private final String id;
    private String name;
    private final CategoryKind kind;
    private boolean active;
    private final String description;

    public Category(
            String id,
            String name,
            CategoryKind kind,
            String description
    ) {
        if (id == null || id.isBlank()) {
            throw new DomainException("Category id is required");
        }

        if (kind == null) {
            throw new DomainException("Category kind is required");
        }

        this.id = id;
        this.kind = kind;
        this.active = true;
        this.description = description;

        rename(name);

        registerDomainEvent(
                new CategoryCreatedEvent(
                        this.id,
                        this.name
                )
        );
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new DomainException("Category name is required");
        }

        this.name = newName.trim();
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
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

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }
}