package com.tecnolink.tecnolink.suppliers.domain.model.aggregates;

import com.tecnolink.tecnolink.iam.domain.model.aggregates.User;
import com.tecnolink.tecnolink.suppliers.domain.model.enums.SupplierStatus;

import java.time.LocalDate;
import java.time.Year;

public class Supplier extends User {
    private final String ruc;
    private final String name;
    private final String region;
    private final String district;
    private final String description;
    private final int since;
    private final double reputation;
    private SupplierStatus status;
    private String reviewNote;
    private LocalDate reviewedOn;

    public Supplier(String id, String firstName, String lastName, String email, String password, String phone,
                    LocalDate registeredOn, boolean active, String ruc, String name, String region,
                    String district, String description, int since, double reputation, SupplierStatus status,
                    String reviewNote, LocalDate reviewedOn) {
        super(id, firstName, lastName, email, password, phone, registeredOn, active);
        if (ruc == null || !ruc.matches("\\d{11}")) {
            throw new IllegalArgumentException("Supplier RUC must have 11 digits");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Supplier name is required");
        }
        if (since > Year.now().getValue()) {
            throw new IllegalArgumentException("Supplier start year cannot be in the future");
        }
        if (reputation < 0 || reputation > 5) {
            throw new IllegalArgumentException("Supplier reputation must be between 0 and 5");
        }
        if (status == null) {
            throw new IllegalArgumentException("Supplier status is required");
        }
        this.ruc = ruc;
        this.name = name.trim();
        this.region = region;
        this.district = district;
        this.description = description;
        this.since = since;
        this.reputation = reputation;
        this.status = status;
        this.reviewNote = reviewNote == null ? "" : reviewNote;
        this.reviewedOn = reviewedOn;
    }

    public void review(SupplierStatus newStatus, String note) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Supplier review status is required");
        }
        this.status = newStatus;
        this.reviewNote = note == null ? "" : note.trim();
        this.reviewedOn = LocalDate.now();
    }

    public boolean isSuspended() {
        return status == SupplierStatus.SUSPENDED;
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

    public boolean isVerified() {
        return status == SupplierStatus.VERIFIED;
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
