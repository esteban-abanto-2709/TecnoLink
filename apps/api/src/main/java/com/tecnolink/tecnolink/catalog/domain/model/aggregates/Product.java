package com.tecnolink.tecnolink.catalog.domain.model.aggregates;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Product {
    private final String id;
    private String name;
    private String brand;
    private final String categoryId;
    private final String supplierId;
    private double price;
    private String description;
    private final Map<String, String> specs;

    public Product(String id, String name, String brand, String categoryId, String supplierId,
                   double price, String description, Map<String, String> specs) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product id is required");
        }
        if (categoryId == null || categoryId.isBlank()) {
            throw new IllegalArgumentException("Product category is required");
        }
        if (supplierId == null || supplierId.isBlank()) {
            throw new IllegalArgumentException("Product supplier is required");
        }
        this.id = id;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.specs = new LinkedHashMap<>(specs == null ? Map.of() : specs);
        update(name, brand, price, description);
    }

    public void update(String name, String brand, double price, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        this.name = name.trim();
        if (brand != null) {
            this.brand = brand.trim();
        }
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
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

    public String getDescription() {
        return description;
    }

    public Map<String, String> getSpecs() {
        return Collections.unmodifiableMap(specs);
    }
}
