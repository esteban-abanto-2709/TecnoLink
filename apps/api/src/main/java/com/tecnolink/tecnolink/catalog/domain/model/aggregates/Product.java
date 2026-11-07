package com.tecnolink.tecnolink.catalog.domain.model.aggregates;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;
import com.tecnolink.tecnolink.catalog.domain.model.valueobjects.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product extends Listing {
    private String brand;
    private final String model;
    private final int stock;
    private final List<Specification> specs;

    public Product(String id, String name, String brand, String model, String categoryId, String supplierId,
                   double price, String description, List<String> images, LocalDate publishedOn,
                   ListingStatus status, int stock, List<Specification> specs) {
        super(id, name, categoryId, supplierId, price, description, images, publishedOn, status);
        if (stock < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }
        this.brand = brand == null ? null : brand.trim();
        this.model = model;
        this.stock = stock;
        this.specs = new ArrayList<>(specs == null ? List.of() : specs);
    }

    public void update(String name, String brand, double price, String description) {
        updateListing(name, price, description);
        if (brand != null) {
            this.brand = brand.trim();
        }
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getStock() {
        return stock;
    }

    public List<Specification> getSpecs() {
        return Collections.unmodifiableList(specs);
    }
}
