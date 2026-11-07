package com.tecnolink.tecnolink.loyalty.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record RedeemBenefitRequest(
        @NotBlank String benefitId
) {
}
