package com.tecnolink.tecnolink.iam.domain.model.aggregates;

import java.time.LocalDate;

public class Client extends User {
    private final String dni;
    private final String address;
    private final String region;

    public Client(String id, String firstName, String lastName, String email, String password, String phone,
                  LocalDate registeredOn, boolean active, String dni, String address, String region) {
        super(id, firstName, lastName, email, password, phone, registeredOn, active);
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("Client DNI must have 8 digits");
        }
        this.dni = dni;
        this.address = address;
        this.region = region;
    }

    public String getDni() {
        return dni;
    }

    public String getAddress() {
        return address;
    }

    public String getRegion() {
        return region;
    }
}
