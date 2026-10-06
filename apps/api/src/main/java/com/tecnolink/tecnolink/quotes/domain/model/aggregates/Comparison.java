package com.tecnolink.tecnolink.quotes.domain.model.aggregates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comparison {
    private final Long id;
    private final String clientId;
    private final LocalDate date;
    private final List<String> productIds;

    public Comparison(Long id, String clientId, LocalDate date, List<String> productIds) {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Comparison client is required");
        }
        if (date == null) {
            throw new IllegalArgumentException("Comparison date is required");
        }
        if (productIds != null && productIds.size() > 4) {
            throw new IllegalArgumentException("A comparison can have at most 4 products");
        }
        this.id = id;
        this.clientId = clientId;
        this.date = date;
        this.productIds = new ArrayList<>(productIds == null ? List.of() : productIds);
    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<String> getProductIds() {
        return Collections.unmodifiableList(productIds);
    }
}
