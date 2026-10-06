package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SpecificationEmbeddable {

    @Column(name = "spec_name", nullable = false)
    private String name;

    @Column(name = "spec_value", nullable = false)
    private String value;

    @Column(name = "spec_unit")
    private String unit;

    protected SpecificationEmbeddable() {
    }

    public SpecificationEmbeddable(String name, String value, String unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
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
