package com.tecnolink.tecnolink.catalog.application.commands;

public record RenameCategoryCommand(
        String categoryId,
        String name
) {
}