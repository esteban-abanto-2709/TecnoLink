package com.tecnolink.tecnolink.loyalty.application;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.Benefit;
import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.PointsMovement;
import com.tecnolink.tecnolink.loyalty.domain.model.valueobjects.PointsSummary;
import com.tecnolink.tecnolink.loyalty.domain.repositories.BenefitRepository;
import com.tecnolink.tecnolink.loyalty.domain.repositories.PointsMovementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LoyaltyService {

    private final BenefitRepository benefitRepository;
    private final PointsMovementRepository movementRepository;

    public LoyaltyService(BenefitRepository benefitRepository, PointsMovementRepository movementRepository) {
        this.benefitRepository = benefitRepository;
        this.movementRepository = movementRepository;
    }

    public List<Benefit> getBenefits() {
        return benefitRepository.findAll();
    }

    public PointsSummary getSummary(String userId) {
        List<PointsMovement> movements = movementRepository.findByUserId(userId);
        movements.sort(Comparator.comparing(PointsMovement::getDate).reversed());

        int balance = 0;
        List<String> usedBenefits = new ArrayList<>();
        for (PointsMovement movement : movements) {
            balance += movement.getPoints();
            if (movement.getBenefitId() != null && !usedBenefits.contains(movement.getBenefitId())) {
                usedBenefits.add(movement.getBenefitId());
            }
        }
        return new PointsSummary(balance, movements, usedBenefits);
    }
}
