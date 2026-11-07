package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.ComparisonJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComparisonJpaRepository extends JpaRepository<ComparisonJpaEntity, Long> {
    List<ComparisonJpaEntity> findByClientId(String clientId);
}
