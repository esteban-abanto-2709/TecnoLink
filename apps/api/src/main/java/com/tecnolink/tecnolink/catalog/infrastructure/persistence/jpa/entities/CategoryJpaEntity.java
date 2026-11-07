package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryJpaEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryKind kind;

    @Column(nullable = false)
    private boolean active;

    private String description;

    protected CategoryJpaEntity() {
    }

    public CategoryJpaEntity(String id, String name, CategoryKind kind, boolean active, String description) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.active = active;
        this.description = description;
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
