package com.tecnolink.tecnolink.catalog.interfaces.rest.mappers;

import com.tecnolink.tecnolink.catalog.application.commands.CreateProductCommand;
import com.tecnolink.tecnolink.catalog.application.commands.UpdateProductCommand;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateProductRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.UpdateProductRequest;

public final class ProductRestMapper {

    private ProductRestMapper() {
    }

    public static CreateProductCommand toCommand(
            CreateProductRequest request
    ) {
        return new CreateProductCommand(
                request.name(),
                request.brand(),
                request.model(),
                request.categoryId(),
                request.supplierId(),
                request.price(),
                request.description(),
                request.images(),
                request.stock(),
                request.specs()
        );
    }

    public static UpdateProductCommand toCommand(
            String productId,
            UpdateProductRequest request
    ) {
        return new UpdateProductCommand(
                productId,
                request.name(),
                request.brand(),
                request.price(),
                request.description()
        );
    }
}