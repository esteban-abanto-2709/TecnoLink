package com.tecnolink.tecnolink.suppliers.domain.model.aggregates;

import java.time.Year;

public class Supplier {
    private final String id;
    private final String name;
    private final String ruc;
    private final String phone;
    private final String district;
    private final String description;
    private final int since;
    private final boolean verified;

    public Supplier(String id, String name, String ruc, String phone, String district,
                    String description, int since, boolean verified) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Supplier id is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Supplier name is required");
        }
        if (ruc == null || !ruc.matches("\\d{11}")) {
            throw new IllegalArgumentException("Supplier RUC must have 11 digits");
        }
        if (since > Year.now().getValue()) {
            throw new IllegalArgumentException("Supplier start year cannot be in the future");
        }
        this.id = id;
        this.name = name.trim();
        this.ruc = ruc;
        this.phone = phone;
        this.district = district;
        this.description = description;
        this.since = since;
        this.verified = verified;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRuc() {
        return ruc;
    }

    public String getPhone() {
        return phone;
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

    public boolean isVerified() {
        return verified;
    }
}
