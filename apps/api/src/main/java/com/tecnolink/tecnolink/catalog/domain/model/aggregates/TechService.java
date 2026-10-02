package com.tecnolink.tecnolink.catalog.domain.model.aggregates;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;

public class TechService {
    private final String id;
    private final String name;
    private final String categoryId;
    private final String supplierId;
    private final double price;
    private final ServicePricing pricing;
    private final String description;
    private final String coverage;

    public TechService(String id, String name, String categoryId, String supplierId, double price,
                       ServicePricing pricing, String description, String coverage) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Service id is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Service name is required");
        }
        if (categoryId == null || categoryId.isBlank()) {
            throw new IllegalArgumentException("Service category is required");
        }
        if (supplierId == null || supplierId.isBlank()) {
            throw new IllegalArgumentException("Service supplier is required");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Service price cannot be negative");
        }
        if (pricing == null) {
            throw new IllegalArgumentException("Service pricing is required");
        }
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.price = price;
        this.pricing = pricing;
        this.description = description;
        this.coverage = coverage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public double getPrice() {
        return price;
    }

    public ServicePricing getPricing() {
        return pricing;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverage() {
        return coverage;
    }
}
