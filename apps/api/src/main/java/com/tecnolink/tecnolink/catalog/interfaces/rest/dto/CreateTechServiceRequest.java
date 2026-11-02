package com.tecnolink.tecnolink.catalog.interfaces.rest.dto;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateTechServiceRequest(
        @NotBlank String name,
        @NotBlank String categoryId,
        @NotBlank String supplierId,
        @Min(0) double price,
        String description,
        List<String> images,
        @NotNull ServicePricing pricing,
        String coverage,
        String modality,
        String estimatedDuration
) {
}