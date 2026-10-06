package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.mappers.ProductPersistenceMapper;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories.ProductJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository jpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll().stream()
                .map(ProductPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(String id) {
        return jpaRepository.findById(id).map(ProductPersistenceMapper::toDomain);
    }

    @Override
    public Product save(Product product) {
        return ProductPersistenceMapper.toDomain(jpaRepository.save(ProductPersistenceMapper.toEntity(product)));
    }
}
