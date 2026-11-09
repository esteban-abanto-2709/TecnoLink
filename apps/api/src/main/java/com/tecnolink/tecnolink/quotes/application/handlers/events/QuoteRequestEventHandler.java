package com.tecnolink.tecnolink.quotes.application.handlers.events;

import com.tecnolink.tecnolink.quotes.domain.events.QuoteAnsweredEvent;
import com.tecnolink.tecnolink.quotes.domain.events.QuoteRequestedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class QuoteRequestEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteRequestEventHandler.class);

    @EventListener
    public void handle(QuoteRequestedEvent event) {
        LOGGER.info("Quote requested. ID: {}, Client: {}, Listing: {}",
                event.quoteRequestId(), event.clientId(), event.listingId());
    }

    @EventListener
    public void handle(QuoteAnsweredEvent event) {
        LOGGER.info("Quote answered. ID: {}, Client: {}, Total: {}",
                event.quoteRequestId(), event.clientId(), event.total());
    }
}
