package com.tecnolink.tecnolink.quotes.domain;

import com.tecnolink.tecnolink.quotes.domain.valueobjects.QuoteAmounts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class QuoteAmountsTest {
    @Test
    void calculatesIgvAndTotal() {
        assertThat(QuoteAmounts.ofSubtotal(100)).isEqualTo(new QuoteAmounts(100, 18, 118));
    }

    @Test
    void roundsHalfUpWithoutBinaryErrors() {
        QuoteAmounts amounts = QuoteAmounts.ofSubtotal(1.25);

        assertThat(amounts.igv()).isEqualTo(0.23);
        assertThat(amounts.total()).isEqualTo(1.48);
    }

    @Test
    void acceptsTheLimits() {
        assertThat(QuoteAmounts.ofSubtotal(0.01).total()).isEqualTo(0.01);
        assertThat(QuoteAmounts.ofSubtotal(QuoteAmounts.MAX_SUBTOTAL).total()).isEqualTo(11_799_999.99);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, 1.001, 10_000_000, Double.NaN, Double.POSITIVE_INFINITY})
    void rejectsInvalidSubtotals(double subtotal) {
        assertThatIllegalArgumentException().isThrownBy(() -> QuoteAmounts.ofSubtotal(subtotal));
    }

    @Test
    void rejectsATotalThatDoesNotAddUp() {
        assertThatIllegalArgumentException().isThrownBy(() -> new QuoteAmounts(100, 18, 119));
    }
}
