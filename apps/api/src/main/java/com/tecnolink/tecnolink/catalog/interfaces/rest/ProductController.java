package com.tecnolink.tecnolink.catalog.interfaces.rest;

import com.tecnolink.tecnolink.catalog.application.dtos.ProductDto;
import com.tecnolink.tecnolink.catalog.application.handlers.commands.ProductCommandHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.CompareProductsQueryHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.ProductCatalogQueryHandler;
import com.tecnolink.tecnolink.catalog.application.handlers.queries.ProductQueryHandler;
import com.tecnolink.tecnolink.catalog.application.queries.CompareProductsQuery;
import com.tecnolink.tecnolink.catalog.application.queries.FilterProductsQuery;
import com.tecnolink.tecnolink.catalog.application.queries.GetProductQuery;
import com.tecnolink.tecnolink.catalog.application.queries.GetProductsQuery;
import com.tecnolink.tecnolink.catalog.application.queries.SearchProductsQuery;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.CreateProductRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.dto.UpdateProductRequest;
import com.tecnolink.tecnolink.catalog.interfaces.rest.mappers.ProductRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(
        name = "Products",
        description = "Operaciones de gestión y consulta de productos del catálogo"
)
public class ProductController {

    private final ProductCommandHandler productCommandHandler;
    private final ProductQueryHandler productQueryHandler;
    private final ProductCatalogQueryHandler productCatalogQueryHandler;
    private final CompareProductsQueryHandler compareProductsQueryHandler;

    public ProductController(
            ProductCommandHandler productCommandHandler,
            ProductQueryHandler productQueryHandler,
            ProductCatalogQueryHandler productCatalogQueryHandler,
            CompareProductsQueryHandler compareProductsQueryHandler
    ) {
        this.productCommandHandler = productCommandHandler;
        this.productQueryHandler = productQueryHandler;
        this.productCatalogQueryHandler = productCatalogQueryHandler;
        this.compareProductsQueryHandler = compareProductsQueryHandler;
    }

    @GetMapping
    @Operation(
            summary = "Listar productos",
            description = "Obtiene todos los productos registrados en el catálogo."
    )
    public List<ProductDto> getProducts() {
        return productQueryHandler.handle(
                new GetProductsQuery()
        );
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consultar producto",
            description = "Obtiene el detalle de un producto mediante su identificador."
    )
    public ProductDto getProduct(
            @Parameter(description = "Identificador del producto")
            @PathVariable String id
    ) {
        return productQueryHandler.handle(
                new GetProductQuery(id)
        );
    }

    @GetMapping("/search")
    @Operation(
            summary = "Buscar productos",
            description = "Busca productos utilizando una palabra clave."
    )
    public List<ProductDto> searchProducts(
            @Parameter(description = "Palabra clave de búsqueda")
            @RequestParam String keyword
    ) {
        return productCatalogQueryHandler.handle(
                new SearchProductsQuery(keyword)
        );
    }

    @GetMapping("/filter")
    @Operation(
            summary = "Filtrar productos",
            description = "Filtra productos por categoría y/o rango de precios."
    )
    public List<ProductDto> filterProducts(
            @Parameter(description = "Identificador de la categoría")
            @RequestParam(required = false) String categoryId,

            @Parameter(description = "Precio mínimo")
            @RequestParam(required = false) Double minPrice,

            @Parameter(description = "Precio máximo")
            @RequestParam(required = false) Double maxPrice
    ) {
        return productCatalogQueryHandler.handle(
                new FilterProductsQuery(
                        categoryId,
                        minPrice,
                        maxPrice
                )
        );
    }

    @GetMapping("/compare")
    @Operation(
            summary = "Comparar productos",
            description = "Obtiene los productos indicados para realizar una comparación."
    )
    public List<ProductDto> compareProducts(
            @Parameter(
                    description = "Lista de identificadores de productos a comparar"
            )
            @RequestParam List<String> ids
    ) {
        return compareProductsQueryHandler.handle(
                new CompareProductsQuery(ids)
        );
    }

    @PostMapping
    @Operation(
            summary = "Registrar producto",
            description = "Registra un nuevo producto en el catálogo."
    )
    public ProductDto createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {
        return productCommandHandler.handle(
                ProductRestMapper.toCommand(request)
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar producto",
            description = "Actualiza la información de un producto existente."
    )
    public ProductDto updateProduct(
            @Parameter(description = "Identificador del producto")
            @PathVariable String id,

            @Valid @RequestBody UpdateProductRequest request
    ) {
        return productCommandHandler.handle(
                ProductRestMapper.toCommand(id, request)
        );
    }
}