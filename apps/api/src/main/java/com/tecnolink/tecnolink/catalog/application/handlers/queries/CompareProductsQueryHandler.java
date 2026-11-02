package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.dtos.ProductDto;
import com.tecnolink.tecnolink.catalog.application.queries.CompareProductsQuery;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompareProductsQueryHandler {

    private final ProductRepository productRepository;

    public CompareProductsQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> handle(CompareProductsQuery query) {

        if (query.productIds() == null || query.productIds().size() < 2) {
            throw new IllegalArgumentException(
                    "At least two products are required for comparison"
            );
        }

        List<ProductDto> products = new ArrayList<>();

        for (String productId : query.productIds()) {

            Product product = productRepository
                    .findById(productId)
                    .orElseThrow(() ->
                            new NotFoundException(
                                    "Product " + productId + " not found"
                            )
                    );

            products.add(
                    new ProductDto(
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
                    )
            );
        }

        return products;
    }
}