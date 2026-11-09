package com.tecnolink.tecnolink.quotes.domain.exceptions;

import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;

public class QuoteRequestNotFoundException extends NotFoundException {
    public QuoteRequestNotFoundException(Long quoteRequestId) {
        super("Quote request with ID %s not found".formatted(quoteRequestId));
    }
}
