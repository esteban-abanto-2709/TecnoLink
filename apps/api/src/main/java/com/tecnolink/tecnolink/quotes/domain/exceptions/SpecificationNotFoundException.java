package com.tecnolink.tecnolink.quotes.domain.exceptions;

public class SpecificationNotFoundException extends RuntimeException {
    public SpecificationNotFoundException(Long specificationId) {
        super("Specification with ID %s not found".formatted(specificationId));
    }
}
