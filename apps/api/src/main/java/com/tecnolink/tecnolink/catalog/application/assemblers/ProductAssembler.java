package com.tecnolink.tecnolink.catalog.application.assemblers;

import com.tecnolink.tecnolink.catalog.application.dtos.ProductDto;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;

public final class ProductAssembler {

    private ProductAssembler() {
    }

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getModel(),
                product.getCategoryId(),
                product.getSupplierId(),
                product.getPrice(),
                product.getDescription(),
                product.getImages(),
                product.getPublishedOn(),
                product.getStatus(),
                product.getStock(),
                product.getSpecs()
        );
    }
}