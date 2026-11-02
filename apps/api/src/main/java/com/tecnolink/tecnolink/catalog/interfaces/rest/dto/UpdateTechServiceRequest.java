package com.tecnolink.tecnolink.catalog.interfaces.rest.dto;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateTechServiceRequest(
        @NotBlank
        String name,

        @Min(0)
        double price,

        ServicePricing pricing,

        String description,

        String coverage
) {
}