package com.tecnolink.tecnolink.loyalty.domain.repositories;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.LoyaltyAccount;

import java.util.Optional;

public interface LoyaltyAccountRepository {
    Optional<LoyaltyAccount> findByUserId(String userId);
    LoyaltyAccount save(LoyaltyAccount account);
}
