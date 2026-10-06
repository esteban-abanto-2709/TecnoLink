package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductJpaEntity extends ListingJpaEntity {

    private String brand;

    private String model;

    @Column(nullable = false)
    private int stock;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_specifications", joinColumns = @JoinColumn(name = "product_id"))
    @OrderColumn(name = "position")
    private List<SpecificationEmbeddable> specs = new ArrayList<>();

    protected ProductJpaEntity() {
    }

    public ProductJpaEntity(String id, String name, String categoryId, String supplierId, double price,
                            String description, List<String> images, LocalDate publishedOn, ListingStatus status,
                            String brand, String model, int stock, List<SpecificationEmbeddable> specs) {
        super(id, name, categoryId, supplierId, price, description, images, publishedOn, status);
        this.brand = brand;
        this.model = model;
        this.stock = stock;
        this.specs = new ArrayList<>(specs);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getStock() {
        return stock;
    }

    public List<SpecificationEmbeddable> getSpecs() {
        return specs;
    }
}
