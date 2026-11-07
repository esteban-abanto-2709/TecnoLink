package com.tecnolink.tecnolink.catalog.application.queries;

import java.util.List;

public record CompareProductsQuery(
        List<String> productIds
) {
}