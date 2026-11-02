package com.tecnolink.tecnolink.shared.domain.events;

public interface DomainEventDispatcher {

    void dispatch(DomainEvent event);
}