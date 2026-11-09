package com.tecnolink.tecnolink.quotes.infrastructure.acl;

import com.tecnolink.tecnolink.catalog.application.CatalogService;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Listing;
import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.model.enums.ListingStatus;
import com.tecnolink.tecnolink.iam.domain.repositories.ClientRepository;
import com.tecnolink.tecnolink.quotes.application.ports.QuoteReferences;
import com.tecnolink.tecnolink.shared.domain.exceptions.NotFoundException;
import com.tecnolink.tecnolink.suppliers.application.SupplierService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

@Component
public class QuoteReferencesAdapter implements QuoteReferences {
    private final ClientRepository clients;
    private final CatalogService catalog;
    private final SupplierService suppliers;

    public QuoteReferencesAdapter(ClientRepository clients, CatalogService catalog, SupplierService suppliers) {
        this.clients = clients;
        this.catalog = catalog;
        this.suppliers = suppliers;
    }

    @Override
    public void requireClient(String clientId) {
        if (clients.findById(clientId).isEmpty())
            throw new IllegalArgumentException("Client not found: " + clientId);
    }

    @Override
    public void requireProduct(String productId) {
        product(productId);
    }

    @Override
    public void requireAvailableProduct(String productId) {
        requireAvailable(product(productId));
    }

    @Override
    public void requireAvailableListing(String listingId) {
        Listing listing = find(() -> (Listing) catalog.getProduct(listingId))
                .or(() -> find(() -> catalog.getService(listingId)))
                .orElseThrow(() -> new IllegalArgumentException("Listing not found: " + listingId));
        requireAvailable(listing);
    }

    private Product product(String productId) {
        return find(() -> catalog.getProduct(productId))
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
    }

    private void requireAvailable(Listing listing) {
        if (listing.getStatus() != ListingStatus.ACTIVE)
            throw new IllegalArgumentException("Listing is not active: " + listing.getId());
        if (suppliers.getSupplier(listing.getSupplierId()).isSuspended())
            throw new IllegalArgumentException("Supplier is suspended: " + listing.getSupplierId());
    }

    private <T> Optional<T> find(Supplier<T> lookup) {
        try {
            return Optional.of(lookup.get());
        } catch (NotFoundException e) {
            return Optional.empty();
        }
    }
}
