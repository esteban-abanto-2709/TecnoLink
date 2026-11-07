package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.domain.entities.Quote;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.QuoteJpaEntity;
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
                entity.getStatus(),
                entity.getQuote() == null ? null : toDomainQuote(entity.getQuote())
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
        entity.replaceQuote(quoteRequest.getQuote() == null ? null : toEntityQuote(quoteRequest.getQuote()));
        return entity;
    }

    private Quote toDomainQuote(QuoteJpaEntity e) {
        return new Quote(
                e.getId(),
                e.getIssueDate(),
                e.getValidityDays(),
                e.getSubtotal(),
                e.getIgv(),
                e.getTotal(),
                e.getConditions()
        );
    }

    private QuoteJpaEntity toEntityQuote(Quote d) {
        QuoteJpaEntity e = new QuoteJpaEntity();
        e.setId(d.getId());
        e.setIssueDate(d.getIssueDate());
        e.setValidityDays(d.getValidityDays());
        e.setSubtotal(d.getSubtotal());
        e.setIgv(d.getIgv());
        e.setTotal(d.getTotal());
        e.setConditions(d.getConditions());
        return e;
    }
}
