package com.tecnolink.tecnolink.quotes.interfaces.rest.mappers;

import com.tecnolink.tecnolink.quotes.application.commands.RequestQuoteCommand;
import com.tecnolink.tecnolink.quotes.interfaces.rest.resources.QuoteRequestCreateRequest;

public final class QuoteRequestRestMapper {
    private QuoteRequestRestMapper() {
    }

    public static RequestQuoteCommand toCommand(QuoteRequestCreateRequest r) {
        return new RequestQuoteCommand(r.clientId(), r.listingId(), r.quantity(), r.requirement());
    }
}
