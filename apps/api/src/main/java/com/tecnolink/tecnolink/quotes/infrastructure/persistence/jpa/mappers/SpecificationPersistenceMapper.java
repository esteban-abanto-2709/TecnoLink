package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.quotes.domain.aggregates.Specification;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.SpecificationJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SpecificationPersistenceMapper {
    public Specification toDomain(SpecificationJpaEntity entity) {
        return new Specification(
                entity.getId(),
                entity.getProductId(),
                entity.getName(),
                entity.getValue(),
                entity.getUnit()
        );
    }

    public SpecificationJpaEntity toEntity(Specification specification) {
        SpecificationJpaEntity entity = new SpecificationJpaEntity();
        entity.setId(specification.getId());
        entity.setProductId(specification.getProductId());
        entity.setName(specification.getName());
        entity.setValue(specification.getValue());
        entity.setUnit(specification.getUnit());
        return entity;
    }
}
