package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.LoyaltyAccount;
import com.tecnolink.tecnolink.loyalty.domain.repositories.LoyaltyAccountRepository;
import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.mappers.LoyaltyAccountPersistenceMapper;
import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.repositories.LoyaltyAccountJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoyaltyAccountRepositoryAdapter implements LoyaltyAccountRepository {

    private final LoyaltyAccountJpaRepository jpaRepository;

    public LoyaltyAccountRepositoryAdapter(LoyaltyAccountJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<LoyaltyAccount> findByUserId(String userId) {
        return jpaRepository.findByUserId(userId).map(LoyaltyAccountPersistenceMapper::toDomain);
    }

    @Override
    public LoyaltyAccount save(LoyaltyAccount account) {
        return LoyaltyAccountPersistenceMapper.toDomain(
                jpaRepository.save(LoyaltyAccountPersistenceMapper.toEntity(account)));
    }
}
