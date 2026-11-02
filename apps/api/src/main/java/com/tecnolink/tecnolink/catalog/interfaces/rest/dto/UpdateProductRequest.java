package com.tecnolink.tecnolink.catalog.interfaces.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateProductRequest(
        @NotBlank
        String name,

        String brand,

        @Min(0)
        double price,

        String description
) {
}