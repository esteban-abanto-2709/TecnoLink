package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities.QuoteRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRequestJpaRepository extends JpaRepository<QuoteRequestJpaEntity, Long> {
}
