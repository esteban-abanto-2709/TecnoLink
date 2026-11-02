package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.valueobjects.Specification;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.ProductJpaEntity;
import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.SpecificationEmbeddable;

import java.util.List;

public final class ProductPersistenceMapper {

    private ProductPersistenceMapper() {
    }

    public static ProductJpaEntity toEntity(Product product) {

        List<SpecificationEmbeddable> specifications =
                product.getSpecs()
                        .stream()
                        .map(specification ->
                                new SpecificationEmbeddable(
                                        specification.name(),
                                        specification.value(),
                                        specification.unit()
                                )
                        )
                        .toList();

        return new ProductJpaEntity(
                product.getId(),
                product.getName(),
                product.getCategoryId(),
                product.getSupplierId(),
                product.getPrice(),
                product.getDescription(),
                product.getImages(),
                product.getPublishedOn(),
                product.getStatus(),
                product.getBrand(),
                product.getModel(),
                product.getStock(),
                specifications
        );
    }

    public static Product toDomain(ProductJpaEntity entity) {

        List<Specification> specifications =
                entity.getSpecifications()
                        .stream()
                        .map(specification ->
                                new Specification(
                                        specification.getName(),
                                        specification.getValue(),
                                        specification.getUnit()
                                )
                        )
                        .toList();

        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getBrand(),
                entity.getModel(),
                entity.getCategoryId(),
                entity.getSupplierId(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getImages(),
                entity.getPublishedOn(),
                entity.getStatus(),
                entity.getStock(),
                specifications
        );
    }
}