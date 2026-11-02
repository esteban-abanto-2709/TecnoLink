package com.tecnolink.tecnolink.quotes.domain.aggregates;

import com.tecnolink.tecnolink.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.time.LocalDate;
import java.util.*;

public class Comparison extends AbstractDomainAggregateRoot<Comparison> {
    public static final int MIN_PRODUCTS = 2;
    public static final int MAX_PRODUCTS = 4;

    private Long id;
    private final String clientId;
    private final LocalDate createdOn;
    private final List<String> productIds = new ArrayList<>();

    public Comparison(String clientId, List<String> productIds) {
        this(null, clientId, LocalDate.now(), productIds);
    }

    public Comparison(Long id, String clientId, LocalDate createdOn, List<String> productIds) {
        if (clientId == null || clientId.isBlank()) throw new IllegalArgumentException("Client id is required");
        if (createdOn == null) throw new IllegalArgumentException("Created on is required");
        this.id = id;
        this.clientId = clientId.trim();
        this.createdOn = createdOn;
        if (productIds != null) productIds.forEach(this::addProduct);
    }

    public void addProduct(String productId) {
        if (productId == null || productId.isBlank()) throw new IllegalArgumentException("Product id is required");
        if (productIds.contains(productId))
            throw new IllegalStateException("Product " + productId + " is already in the comparison");
        if (productIds.size() == MAX_PRODUCTS)
            throw new IllegalStateException("A comparison allows up to " + MAX_PRODUCTS + " products");
        productIds.add(productId);
    }

    public void removeProduct(String productId) {
        if (!productIds.remove(productId))
            throw new IllegalArgumentException("Product " + productId + " is not in the comparison");
    }

    public Map<String, List<String>> buildTable(List<Specification> specifications) {
        if (productIds.size() < MIN_PRODUCTS)
            throw new IllegalStateException("A comparison needs at least " + MIN_PRODUCTS + " products");
        Map<String, List<String>> table = new LinkedHashMap<>();
        for (Specification specification : specifications) {
            int column = productIds.indexOf(specification.getProductId());
            if (column < 0) continue;
            table.computeIfAbsent(specification.getName(), name -> new ArrayList<>(Collections.nCopies(productIds.size(), "-")))
                    .set(column, specification.text());
        }
        return table;
    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public List<String> getProductIds() {
        return Collections.unmodifiableList(productIds);
    }
}
