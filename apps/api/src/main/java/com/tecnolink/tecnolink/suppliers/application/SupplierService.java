package com.tecnolink.tecnolink.suppliers.application;

import com.tecnolink.tecnolink.catalog.application.CatalogService;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.TechService;
import com.tecnolink.tecnolink.catalog.domain.model.enums.ServicePricing;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;
import com.tecnolink.tecnolink.suppliers.domain.model.enums.SupplierStatus;
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

    public Product publishProduct(String supplierId, String name, String brand, String categoryId,
                                  double price, String description) {
        getSupplierAllowedToPublish(supplierId);
        return catalogService.createProduct(supplierId, name, brand, categoryId, price, description);
    }

    public Product editProduct(String supplierId, String productId, String name, String brand,
                               double price, String description) {
        getSupplierAllowedToPublish(supplierId);
        Product product = catalogService.getProduct(productId);
        if (!product.getSupplierId().equals(supplierId)) {
            throw new NotFoundException("Product " + productId + " not found for supplier " + supplierId);
        }
        return catalogService.updateProduct(productId, name, brand, price, description);
    }

    public TechService publishService(String supplierId, String name, String categoryId, double price,
                                      ServicePricing pricing, String description, String coverage) {
        getSupplierAllowedToPublish(supplierId);
        return catalogService.createService(supplierId, name, categoryId, price, pricing, description, coverage);
    }

    public TechService editService(String supplierId, String serviceId, String name, double price,
                                   ServicePricing pricing, String description, String coverage) {
        getSupplierAllowedToPublish(supplierId);
        TechService service = catalogService.getService(serviceId);
        if (!service.getSupplierId().equals(supplierId)) {
            throw new NotFoundException("Service " + serviceId + " not found for supplier " + supplierId);
        }
        return catalogService.updateService(serviceId, name, price, pricing, description, coverage);
    }

    public Supplier reviewSupplier(String id, SupplierStatus status, String note) {
        Supplier supplier = getSupplier(id);
        supplier.review(status, note);
        return supplierRepository.save(supplier);
    }

    private Supplier getSupplierAllowedToPublish(String supplierId) {
        Supplier supplier = getSupplier(supplierId);
        if (supplier.isSuspended()) {
            throw new IllegalArgumentException("Supplier " + supplierId + " is suspended");
        }
        return supplier;
    }
}
