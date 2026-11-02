package com.tecnolink.tecnolink.catalog.application.commands;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;

public record UpdateTechServiceCommand(
        String serviceId,
        String name,
        double price,
        ServicePricing pricing,
        String description,
        String coverage
) {
}