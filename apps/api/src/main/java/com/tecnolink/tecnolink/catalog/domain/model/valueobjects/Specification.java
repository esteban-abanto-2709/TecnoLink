package com.tecnolink.tecnolink.catalog.domain.model.valueobjects;

public record Specification(String name, String value, String unit) {
    public Specification {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Specification name is required");
        }
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Specification value is required");
        }
    }
}
