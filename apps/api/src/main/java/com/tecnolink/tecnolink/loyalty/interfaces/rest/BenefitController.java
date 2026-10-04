package com.tecnolink.tecnolink.loyalty.interfaces.rest;

import com.tecnolink.tecnolink.loyalty.application.LoyaltyService;
import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.Benefit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/benefits")
public class BenefitController {

    private final LoyaltyService loyaltyService;

    public BenefitController(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @GetMapping
    public List<Benefit> all() {
        return loyaltyService.getBenefits();
    }
}
