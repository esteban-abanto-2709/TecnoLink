package com.tecnolink.tecnolink.catalog.application.commands;

public record UpdateProductCommand(
        String productId,
        String name,
        String brand,
        double price,
        String description
) {
}