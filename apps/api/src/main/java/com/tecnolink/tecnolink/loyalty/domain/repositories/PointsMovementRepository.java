package com.tecnolink.tecnolink.loyalty.domain.repositories;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.PointsMovement;

import java.util.List;

public interface PointsMovementRepository {
    List<PointsMovement> findByUserId(String userId);
    PointsMovement save(PointsMovement movement);
    String nextId();
}
