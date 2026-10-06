package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.valueobjects.Specification;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.ProductJpaEntity;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.SpecificationEmbeddable;

public final class ProductPersistenceMapper {

    private ProductPersistenceMapper() {
    }

    public static ProductJpaEntity toEntity(Product product) {
        return new ProductJpaEntity(product.getId(), product.getName(), product.getCategoryId(),
                product.getSupplierId(), product.getPrice(), product.getDescription(), product.getImages(),
                product.getPublishedOn(), product.getStatus(), product.getBrand(), product.getModel(),
                product.getStock(), product.getSpecs().stream()
                .map(spec -> new SpecificationEmbeddable(spec.name(), spec.value(), spec.unit()))
                .toList());
    }

    public static Product toDomain(ProductJpaEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getBrand(), entity.getModel(),
                entity.getCategoryId(), entity.getSupplierId(), entity.getPrice(), entity.getDescription(),
                entity.getImages(), entity.getPublishedOn(), entity.getStatus(), entity.getStock(),
                entity.getSpecs().stream()
                        .map(spec -> new Specification(spec.getName(), spec.getValue(), spec.getUnit()))
                        .toList());
    }
}
