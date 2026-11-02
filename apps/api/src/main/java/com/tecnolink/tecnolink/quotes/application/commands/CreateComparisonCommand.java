package com.tecnolink.tecnolink.quotes.application.commands;

import java.util.List;

public record CreateComparisonCommand(String clientId, List<String> productIds) {}
