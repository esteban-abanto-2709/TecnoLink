package com.tecnolink.tecnolink.catalog.domain.events;

import java.time.Instant;

public record CategoryCreatedEvent(
        String categoryId,
        String categoryName,
        Instant occurredAt
) {

    public CategoryCreatedEvent(
            String categoryId,
            String categoryName
    ) {
        this(
                categoryId,
                categoryName,
                Instant.now()
        );
    }
}