package com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.iam.domain.model.aggregates.Administrator;
import com.tecnolink.tecnolink.iam.domain.repositories.AdministratorRepository;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.mappers.AdministratorPersistenceMapper;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.repositories.AdministratorJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdministratorRepositoryAdapter implements AdministratorRepository {

    private final AdministratorJpaRepository jpaRepository;

    public AdministratorRepositoryAdapter(AdministratorJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Administrator> findById(String id) {
        return jpaRepository.findById(id).map(AdministratorPersistenceMapper::toDomain);
    }

    @Override
    public Administrator save(Administrator administrator) {
        return AdministratorPersistenceMapper.toDomain(
                jpaRepository.save(AdministratorPersistenceMapper.toEntity(administrator)));
    }
}
