package com.tecnolink.tecnolink.quotes.interfaces.rest.resources;

import java.util.List;

public record ComparisonCreateRequest(String clientId, List<String> productIds) {}
