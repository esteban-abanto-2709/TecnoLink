package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.quotes.domain.aggregates.Comparison;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.ComparisonJpaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ComparisonPersistenceMapper {
    public Comparison toDomain(ComparisonJpaEntity entity) {
        return new Comparison(
                entity.getId(),
                entity.getClientId(),
                entity.getCreatedOn(),
                entity.getProductIds()
        );
    }

    public ComparisonJpaEntity toEntity(Comparison comparison) {
        ComparisonJpaEntity entity = new ComparisonJpaEntity();
        entity.setId(comparison.getId());
        entity.setClientId(comparison.getClientId());
        entity.setCreatedOn(comparison.getCreatedOn());
        entity.setProductIds(new ArrayList<>(comparison.getProductIds()));
        return entity;
    }
}
