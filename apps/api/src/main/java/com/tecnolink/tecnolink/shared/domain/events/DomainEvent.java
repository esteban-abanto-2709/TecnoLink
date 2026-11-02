package com.tecnolink.tecnolink.shared.domain.events;

import java.time.Instant;

public interface DomainEvent {

    Instant occurredAt();
}