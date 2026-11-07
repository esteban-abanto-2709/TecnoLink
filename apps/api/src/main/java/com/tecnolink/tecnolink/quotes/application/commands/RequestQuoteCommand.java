package com.tecnolink.tecnolink.quotes.application.commands;
public record RequestQuoteCommand(String clientId, String listingId, int quantity, String requirement) {}
