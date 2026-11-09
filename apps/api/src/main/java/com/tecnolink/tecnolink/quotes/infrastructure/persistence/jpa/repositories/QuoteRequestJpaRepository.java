package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.QuoteRequestJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRequestJpaRepository extends JpaRepository<QuoteRequestJpaEntity, Long> {
    @EntityGraph(attributePaths = "quote")
    List<QuoteRequestJpaEntity> findByClientId(String clientId);

    @EntityGraph(attributePaths = "quote")
    List<QuoteRequestJpaEntity> findByListing_SupplierId(String supplierId);
}
