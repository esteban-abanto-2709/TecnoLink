package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.repositories.TechServiceRepository;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.mappers.TechServicePersistenceMapper;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories.TechServiceJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TechServiceRepositoryAdapter implements TechServiceRepository {

    private final TechServiceJpaRepository jpaRepository;

    public TechServiceRepositoryAdapter(TechServiceJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<TechService> findAll() {
        return jpaRepository.findAll().stream()
                .map(TechServicePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<TechService> findById(String id) {
        return jpaRepository.findById(id).map(TechServicePersistenceMapper::toDomain);
    }

    @Override
    public TechService save(TechService techService) {
        return TechServicePersistenceMapper.toDomain(jpaRepository.save(TechServicePersistenceMapper.toEntity(techService)));
    }
}
