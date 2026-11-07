package com.tecnolink.tecnolink.orders.domain.model.entities;

public class CartItem {
    private final String listingId;
    private final int quantity;
    private final double unitPrice;

    public CartItem(String listingId, int quantity, double unitPrice) {
        if (listingId == null || listingId.isBlank()) {
            throw new IllegalArgumentException("Cart item listing is required");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Cart item quantity must be greater than zero");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Cart item price cannot be negative");
        }
        this.listingId = listingId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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
}
