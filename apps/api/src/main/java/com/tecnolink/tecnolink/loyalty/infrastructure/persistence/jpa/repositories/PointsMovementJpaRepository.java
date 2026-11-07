package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.entities.PointsMovementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointsMovementJpaRepository extends JpaRepository<PointsMovementJpaEntity, String> {
    List<PointsMovementJpaEntity> findByUserId(String userId);
}
