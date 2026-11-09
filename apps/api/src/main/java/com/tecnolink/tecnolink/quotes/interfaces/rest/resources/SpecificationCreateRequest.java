package com.tecnolink.tecnolink.quotes.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SpecificationCreateRequest(
        @NotBlank String productId,
        @NotBlank @Size(max = 60) String name,
        @NotBlank @Size(max = 120) String value,
        @Size(max = 20) String unit
) {}
