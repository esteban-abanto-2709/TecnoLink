package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories.ProductJpaRepository;
import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.repositories.ClientJpaRepository;
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
    private final ClientJpaRepository clientRepo;
    private final ProductJpaRepository productRepo;

    public ComparisonRepositoryAdapter(
            ComparisonJpaRepository repo,
            ComparisonPersistenceMapper mapper,
            ClientJpaRepository clientRepo,
            ProductJpaRepository productRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.clientRepo = clientRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Comparison save(Comparison comparison) {
        if (!clientRepo.existsById(comparison.getClientId()))
            throw new IllegalArgumentException("Client not found: " + comparison.getClientId());
        comparison.getProductIds().forEach(productId -> {
            if (!productRepo.existsById(productId))
                throw new IllegalArgumentException("Product not found: " + productId);
        });
        return mapper.toDomain(repo.save(mapper.toEntity(comparison)));
    }

    @Override
    public Comparison update(Comparison comparison) {
        return save(comparison);
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
