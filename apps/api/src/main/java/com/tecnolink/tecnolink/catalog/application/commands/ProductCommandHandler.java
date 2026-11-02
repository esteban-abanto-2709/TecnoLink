package com.tecnolink.tecnolink.catalog.application.handlers.commands;

import com.tecnolink.tecnolink.catalog.application.assemblers.ProductAssembler;
import com.tecnolink.tecnolink.catalog.application.commands.CreateProductCommand;
import com.tecnolink.tecnolink.catalog.application.commands.UpdateProductCommand;
import com.tecnolink.tecnolink.catalog.application.dtos.ProductDto;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductCommandHandler {

    private final ProductRepository productRepository;

    public ProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto handle(CreateProductCommand command) {

        String id = UUID.randomUUID().toString();

        Product product = new Product(
                id,
                command.name(),
                command.brand(),
                command.model(),
                command.categoryId(),
                command.supplierId(),
                command.price(),
                command.description(),
                command.images(),
                null,
                null,
                command.stock(),
                command.specs()
        );

        Product savedProduct = productRepository.save(product);

        return ProductAssembler.toDto(savedProduct);
    }

    public ProductDto handle(UpdateProductCommand command) {

        Product product = productRepository.findById(command.productId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Product not found")
                );

        product.update(
                command.name(),
                command.brand(),
                command.price(),
                command.description()
        );

        Product savedProduct = productRepository.save(product);

        return ProductAssembler.toDto(savedProduct);
    }
}