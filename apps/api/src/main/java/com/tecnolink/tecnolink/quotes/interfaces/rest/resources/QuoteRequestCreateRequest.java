package com.tecnolink.tecnolink.quotes.interfaces.rest.resources;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuoteRequestCreateRequest(
        @NotBlank String clientId,
        @NotBlank String listingId,
        @NotNull @Min(1) Integer quantity,
        @NotBlank @Size(max = 1000) String requirement
) {}
