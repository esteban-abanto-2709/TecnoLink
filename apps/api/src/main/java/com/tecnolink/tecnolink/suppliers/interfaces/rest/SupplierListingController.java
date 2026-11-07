package com.tecnolink.tecnolink.suppliers.interfaces.rest;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.suppliers.application.SupplierService;
import com.tecnolink.tecnolink.suppliers.interfaces.rest.dto.PublishProductRequest;
import com.tecnolink.tecnolink.suppliers.interfaces.rest.dto.PublishServiceRequest;
import com.tecnolink.tecnolink.suppliers.interfaces.rest.dto.UpdateProductRequest;
import com.tecnolink.tecnolink.suppliers.interfaces.rest.dto.UpdateServiceRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/suppliers/{supplierId}")
public class SupplierListingController {

    private final SupplierService supplierService;

    public SupplierListingController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> publishProduct(@PathVariable String supplierId,
                                                  @Valid @RequestBody PublishProductRequest request) {
        Product product = supplierService.publishProduct(supplierId, request.name(), request.brand(),
                request.categoryId(), request.price(), request.description());
        return ResponseEntity.status(201).body(product);
    }

    @PutMapping("/products/{productId}")
    public Product editProduct(@PathVariable String supplierId, @PathVariable String productId,
                               @Valid @RequestBody UpdateProductRequest request) {
        return supplierService.editProduct(supplierId, productId, request.name(), request.brand(),
                request.price(), request.description());
    }

    @PostMapping("/services")
    public ResponseEntity<TechService> publishService(@PathVariable String supplierId,
                                                      @Valid @RequestBody PublishServiceRequest request) {
        TechService service = supplierService.publishService(supplierId, request.name(), request.categoryId(),
                request.price(), request.pricing(), request.description(), request.coverage());
        return ResponseEntity.status(201).body(service);
    }

    @PutMapping("/services/{serviceId}")
    public TechService editService(@PathVariable String supplierId, @PathVariable String serviceId,
                                   @Valid @RequestBody UpdateServiceRequest request) {
        return supplierService.editService(supplierId, serviceId, request.name(), request.price(),
                request.pricing(), request.description(), request.coverage());
    }
}
