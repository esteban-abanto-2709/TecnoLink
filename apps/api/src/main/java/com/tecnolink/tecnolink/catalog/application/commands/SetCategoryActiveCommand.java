package com.tecnolink.tecnolink.catalog.application.commands;

public record SetCategoryActiveCommand(
        String categoryId,
        boolean active
) {
}