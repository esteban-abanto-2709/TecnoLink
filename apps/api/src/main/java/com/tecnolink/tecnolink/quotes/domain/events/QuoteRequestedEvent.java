package com.tecnolink.tecnolink.quotes.domain.events;

import java.time.Instant;

public record QuoteRequestedEvent(Long quoteRequestId, String clientId,
                                  String listingId, Instant occurredAt) {
}
