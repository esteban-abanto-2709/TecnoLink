package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository
        extends JpaRepository<ProductJpaEntity, String> {

    List<ProductJpaEntity> findByNameContainingIgnoreCase(String keyword);

    List<ProductJpaEntity> findByCategoryId(String categoryId);

    List<ProductJpaEntity> findByPriceBetween(
            double minPrice,
            double maxPrice
    );
}