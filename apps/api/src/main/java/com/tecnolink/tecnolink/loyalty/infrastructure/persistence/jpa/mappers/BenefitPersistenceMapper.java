package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.Benefit;
import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.entities.BenefitJpaEntity;

public final class BenefitPersistenceMapper {

    private BenefitPersistenceMapper() {
    }

    public static BenefitJpaEntity toEntity(Benefit benefit) {
        return new BenefitJpaEntity(benefit.getId(), benefit.getName(), benefit.getDescription(), benefit.getCost());
    }

    public static Benefit toDomain(BenefitJpaEntity entity) {
        return new Benefit(entity.getId(), entity.getName(), entity.getDescription(), entity.getCost());
    }
}
