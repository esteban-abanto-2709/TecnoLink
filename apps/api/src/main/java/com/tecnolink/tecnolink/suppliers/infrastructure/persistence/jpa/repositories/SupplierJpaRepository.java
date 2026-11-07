package com.tecnolink.tecnolink.suppliers.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.suppliers.infrastructure.persistence.jpa.entities.SupplierJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierJpaRepository extends JpaRepository<SupplierJpaEntity, String> {
}
