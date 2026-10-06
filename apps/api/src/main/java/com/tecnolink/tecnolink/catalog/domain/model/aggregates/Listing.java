package com.tecnolink.tecnolink.catalog.domain.model.aggregates;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Listing {
    private final String id;
    private String name;
    private final String categoryId;
    private final String supplierId;
    private double price;
    private String description;
    private final List<String> images;
    private final LocalDate publishedOn;
    private final ListingStatus status;

    protected Listing(String id, String name, String categoryId, String supplierId, double price,
                      String description, List<String> images, LocalDate publishedOn, ListingStatus status) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Listing id is required");
        }
        if (categoryId == null || categoryId.isBlank()) {
            throw new IllegalArgumentException("Listing category is required");
        }
        if (supplierId == null || supplierId.isBlank()) {
            throw new IllegalArgumentException("Listing supplier is required");
        }
        if (status == null) {
            throw new IllegalArgumentException("Listing status is required");
        }
        this.id = id;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.images = new ArrayList<>(images == null ? List.of() : images);
        this.publishedOn = publishedOn;
        this.status = status;
        updateListing(name, price, description);
    }

    protected void updateListing(String name, double price, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Listing name is required");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Listing price cannot be negative");
        }
        this.name = name.trim();
        this.price = price;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return Collections.unmodifiableList(images);
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public ListingStatus getStatus() {
        return status;
    }
}
