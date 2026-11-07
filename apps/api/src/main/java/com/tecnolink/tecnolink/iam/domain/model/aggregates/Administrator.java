package com.tecnolink.tecnolink.iam.domain.model.aggregates;

import java.time.LocalDate;

public class Administrator extends User {
    private final String position;

    public Administrator(String id, String firstName, String lastName, String email, String password, String phone,
                         LocalDate registeredOn, boolean active, String position) {
        super(id, firstName, lastName, email, password, phone, registeredOn, active);
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Administrator position is required");
        }
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
