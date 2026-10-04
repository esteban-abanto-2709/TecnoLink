package com.tecnolink.tecnolink.loyalty.application;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.Benefit;
import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.PointsMovement;
import com.tecnolink.tecnolink.loyalty.domain.model.valueobjects.PointsSummary;
import com.tecnolink.tecnolink.loyalty.domain.repositories.BenefitRepository;
import com.tecnolink.tecnolink.loyalty.domain.repositories.PointsMovementRepository;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public PointsMovement redeem(String userId, String benefitId) {
        Benefit benefit = benefitRepository.findById(benefitId)
                .orElseThrow(() -> new NotFoundException("Benefit " + benefitId + " not found"));
        PointsSummary summary = getSummary(userId);
        if (summary.usedBenefits().contains(benefitId)) {
            throw new IllegalArgumentException("Benefit " + benefitId + " was already redeemed");
        }
        if (summary.balance() < benefit.getCost()) {
            throw new IllegalArgumentException("Not enough points: balance " + summary.balance()
                    + ", cost " + benefit.getCost());
        }
        PointsMovement movement = new PointsMovement(movementRepository.nextId(), userId, LocalDate.now(),
                "Canje: " + benefit.getName(), -benefit.getCost(), benefit.getId());
        return movementRepository.save(movement);
    }

    public PointsMovement awardPoints(String userId, int points, String description) {
        if (points <= 0) {
            throw new IllegalArgumentException("Awarded points must be greater than zero");
        }
        PointsMovement movement = new PointsMovement(movementRepository.nextId(), userId, LocalDate.now(),
                description, points, null);
        return movementRepository.save(movement);
    }
}
