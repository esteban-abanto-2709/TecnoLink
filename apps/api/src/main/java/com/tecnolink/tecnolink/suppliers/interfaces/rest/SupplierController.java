package com.tecnolink.tecnolink.suppliers.interfaces.rest;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.suppliers.application.SupplierService;
import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> all() {
        return supplierService.getSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier one(@PathVariable String id) {
        return supplierService.getSupplier(id);
    }

    @GetMapping("/{id}/products")
    public List<Product> products(@PathVariable String id) {
        return supplierService.getProducts(id);
    }

    @GetMapping("/{id}/services")
    public List<TechService> services(@PathVariable String id) {
        return supplierService.getServices(id);
    }
}
