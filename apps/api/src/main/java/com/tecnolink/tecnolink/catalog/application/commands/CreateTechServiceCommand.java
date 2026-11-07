package com.tecnolink.tecnolink.catalog.application.commands;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;

import java.util.List;

public record CreateTechServiceCommand(
        String name,
        String categoryId,
        String supplierId,
        double price,
        String description,
        List<String> images,
        ServicePricing pricing,
        String coverage,
        String modality,
        String estimatedDuration
) {
}