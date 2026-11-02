package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories.ProductJpaRepository;
import com.tecnolink.tecnolink.quotes.domain.aggregates.Specification;
import com.tecnolink.tecnolink.quotes.domain.repositories.SpecificationRepository;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.mappers.SpecificationPersistenceMapper;
import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories.SpecificationJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SpecificationRepositoryAdapter implements SpecificationRepository {
    private final SpecificationJpaRepository repo;
    private final SpecificationPersistenceMapper mapper;
    private final ProductJpaRepository productRepo;

    public SpecificationRepositoryAdapter(
            SpecificationJpaRepository repo,
            SpecificationPersistenceMapper mapper,
            ProductJpaRepository productRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.productRepo = productRepo;
    }

    @Override
    public Specification save(Specification specification) {
        if (!productRepo.existsById(specification.getProductId()))
            throw new IllegalArgumentException("Product not found: " + specification.getProductId());
        return mapper.toDomain(repo.save(mapper.toEntity(specification)));
    }

    @Override
    public Optional<Specification> findById(Long id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Specification> findByProductId(String productId) {
        return repo.findByProductId(productId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsByProductIdAndName(String productId, String name) {
        return repo.existsByProductIdAndName(productId, name);
    }
}
