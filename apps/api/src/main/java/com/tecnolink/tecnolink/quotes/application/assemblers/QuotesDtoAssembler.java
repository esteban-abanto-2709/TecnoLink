package com.tecnolink.tecnolink.quotes.application.assemblers;

import com.tecnolink.tecnolink.quotes.application.dtos.QuoteDto;
import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.application.dtos.SpecificationDto;
import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.domain.aggregates.Specification;
import com.tecnolink.tecnolink.quotes.domain.entities.Quote;

public final class QuotesDtoAssembler {
    private QuotesDtoAssembler() {}

    public static QuoteRequestDto toDto(QuoteRequest q) {
        return new QuoteRequestDto(q.getId(),
                q.getClientId(),
                q.getListingId(),
                q.getRequestedOn(),
                q.getQuantity(),
                q.getRequirement(),
                q.getStatus(),
                q.getQuote() == null ? null : toDto(q.getQuote()));
    }

    public static QuoteDto toDto(Quote q) {
        return new QuoteDto(q.getId(),
                q.getIssueDate(),
                q.getValidityDays(),
                q.validUntil(),
                q.getSubtotal(),
                q.getIgv(),
                q.getTotal(),
                q.getConditions());
    }

    public static SpecificationDto toDto(Specification s) {
        return new SpecificationDto(s.getId(),
                s.getProductId(),
                s.getName(),
                s.getValue(),
                s.getUnit(),
                s.text());
    }
}
