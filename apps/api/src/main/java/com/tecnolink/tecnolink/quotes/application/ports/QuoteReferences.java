package com.tecnolink.tecnolink.quotes.application.ports;

public interface QuoteReferences {
    void requireClient(String clientId);
    void requireProduct(String productId);
    void requireAvailableProduct(String productId);
    void requireAvailableListing(String listingId);
}
