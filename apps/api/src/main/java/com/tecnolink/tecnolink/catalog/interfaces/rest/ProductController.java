package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.dtos.ProductDto;
import com.tecnolink.tecnolink.catalog.application.handlers.commands.ProductCommandHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.GetProductQueryHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.GetProductsQueryHandler;
import com.tecnolink.tecnolink.catalog.application.queries.GetProductQuery;
import com.tecnolink.tecnolink.catalog.application.queries.GetProductsQuery;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateProductRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.UpdateProductRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.mappers.ProductRestMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductCommandHandler productCommandHandler;
    private final GetProductsQueryHandler getProductsQueryHandler;
    private final GetProductQueryHandler getProductQueryHandler;

    public ProductController(
            ProductCommandHandler productCommandHandler,
            GetProductsQueryHandler getProductsQueryHandler,
            GetProductQueryHandler getProductQueryHandler
    ) {
        this.productCommandHandler = productCommandHandler;
        this.getProductsQueryHandler = getProductsQueryHandler;
        this.getProductQueryHandler = getProductQueryHandler;
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        GetProductsQuery query = new GetProductsQuery();

        return getProductsQueryHandler.handle(query);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable String id) {
        GetProductQuery query = new GetProductQuery(id);

        return getProductQueryHandler.handle(query);
    }

    @PostMapping
    public ProductDto createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {
        return productCommandHandler.handle(
                ProductRestMapper.toCommand(request)
        );
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(
            @PathVariable String id,
            @Valid @RequestBody UpdateProductRequest request
    ) {
        return productCommandHandler.handle(
                ProductRestMapper.toCommand(id, request)
        );
    }
}