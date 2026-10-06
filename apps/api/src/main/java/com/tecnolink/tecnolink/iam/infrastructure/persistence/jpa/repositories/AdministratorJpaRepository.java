package com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.AdministratorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorJpaRepository extends JpaRepository<AdministratorJpaEntity, String> {
}
