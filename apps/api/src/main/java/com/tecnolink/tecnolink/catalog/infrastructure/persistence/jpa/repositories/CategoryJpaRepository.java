package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, String> {
}
