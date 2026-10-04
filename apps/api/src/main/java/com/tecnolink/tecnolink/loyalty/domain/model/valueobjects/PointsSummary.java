package com.tecnolink.tecnolink.loyalty.domain.model.valueobjects;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.PointsMovement;

import java.util.List;

public record PointsSummary(int balance, List<PointsMovement> movements, List<String> usedBenefits) {
}
