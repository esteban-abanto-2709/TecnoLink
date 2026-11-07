package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.TechServiceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechServiceJpaRepository extends JpaRepository<TechServiceJpaEntity, String> {
}
