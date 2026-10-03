package com.tecnolink.tecnolink.catalog.interfaces.rest.dto;

import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank @Size(max = 50) String name,
        @NotNull CategoryKind kind
) {
}
