package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.CategoryJpaEntity;

public final class CategoryPersistenceMapper {

    private CategoryPersistenceMapper() {
    }

    public static CategoryJpaEntity toEntity(Category category) {
        return new CategoryJpaEntity(category.getId(), category.getName(), category.getKind(), category.isActive(),
                category.getDescription());
    }

    public static Category toDomain(CategoryJpaEntity entity) {
        Category category = new Category(entity.getId(), entity.getName(), entity.getKind(), entity.getDescription());
        if (!entity.isActive()) {
            category.deactivate();
        }
        return category;
    }
}
