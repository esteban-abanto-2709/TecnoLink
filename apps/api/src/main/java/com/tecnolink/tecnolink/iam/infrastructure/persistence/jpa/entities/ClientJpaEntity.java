package com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class ClientJpaEntity extends UserJpaEntity {

    @Column(nullable = false, length = 8, unique = true)
    private String dni;

    private String address;

    private String region;

    protected ClientJpaEntity() {
    }

    public ClientJpaEntity(String id, String firstName, String lastName, String email, String password, String phone,
                           LocalDate registeredOn, boolean active, String dni, String address, String region) {
        super(id, firstName, lastName, email, password, phone, registeredOn, active);
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
