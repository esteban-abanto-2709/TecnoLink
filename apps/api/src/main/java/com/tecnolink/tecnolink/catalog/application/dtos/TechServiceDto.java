package com.tecnolink.tecnolink.catalog.application.dtos;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;
import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;

import java.time.LocalDate;
import java.util.List;

public record TechServiceDto(
        String id,
        String name,
        String categoryId,
        String supplierId,
        double price,
        String description,
        List<String> images,
        LocalDate publishedOn,
        ListingStatus status,
        ServicePricing pricing,
        String coverage,
        String modality,
        String estimatedDuration
) {
}