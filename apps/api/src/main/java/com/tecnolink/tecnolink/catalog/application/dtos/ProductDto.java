package com.tecnolink.tecnolink.catalog.application.dtos;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;
import com.tecnolink.tecnolink.catalog.domain.model.valueobjects.Specification;

import java.time.LocalDate;
import java.util.List;

public record ProductDto(
        String id,
        String name,
        String brand,
        String model,
        String categoryId,
        String supplierId,
        double price,
        String description,
        List<String> images,
        LocalDate publishedOn,
        ListingStatus status,
        int stock,
        List<Specification> specs
) {
}