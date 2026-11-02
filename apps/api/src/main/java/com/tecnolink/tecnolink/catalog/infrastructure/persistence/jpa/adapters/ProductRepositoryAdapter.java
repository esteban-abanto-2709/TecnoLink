package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.ProductJpaEntity;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.mappers.ProductPersistenceMapper;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    public ProductRepositoryAdapter(
            ProductJpaRepository productJpaRepository
    ) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity =
                ProductPersistenceMapper.toEntity(product);

        ProductJpaEntity savedEntity =
                productJpaRepository.save(entity);

        return ProductPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(String id) {
        return productJpaRepository.findById(id)
                .map(ProductPersistenceMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(ProductPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> search(String keyword) {
        return productJpaRepository
                .findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(ProductPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findByCategoryId(String categoryId) {
        return productJpaRepository
                .findByCategoryId(categoryId)
                .stream()
                .map(ProductPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findByPriceBetween(
            double minPrice,
            double maxPrice
    ) {
        return productJpaRepository
                .findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(ProductPersistenceMapper::toDomain)
                .toList();
    }
}