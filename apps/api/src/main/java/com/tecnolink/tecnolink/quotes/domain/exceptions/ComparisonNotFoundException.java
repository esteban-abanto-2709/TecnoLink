package com.tecnolink.tecnolink.quotes.domain.exceptions;

public class ComparisonNotFoundException extends RuntimeException {
    public ComparisonNotFoundException(Long comparisonId) {
        super("Comparison with ID %s not found".formatted(comparisonId));
    }
}
