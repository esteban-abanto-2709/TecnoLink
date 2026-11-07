package com.tecnolink.tecnolink.iam.domain.repositories;

import com.tecnolink.tecnolink.iam.domain.model.aggregates.Client;

import java.util.Optional;

public interface ClientRepository {
    Optional<Client> findById(String id);
    Client save(Client client);
}
