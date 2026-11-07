package com.tecnolink.tecnolink.catalog.interfaces.rest.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryStatusRequest(
        @NotNull Boolean active
) {
}
