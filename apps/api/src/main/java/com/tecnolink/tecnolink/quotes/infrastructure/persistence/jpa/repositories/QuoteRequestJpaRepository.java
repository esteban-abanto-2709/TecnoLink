package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.QuoteRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRequestJpaRepository extends JpaRepository<QuoteRequestJpaEntity, Long> {
    List<QuoteRequestJpaEntity> findByClientId(String clientId);

    List<QuoteRequestJpaEntity> findByListing_SupplierId(String supplierId);
}
