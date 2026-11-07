package com.tecnolink.tecnolink.loyalty.domain.model.aggregates;

import com.tecnolink.tecnolink.loyalty.domain.model.enums.MovementType;

import java.time.LocalDate;

public class PointsMovement {
    private final String id;
    private final String userId;
    private final LocalDate date;
    private final String description;
    private final int points;
    private final String benefitId;
    private final MovementType type;

    public PointsMovement(String id, String userId, LocalDate date, String description, int points, String benefitId) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Movement id is required");
        }
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("Movement user is required");
        }
        if (date == null) {
            throw new IllegalArgumentException("Movement date is required");
        }
        if (points == 0) {
            throw new IllegalArgumentException("Movement points cannot be zero");
        }
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.description = description;
        this.points = points;
        this.benefitId = benefitId;
        this.type = points > 0 ? MovementType.EARN : MovementType.REDEEM;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getPoints() {
        return points;
    }

    public String getBenefitId() {
        return benefitId;
    }

    public MovementType getType() {
        return type;
    }
}
