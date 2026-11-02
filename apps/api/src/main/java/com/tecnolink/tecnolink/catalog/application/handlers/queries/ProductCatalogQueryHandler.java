package com.tecnolink.tecnolink.catalog.application.handlers.queries;

import com.tecnolink.tecnolink.catalog.application.assemblers.ProductAssembler;
import com.tecnolink.tecnolink.catalog.application.dtos.ProductDto;
import com.tecnolink.tecnolink.catalog.application.queries.FilterProductsQuery;
import com.tecnolink.tecnolink.catalog.application.queries.SearchProductsQuery;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCatalogQueryHandler {

    private final ProductRepository productRepository;

    public ProductCatalogQueryHandler(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> handle(
            SearchProductsQuery query
    ) {
        return productRepository
                .search(query.keyword())
                .stream()
                .map(ProductAssembler::toDto)
                .toList();
    }

    public List<ProductDto> handle(
            FilterProductsQuery query
    ) {

        List<Product> products;

        if (query.categoryId() != null
                && query.minPrice() != null
                && query.maxPrice() != null) {

            products = productRepository
                    .findByCategoryId(query.categoryId())
                    .stream()
                    .filter(product ->
                            product.getPrice() >= query.minPrice()
                                    && product.getPrice() <= query.maxPrice()
                    )
                    .toList();

        } else if (query.categoryId() != null) {

            products = productRepository
                    .findByCategoryId(query.categoryId());

        } else if (query.minPrice() != null
                && query.maxPrice() != null) {

            products = productRepository
                    .findByPriceBetween(
                            query.minPrice(),
                            query.maxPrice()
                    );

        } else {

            products = productRepository.findAll();
        }

        return products.stream()
                .map(ProductAssembler::toDto)
                .toList();
    }
}