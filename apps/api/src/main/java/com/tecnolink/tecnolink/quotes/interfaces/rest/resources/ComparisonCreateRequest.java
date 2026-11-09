package com.tecnolink.tecnolink.quotes.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ComparisonCreateRequest(
        @NotBlank String clientId,
        List<String> productIds
) {}
