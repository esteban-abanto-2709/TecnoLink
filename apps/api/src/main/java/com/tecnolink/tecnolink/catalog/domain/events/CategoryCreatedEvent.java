package com.tecnolink.tecnolink.catalog.domain.events;

import com.tecnolink.tecnolink.shared.domain.events.DomainEvent;

import java.time.Instant;

public record CategoryCreatedEvent(
        String categoryId,
        String categoryName,
        Instant occurredAt
) implements DomainEvent {

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