package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.LoyaltyAccount;
import com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.entities.LoyaltyAccountJpaEntity;

public final class LoyaltyAccountPersistenceMapper {

    private LoyaltyAccountPersistenceMapper() {
    }

    public static LoyaltyAccountJpaEntity toEntity(LoyaltyAccount account) {
        return new LoyaltyAccountJpaEntity(account.getId(), account.getUserId(), account.getAccumulatedPoints(),
                account.getLevel());
    }

    public static LoyaltyAccount toDomain(LoyaltyAccountJpaEntity entity) {
        return new LoyaltyAccount(entity.getId(), entity.getUserId(), entity.getAccumulatedPoints(),
                entity.getLevel());
    }
}
