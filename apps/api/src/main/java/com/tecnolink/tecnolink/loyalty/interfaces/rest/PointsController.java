package com.tecnolink.tecnolink.loyalty.interfaces.rest;

import com.tecnolink.tecnolink.loyalty.application.LoyaltyService;
import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.PointsMovement;
import com.tecnolink.tecnolink.loyalty.domain.model.valueobjects.PointsSummary;
import com.tecnolink.tecnolink.loyalty.interfaces.rest.dto.RedeemBenefitRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/{userId}/points")
public class PointsController {

    private final LoyaltyService loyaltyService;

    public PointsController(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @GetMapping
    public PointsSummary summary(@PathVariable String userId) {
        return loyaltyService.getSummary(userId);
    }

    @PostMapping("/redemptions")
    public ResponseEntity<PointsMovement> redeem(@PathVariable String userId,
                                                 @Valid @RequestBody RedeemBenefitRequest request) {
        PointsMovement movement = loyaltyService.redeem(userId, request.benefitId());
        return ResponseEntity.status(201).body(movement);
    }
}
