package com.tecnolink.tecnolink.catalog.domain.model.aggregates;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;
import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;

import java.time.LocalDate;
import java.util.List;

public class TechService extends Listing {
    private ServicePricing pricing;
    private String coverage;
    private final String modality;
    private final String estimatedDuration;

    public TechService(String id, String name, String categoryId, String supplierId, double price,
                       String description, List<String> images, LocalDate publishedOn, ListingStatus status,
                       ServicePricing pricing, String coverage, String modality, String estimatedDuration) {
        super(id, name, categoryId, supplierId, price, description, images, publishedOn, status);
        if (pricing == null) {
            throw new IllegalArgumentException("Service pricing is required");
        }
        this.pricing = pricing;
        this.coverage = coverage == null ? null : coverage.trim();
        this.modality = modality;
        this.estimatedDuration = estimatedDuration;
    }

    public void update(String name, double price, ServicePricing pricing, String description, String coverage) {
        updateListing(name, price, description);
        if (pricing != null) {
            this.pricing = pricing;
        }
        if (coverage != null) {
            this.coverage = coverage.trim();
        }
    }

    public ServicePricing getPricing() {
        return pricing;
    }

    public String getCoverage() {
        return coverage;
    }

    public String getModality() {
        return modality;
    }

    public String getEstimatedDuration() {
        return estimatedDuration;
    }
}
