package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.quotes.domain.aggregates.Comparison;
import com.tecnolink.tecnolink.quotes.domain.repositories.ComparisonRepository;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers.ComparisonPersistenceMapper;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories.ComparisonJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ComparisonRepositoryAdapter implements ComparisonRepository {
    private final ComparisonJpaRepository repo;
    private final ComparisonPersistenceMapper mapper;

    public ComparisonRepositoryAdapter(ComparisonJpaRepository repo, ComparisonPersistenceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Comparison save(Comparison comparison) {
        return mapper.toDomain(repo.save(mapper.toEntity(comparison)));
    }

    @Override
    public Optional<Comparison> findById(Long id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Comparison> findByClientId(String clientId) {
        return repo.findByClientId(clientId).stream().map(mapper::toDomain).toList();
    }
}
