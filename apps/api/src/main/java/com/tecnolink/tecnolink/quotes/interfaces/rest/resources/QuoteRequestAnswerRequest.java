package com.tecnolink.tecnolink.quotes.interfaces.rest.resources;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record QuoteRequestAnswerRequest(
        @NotNull @Min(1) Integer validityDays,
        @NotNull @Positive Double subtotal,
        @NotBlank @Size(max = 1000) String conditions,
        @Size(max = 1000) String notes
) {}
