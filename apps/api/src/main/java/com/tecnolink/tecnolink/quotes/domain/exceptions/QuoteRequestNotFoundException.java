package com.tecnolink.tecnolink.quotes.domain.exceptions;

public class QuoteRequestNotFoundException extends RuntimeException {
    public QuoteRequestNotFoundException(Long quoteRequestId) {
        super("Quote request with ID %s not found".formatted(quoteRequestId));
    }
}
