package com.tecnolink.tecnolink.orders.domain.model.entities;

public class OrderDetail {
    private final String listingId;
    private final int quantity;
    private final double unitPrice;
    private final double subtotal;

    public OrderDetail(String listingId, int quantity, double unitPrice, double subtotal) {
        if (listingId == null || listingId.isBlank()) {
            throw new IllegalArgumentException("Order detail listing is required");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Order detail quantity must be greater than zero");
        }
        if (unitPrice < 0 || subtotal < 0) {
            throw new IllegalArgumentException("Order detail amounts cannot be negative");
        }
        this.listingId = listingId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public String getListingId() {
        return listingId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
