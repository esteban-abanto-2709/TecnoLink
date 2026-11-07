package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.entities.BenefitJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitJpaRepository extends JpaRepository<BenefitJpaEntity, String> {
}
