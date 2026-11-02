package com.tecnolink.tecnolink.shared.domain.model.aggregates;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractDomainAggregateRoot<T extends AbstractDomainAggregateRoot<T>> {
    private final List<Object> pendingEvents = new ArrayList<>();

    protected final void registerDomainEvent(Object event) {
        pendingEvents.add(Objects.requireNonNull(event, "event"));
    }

    public final List<Object> pullDomainEvents() {
        List<Object> events = List.copyOf(pendingEvents);
        pendingEvents.clear();
        return events;
    }
}
