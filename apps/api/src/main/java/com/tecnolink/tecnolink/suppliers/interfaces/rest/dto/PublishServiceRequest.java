package com.tecnolink.tecnolink.suppliers.interfaces.rest.dto;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PublishServiceRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank String categoryId,
        @NotNull @Positive Double price,
        @NotNull ServicePricing pricing,
        @NotBlank @Size(min = 20, max = 1000) String description,
        @NotBlank @Size(max = 100) String coverage
) {
}
