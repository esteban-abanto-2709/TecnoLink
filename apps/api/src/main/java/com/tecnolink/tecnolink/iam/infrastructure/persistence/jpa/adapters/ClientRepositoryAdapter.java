package com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.iam.domain.model.aggregates.Client;
import com.tecnolink.tecnolink.iam.domain.repositories.ClientRepository;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.mappers.ClientPersistenceMapper;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.repositories.ClientJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientRepositoryAdapter implements ClientRepository {

    private final ClientJpaRepository jpaRepository;

    public ClientRepositoryAdapter(ClientJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Client> findById(String id) {
        return jpaRepository.findById(id).map(ClientPersistenceMapper::toDomain);
    }

    @Override
    public Client save(Client client) {
        return ClientPersistenceMapper.toDomain(jpaRepository.save(ClientPersistenceMapper.toEntity(client)));
    }
}
