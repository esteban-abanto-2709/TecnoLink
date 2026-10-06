package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.quotes.domain.model.aggregates.Quote;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.QuoteJpaEntity;

public final class QuotePersistenceMapper {

    private QuotePersistenceMapper() {
    }

    public static QuoteJpaEntity toEntity(Quote quote) {
        return new QuoteJpaEntity(quote.getId(), quote.getRequestId(), quote.getIssueDate(), quote.getValidityDays(),
                quote.getSubtotal(), quote.getIgv(), quote.getTotal(), quote.getConditions());
    }

    public static Quote toDomain(QuoteJpaEntity entity) {
        return new Quote(entity.getId(), entity.getRequestId(), entity.getIssueDate(), entity.getValidityDays(),
                entity.getSubtotal(), entity.getIgv(), entity.getTotal(), entity.getConditions());
    }
}
