package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;
import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tech_services")
public class TechServiceJpaEntity extends ListingJpaEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServicePricing pricing;

    private String coverage;

    private String modality;

    @Column(name = "estimated_duration")
    private String estimatedDuration;

    protected TechServiceJpaEntity() {
    }

    public TechServiceJpaEntity(String id, String name, String categoryId, String supplierId, double price,
                                String description, List<String> images, LocalDate publishedOn,
                                ListingStatus status, ServicePricing pricing, String coverage, String modality,
                                String estimatedDuration) {
        super(id, name, categoryId, supplierId, price, description, images, publishedOn, status);
        this.pricing = pricing;
        this.coverage = coverage;
        this.modality = modality;
        this.estimatedDuration = estimatedDuration;
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
