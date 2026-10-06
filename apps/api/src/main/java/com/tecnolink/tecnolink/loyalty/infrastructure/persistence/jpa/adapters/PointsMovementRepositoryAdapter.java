package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.PointsMovement;
import com.tecnolink.tecnolink.loyalty.domain.repositories.PointsMovementRepository;
import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.mappers.PointsMovementPersistenceMapper;
import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.repositories.PointsMovementJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointsMovementRepositoryAdapter implements PointsMovementRepository {

    private final PointsMovementJpaRepository jpaRepository;

    public PointsMovementRepositoryAdapter(PointsMovementJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<PointsMovement> findByUserId(String userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(PointsMovementPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public PointsMovement save(PointsMovement movement) {
        return PointsMovementPersistenceMapper.toDomain(jpaRepository.save(PointsMovementPersistenceMapper.toEntity(movement)));
    }

    @Override
    public String nextId() {
        return String.format("pts-%03d", jpaRepository.count() + 1);
    }
}
