package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.quotes.domain.model.aggregates.Comparison;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.ComparisonJpaEntity;

public final class ComparisonPersistenceMapper {

    private ComparisonPersistenceMapper() {
    }

    public static ComparisonJpaEntity toEntity(Comparison comparison) {
        return new ComparisonJpaEntity(comparison.getId(), comparison.getClientId(), comparison.getDate(),
                comparison.getProductIds());
    }

    public static Comparison toDomain(ComparisonJpaEntity entity) {
        return new Comparison(entity.getId(), entity.getClientId(), entity.getDate(), entity.getProductIds());
    }
}
