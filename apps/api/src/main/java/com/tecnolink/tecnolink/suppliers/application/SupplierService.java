package com.tecnolink.tecnolink.suppliers.application;

import com.tecnolink.tecnolink.catalog.application.CatalogService;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;
import com.tecnolink.tecnolink.suppliers.domain.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final CatalogService catalogService;

    public SupplierService(SupplierRepository supplierRepository, CatalogService catalogService) {
        this.supplierRepository = supplierRepository;
        this.catalogService = catalogService;
    }

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(String id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier " + id + " not found"));
    }

    public List<Product> getProducts(String supplierId) {
        getSupplier(supplierId);
        return catalogService.getProductsBySupplier(supplierId);
    }

    public List<TechService> getServices(String supplierId) {
        getSupplier(supplierId);
        return catalogService.getServicesBySupplier(supplierId);
    }
}
