package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.quotes.domain.model.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.QuoteRequestJpaEntity;

public final class QuoteRequestPersistenceMapper {

    private QuoteRequestPersistenceMapper() {
    }

    public static QuoteRequestJpaEntity toEntity(QuoteRequest request) {
        return new QuoteRequestJpaEntity(request.getId(), request.getClientId(), request.getListingId(),
                request.getDate(), request.getQuantity(), request.getRequirement(), request.getStatus());
    }

    public static QuoteRequest toDomain(QuoteRequestJpaEntity entity) {
        return new QuoteRequest(entity.getId(), entity.getClientId(), entity.getListingId(), entity.getDate(),
                entity.getQuantity(), entity.getRequirement(), entity.getStatus());
    }
}
