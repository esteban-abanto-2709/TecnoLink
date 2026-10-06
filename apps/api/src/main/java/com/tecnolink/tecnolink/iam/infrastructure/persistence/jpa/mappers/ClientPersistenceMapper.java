package com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.iam.domain.model.aggregates.Client;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.ClientJpaEntity;

public final class ClientPersistenceMapper {

    private ClientPersistenceMapper() {
    }

    public static ClientJpaEntity toEntity(Client client) {
        return new ClientJpaEntity(client.getId(), client.getFirstName(), client.getLastName(), client.getEmail(),
                client.getPassword(), client.getPhone(), client.getRegisteredOn(), client.isActive(),
                client.getDni(), client.getAddress(), client.getRegion());
    }

    public static Client toDomain(ClientJpaEntity entity) {
        return new Client(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(),
                entity.getPassword(), entity.getPhone(), entity.getRegisteredOn(), entity.isActive(),
                entity.getDni(), entity.getAddress(), entity.getRegion());
    }
}
