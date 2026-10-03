package com.tecnolink.tecnolink.suppliers.interfaces.rest;

import com.tecnolink.tecnolink.suppliers.application.SupplierService;
import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;
import com.tecnolink.tecnolink.suppliers.interfaces.rest.dto.ReviewSupplierRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/suppliers")
public class AdminSupplierController {

    private final SupplierService supplierService;

    public AdminSupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> all() {
        return supplierService.getSuppliers();
    }

    @PatchMapping("/{id}/review")
    public Supplier review(@PathVariable String id, @Valid @RequestBody ReviewSupplierRequest request) {
        return supplierService.reviewSupplier(id, request.status(), request.note());
    }
}
