package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.Benefit;
import com.tecnolink.tecnolink.loyalty.domain.repositories.BenefitRepository;
import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.mappers.BenefitPersistenceMapper;
import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.repositories.BenefitJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BenefitRepositoryAdapter implements BenefitRepository {

    private final BenefitJpaRepository jpaRepository;

    public BenefitRepositoryAdapter(BenefitJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Benefit> findAll() {
        return jpaRepository.findAll().stream()
                .map(BenefitPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Benefit> findById(String id) {
        return jpaRepository.findById(id).map(BenefitPersistenceMapper::toDomain);
    }
}
