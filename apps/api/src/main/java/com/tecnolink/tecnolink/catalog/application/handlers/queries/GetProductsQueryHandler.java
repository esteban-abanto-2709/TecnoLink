package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.assemblers.ProductAssembler;
import com.tecnolink.tecnolink.catalog.application.dtos.ProductDto;
import com.tecnolink.tecnolink.catalog.application.queries.GetProductsQuery;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsQueryHandler {

    private final ProductRepository productRepository;

    public GetProductsQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> handle(GetProductsQuery query) {
        return productRepository.findAll()
                .stream()
                .map(ProductAssembler::toDto)
                .toList();
    }
}