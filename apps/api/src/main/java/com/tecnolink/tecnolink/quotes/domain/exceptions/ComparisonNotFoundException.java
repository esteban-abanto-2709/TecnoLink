package com.tecnolink.tecnolink.quotes.domain.exceptions;

import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;

public class ComparisonNotFoundException extends NotFoundException {
    public ComparisonNotFoundException(Long comparisonId) {
        super("Comparison with ID %s not found".formatted(comparisonId));
    }
}
