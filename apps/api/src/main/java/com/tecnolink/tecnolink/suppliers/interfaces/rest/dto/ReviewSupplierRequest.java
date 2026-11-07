package com.tecnolink.tecnolink.suppliers.interfaces.rest.dto;

import com.tecnolink.tecnolink.suppliers.domain.model.enums.SupplierStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewSupplierRequest(
        @NotNull SupplierStatus status,
        @Size(max = 300) String note
) {
}
