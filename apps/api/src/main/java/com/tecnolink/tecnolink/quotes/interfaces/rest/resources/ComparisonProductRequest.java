package com.tecnolink.tecnolink.quotes.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record ComparisonProductRequest(@NotBlank String productId) {}
