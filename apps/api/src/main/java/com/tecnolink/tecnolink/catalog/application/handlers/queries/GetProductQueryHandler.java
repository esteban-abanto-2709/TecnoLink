package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.assemblers.ProductAssembler;
import com.tecnolink.tecnolink.catalog.application.dtos.ProductDto;
import com.tecnolink.tecnolink.catalog.application.queries.GetProductQuery;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class GetProductQueryHandler {

    private final ProductRepository productRepository;

    public GetProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto handle(GetProductQuery query) {
        Product product = productRepository.findById(query.productId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Product not found")
                );

        return ProductAssembler.toDto(product);
    }
}