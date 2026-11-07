package com.tecnolink.tecnolink.shared.domain.events;

import com.tecnolink.tecnolink.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

public interface DomainEventDispatcher {
    void dispatch(AbstractDomainAggregateRoot<?> aggregate);
}
