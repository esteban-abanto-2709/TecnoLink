package com.tecnolink.tecnolink.quotes.domain.events;

import java.time.Instant;

public record QuoteAnsweredEvent(Long quoteRequestId, String clientId,
                                 double total, Instant occurredAt) {
}
