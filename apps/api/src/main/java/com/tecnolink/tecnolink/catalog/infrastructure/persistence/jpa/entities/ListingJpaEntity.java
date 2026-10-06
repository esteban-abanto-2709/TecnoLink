package com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;
import com.tecnolink.tecnolink.suppliers.infrastructure.persistence.jpa.entities.SupplierJpaEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "listings")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ListingJpaEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "category_id", nullable = false)
    private String categoryId;

    @Column(name = "supplier_id", nullable = false)
    private String supplierId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryJpaEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private SupplierJpaEntity supplier;

    @Column(nullable = false)
    private double price;

    @Column(length = 1000)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "listing_images", joinColumns = @JoinColumn(name = "listing_id"))
    @OrderColumn(name = "position")
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @Column(name = "published_on")
    private LocalDate publishedOn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ListingStatus status;

    protected ListingJpaEntity() {
    }

    protected ListingJpaEntity(String id, String name, String categoryId, String supplierId, double price,
                               String description, List<String> images, LocalDate publishedOn,
                               ListingStatus status) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.price = price;
        this.description = description;
        this.images = new ArrayList<>(images);
        this.publishedOn = publishedOn;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public ListingStatus getStatus() {
        return status;
    }
}
