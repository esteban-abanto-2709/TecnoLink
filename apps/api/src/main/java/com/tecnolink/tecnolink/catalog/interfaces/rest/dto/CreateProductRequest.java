package com.tecnolink.tecnolink.catalog.interfaces.rest.dto;

import com.tecnolink.tecnolink.catalog.domain.model.valueobjects.Specification;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateProductRequest(
        @NotBlank
        String name,

        String brand,

        @NotBlank
        String model,

        @NotBlank
        String categoryId,

        @NotBlank
        String supplierId,

        @Min(0)
        double price,

        String description,

        List<String> images,

        @Min(0)
        int stock,

        List<Specification> specs
) {
}