package com.tecnolink.tecnolink.catalog.interfaces.rest.mappers;

import com.tecnolink.tecnolink.catalog.application.commands.CreateCategoryCommand;
import com.tecnolink.tecnolink.catalog.application.commands.RenameCategoryCommand;
import com.tecnolink.tecnolink.catalog.application.commands.SetCategoryActiveCommand;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CategoryStatusRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateCategoryRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.RenameCategoryRequest;

public final class CategoryRestMapper {

    private CategoryRestMapper() {
    }

    public static CreateCategoryCommand toCommand(
            CreateCategoryRequest request
    ) {
        return new CreateCategoryCommand(
                request.name(),
                request.kind()
        );
    }

    public static RenameCategoryCommand toCommand(
            String categoryId,
            RenameCategoryRequest request
    ) {
        return new RenameCategoryCommand(
                categoryId,
                request.name()
        );
    }

    public static SetCategoryActiveCommand toCommand(
            String categoryId,
            CategoryStatusRequest request
    ) {
        return new SetCategoryActiveCommand(
                categoryId,
                request.active()
        );
    }
}