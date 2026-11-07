package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.TechServiceJpaEntity;

public final class TechServicePersistenceMapper {

    private TechServicePersistenceMapper() {
    }

    public static TechServiceJpaEntity toEntity(TechService service) {
        return new TechServiceJpaEntity(service.getId(), service.getName(), service.getCategoryId(),
                service.getSupplierId(), service.getPrice(), service.getDescription(), service.getImages(),
                service.getPublishedOn(), service.getStatus(), service.getPricing(), service.getCoverage(),
                service.getModality(), service.getEstimatedDuration());
    }

    public static TechService toDomain(TechServiceJpaEntity entity) {
        return new TechService(entity.getId(), entity.getName(), entity.getCategoryId(), entity.getSupplierId(),
                entity.getPrice(), entity.getDescription(), entity.getImages(), entity.getPublishedOn(),
                entity.getStatus(), entity.getPricing(), entity.getCoverage(), entity.getModality(),
                entity.getEstimatedDuration());
    }
}
