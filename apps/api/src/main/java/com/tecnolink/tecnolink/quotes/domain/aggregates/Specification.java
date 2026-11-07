package com.tecnolink.tecnolink.quotes.domain.aggregates;

import com.tecnolink.tecnolink.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

public class Specification extends AbstractDomainAggregateRoot<Specification> {
    private Long id;
    private final String productId;
    private final String name;
    private final String value;
    private final String unit;

    public Specification(String productId, String name, String value, String unit) {
        this(null, productId, name, value, unit);
    }

    public Specification(Long id, String productId, String name, String value, String unit) {
        this.id = id;
        this.productId = required(productId, "Product id");
        this.name = required(name, "Specification name");
        this.value = required(value, "Specification value");
        this.unit = unit == null || unit.isBlank() ? null : unit.trim();
    }

    public String text() {
        return unit == null ? value : value + " " + unit;
    }

    private static String required(String value, String name) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(name + " is required");
        return value.trim();
    }

    public Long getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
