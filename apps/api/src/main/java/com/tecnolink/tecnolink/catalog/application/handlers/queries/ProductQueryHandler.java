package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.assemblers.ProductAssembler;
import com.tecnolink.tecnolink.catalog.application.dtos.ProductDto;
import com.tecnolink.tecnolink.catalog.application.queries.GetProductQuery;
import com.tecnolink.tecnolink.catalog.application.queries.GetProductsQuery;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> handle(GetProductsQuery query) {

        return productRepository.findAll()
                .stream()
                .map(ProductAssembler::toDto)
                .toList();
    }

    public ProductDto handle(GetProductQuery query) {

        return productRepository.findById(query.productId())
                .map(ProductAssembler::toDto)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Product " + query.productId() + " not found"
                        )
                );
    }
}