package com.tecnolink.tecnolink.quotes.application.assemblers;

import com.tecnolink.tecnolink.quotes.application.dtos.QuoteRequestDto;
import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;

public final class QuotesDtoAssembler {
    private QuotesDtoAssembler() {}

    public static QuoteRequestDto toDto(QuoteRequest q) {
        return new QuoteRequestDto(q.getId(),
                q.getClientId(),
                q.getListingId(),
                q.getRequestedOn(),
                q.getQuantity(),
                q.getRequirement(),
                q.getStatus());
    }
}
