package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.entities.LoyaltyAccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoyaltyAccountJpaRepository extends JpaRepository<LoyaltyAccountJpaEntity, Long> {
    Optional<LoyaltyAccountJpaEntity> findByUserId(String userId);
}
