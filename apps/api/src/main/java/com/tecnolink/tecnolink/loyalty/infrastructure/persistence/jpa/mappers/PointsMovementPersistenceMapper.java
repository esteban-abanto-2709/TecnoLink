package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.PointsMovement;
import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.entities.PointsMovementJpaEntity;

public final class PointsMovementPersistenceMapper {

    private PointsMovementPersistenceMapper() {
    }

    public static PointsMovementJpaEntity toEntity(PointsMovement movement) {
        return new PointsMovementJpaEntity(movement.getId(), movement.getUserId(), movement.getDate(),
                movement.getDescription(), movement.getPoints(), movement.getType(), movement.getBenefitId());
    }

    public static PointsMovement toDomain(PointsMovementJpaEntity entity) {
        return new PointsMovement(entity.getId(), entity.getUserId(), entity.getDate(), entity.getDescription(),
                entity.getPoints(), entity.getBenefitId());
    }
}
