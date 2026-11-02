package com.tecnolink.tecnolink.catalog.application.commands;

import com.tecnolink.tecnolink.catalog.domain.model.valueobjects.Specification;

import java.util.List;

public record CreateProductCommand(
        String name,
        String brand,
        String model,
        String categoryId,
        String supplierId,
        double price,
        String description,
        List<String> images,
        int stock,
        List<Specification> specs
) {
}