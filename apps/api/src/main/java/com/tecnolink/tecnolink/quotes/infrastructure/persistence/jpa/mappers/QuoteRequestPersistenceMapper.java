package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.QuoteRequestJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class QuoteRequestPersistenceMapper {
    public QuoteRequest toDomain(QuoteRequestJpaEntity entity) {
        return new QuoteRequest(
                entity.getId(),
                entity.getClientId(),
                entity.getListingId(),
                entity.getRequestedOn(),
                entity.getQuantity(),
                entity.getRequirement(),
                entity.getStatus()
        );
    }

    public QuoteRequestJpaEntity toEntity(QuoteRequest quoteRequest) {
        QuoteRequestJpaEntity entity = new QuoteRequestJpaEntity();
        entity.setId(quoteRequest.getId());
        entity.setClientId(quoteRequest.getClientId());
        entity.setListingId(quoteRequest.getListingId());
        entity.setRequestedOn(quoteRequest.getRequestedOn());
        entity.setQuantity(quoteRequest.getQuantity());
        entity.setRequirement(quoteRequest.getRequirement());
        entity.setStatus(quoteRequest.getStatus());
        return entity;
    }
}
