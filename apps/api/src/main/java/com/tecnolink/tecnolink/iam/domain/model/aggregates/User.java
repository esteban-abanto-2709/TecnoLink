package com.tecnolink.tecnolink.iam.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public abstract class User {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String phone;
    private final LocalDate registeredOn;
    private final boolean active;

    protected User(String id, String firstName, String lastName, String email, String password,
                   String phone, LocalDate registeredOn, boolean active) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("User id is required");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("User first name is required");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("User last name is required");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("User email is not valid");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("User password must have at least 8 characters");
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.registeredOn = registeredOn;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public boolean isActive() {
        return active;
    }
}
