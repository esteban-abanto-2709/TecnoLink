package com.tecnolink.tecnolink.catalog.domain.model.aggregates;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;

public class TechService {
    private final String id;
    private String name;
    private final String categoryId;
    private final String supplierId;
    private double price;
    private ServicePricing pricing;
    private String description;
    private String coverage;

    public TechService(String id, String name, String categoryId, String supplierId, double price,
                       ServicePricing pricing, String description, String coverage) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Service id is required");
        }
        if (categoryId == null || categoryId.isBlank()) {
            throw new IllegalArgumentException("Service category is required");
        }
        if (supplierId == null || supplierId.isBlank()) {
            throw new IllegalArgumentException("Service supplier is required");
        }
        if (pricing == null) {
            throw new IllegalArgumentException("Service pricing is required");
        }
        this.id = id;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.pricing = pricing;
        update(name, price, pricing, description, coverage);
    }

    public void update(String name, double price, ServicePricing pricing, String description, String coverage) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Service name is required");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Service price cannot be negative");
        }
        this.name = name.trim();
        this.price = price;
        if (pricing != null) {
            this.pricing = pricing;
        }
        this.description = description;
        if (coverage != null) {
            this.coverage = coverage.trim();
        }
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
