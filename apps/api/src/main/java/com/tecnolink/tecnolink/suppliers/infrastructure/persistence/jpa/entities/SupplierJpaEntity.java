package com.tecnolink.tecnolink.suppliers.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.UserJpaEntity;
import com.tecnolink.tecnolink.suppliers.domain.model.enums.SupplierStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "suppliers")
public class SupplierJpaEntity extends UserJpaEntity {

    @Column(nullable = false, length = 11, unique = true)
    private String ruc;

    @Column(nullable = false)
    private String name;

    private String region;

    private String district;

    @Column(length = 1000)
    private String description;

    @Column(name = "since_year")
    private int since;

    @Column(nullable = false)
    private double reputation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SupplierStatus status;

    @Column(name = "review_note")
    private String reviewNote;

    @Column(name = "reviewed_on")
    private LocalDate reviewedOn;

    protected SupplierJpaEntity() {
    }

    public SupplierJpaEntity(String id, String firstName, String lastName, String email, String password,
                             String phone, LocalDate registeredOn, boolean active, String ruc, String name,
                             String region, String district, String description, int since, double reputation,
                             SupplierStatus status, String reviewNote, LocalDate reviewedOn) {
        super(id, firstName, lastName, email, password, phone, registeredOn, active);
        this.ruc = ruc;
        this.name = name;
        this.region = region;
        this.district = district;
        this.description = description;
        this.since = since;
        this.reputation = reputation;
        this.status = status;
        this.reviewNote = reviewNote;
        this.reviewedOn = reviewedOn;
    }

    public String getRuc() {
        return ruc;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getDistrict() {
        return district;
    }

    public String getDescription() {
        return description;
    }

    public int getSince() {
        return since;
    }

    public double getReputation() {
        return reputation;
    }

    public SupplierStatus getStatus() {
        return status;
    }

    public String getReviewNote() {
        return reviewNote;
    }

    public LocalDate getReviewedOn() {
        return reviewedOn;
    }
}
