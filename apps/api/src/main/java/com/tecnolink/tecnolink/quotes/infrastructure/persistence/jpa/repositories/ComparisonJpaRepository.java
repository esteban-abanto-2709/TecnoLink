package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.ComparisonJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComparisonJpaRepository extends JpaRepository<ComparisonJpaEntity, Long> {
}
