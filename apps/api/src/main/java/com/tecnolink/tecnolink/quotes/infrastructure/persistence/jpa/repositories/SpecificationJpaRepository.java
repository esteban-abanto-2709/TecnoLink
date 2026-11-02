package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.SpecificationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificationJpaRepository extends JpaRepository<SpecificationJpaEntity, Long> {
    List<SpecificationJpaEntity> findByProductId(String productId);

    boolean existsByProductIdAndName(String productId, String name);
}
