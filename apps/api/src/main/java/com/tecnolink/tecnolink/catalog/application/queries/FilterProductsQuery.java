package com.tecnolink.tecnolink.catalog.application.queries;

public record FilterProductsQuery(
        String categoryId,
        Double minPrice,
        Double maxPrice
) {
}