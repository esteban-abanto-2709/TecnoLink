package com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "administrators")
public class AdministratorJpaEntity extends UserJpaEntity {

    @Column(nullable = false)
    private String position;

    protected AdministratorJpaEntity() {
    }

    public AdministratorJpaEntity(String id, String firstName, String lastName, String email, String password,
                                  String phone, LocalDate registeredOn, boolean active, String position) {
        super(id, firstName, lastName, email, password, phone, registeredOn, active);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
