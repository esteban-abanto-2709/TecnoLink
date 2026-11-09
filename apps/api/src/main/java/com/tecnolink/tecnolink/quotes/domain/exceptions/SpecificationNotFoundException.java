package com.tecnolink.tecnolink.quotes.domain.exceptions;

import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;

public class SpecificationNotFoundException extends NotFoundException {
    public SpecificationNotFoundException(Long specificationId) {
        super("Specification with ID %s not found".formatted(specificationId));
    }
}
