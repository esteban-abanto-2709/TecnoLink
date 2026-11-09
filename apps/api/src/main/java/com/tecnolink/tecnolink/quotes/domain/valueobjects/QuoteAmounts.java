package com.tecnolink.tecnolink.quotes.domain.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record QuoteAmounts(double subtotal, double igv, double total) {
    public static final double MAX_SUBTOTAL = 9_999_999.99;
    private static final BigDecimal IGV_RATE = new BigDecimal("0.18");
    private static final int DECIMALS = 2;

    public QuoteAmounts {
        requireValid(subtotal);
        if (exact(subtotal).add(exact(igv)).compareTo(exact(total)) != 0)
            throw new IllegalArgumentException("Total must be the subtotal plus the IGV");
    }

    public static QuoteAmounts ofSubtotal(double subtotal) {
        requireValid(subtotal);
        BigDecimal igv = exact(subtotal).multiply(IGV_RATE).setScale(DECIMALS, RoundingMode.HALF_UP);
        return new QuoteAmounts(subtotal, igv.doubleValue(), exact(subtotal).add(igv).doubleValue());
    }

    private static void requireValid(double subtotal) {
        if (!(subtotal > 0)) throw new IllegalArgumentException("Subtotal must be greater than 0");
        if (subtotal > MAX_SUBTOTAL) throw new IllegalArgumentException("Subtotal cannot exceed 9999999.99");
        if (exact(subtotal).stripTrailingZeros().scale() > DECIMALS)
            throw new IllegalArgumentException("Subtotal allows up to 2 decimals");
    }

    private static BigDecimal exact(double value) {
        return BigDecimal.valueOf(value);
    }
}
