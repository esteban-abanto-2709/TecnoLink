package com.tecnolink.tecnolink.loyalty.domain.model.aggregates;

import com.tecnolink.tecnolink.loyalty.domain.model.enums.LoyaltyLevel;

public class LoyaltyAccount {
    private final Long id;
    private final String userId;
    private final int accumulatedPoints;
    private final LoyaltyLevel level;

    public LoyaltyAccount(Long id, String userId, int accumulatedPoints, LoyaltyLevel level) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("Loyalty account user is required");
        }
        if (accumulatedPoints < 0) {
            throw new IllegalArgumentException("Loyalty account points cannot be negative");
        }
        if (level == null) {
            throw new IllegalArgumentException("Loyalty account level is required");
        }
        this.id = id;
        this.userId = userId;
        this.accumulatedPoints = accumulatedPoints;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }

    public LoyaltyLevel getLevel() {
        return level;
    }
}
