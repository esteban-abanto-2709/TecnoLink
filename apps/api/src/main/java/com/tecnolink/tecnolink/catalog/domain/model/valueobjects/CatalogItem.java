package com.tecnolink.tecnolink.catalog.domain.model.valueobjects;

import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;
import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;

public record CatalogItem(
        String id,
        CategoryKind kind,
        String name,
        String categoryId,
        String supplierId,
        double price,
        ServicePricing pricing,
        String description
) {
}
