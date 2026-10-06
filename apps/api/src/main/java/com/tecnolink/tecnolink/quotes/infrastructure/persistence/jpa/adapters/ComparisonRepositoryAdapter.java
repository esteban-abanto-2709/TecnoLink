package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.quotes.domain.model.aggregates.Comparison;
import com.tecnolink.tecnolink.quotes.domain.repositories.ComparisonRepository;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers.ComparisonPersistenceMapper;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories.ComparisonJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ComparisonRepositoryAdapter implements ComparisonRepository {

    private final ComparisonJpaRepository jpaRepository;

    public ComparisonRepositoryAdapter(ComparisonJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Comparison> findById(Long id) {
        return jpaRepository.findById(id).map(ComparisonPersistenceMapper::toDomain);
    }

    @Override
    public Comparison save(Comparison comparison) {
        return ComparisonPersistenceMapper.toDomain(jpaRepository.save(ComparisonPersistenceMapper.toEntity(comparison)));
    }
}
