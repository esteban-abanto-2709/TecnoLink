package com.tecnolink.tecnolink.iam.domain.repositories;

import com.tecnolink.tecnolink.iam.domain.model.aggregates.Administrator;

import java.util.Optional;

public interface AdministratorRepository {
    Optional<Administrator> findById(String id);
    Administrator save(Administrator administrator);
}
