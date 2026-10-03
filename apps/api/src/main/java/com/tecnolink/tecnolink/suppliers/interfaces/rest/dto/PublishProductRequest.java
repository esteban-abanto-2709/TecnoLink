package com.tecnolink.tecnolink.suppliers.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PublishProductRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 50) String brand,
        @NotBlank String categoryId,
        @NotNull @Positive Double price,
        @NotBlank @Size(min = 20, max = 1000) String description
) {
}
