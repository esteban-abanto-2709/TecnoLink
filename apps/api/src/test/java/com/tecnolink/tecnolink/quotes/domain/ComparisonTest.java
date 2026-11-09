package com.tecnolink.tecnolink.quotes.domain;

import com.tecnolink.tecnolink.quotes.domain.aggregates.Comparison;
import com.tecnolink.tecnolink.quotes.domain.aggregates.Specification;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class ComparisonTest {
    private static final LocalDate TODAY = LocalDate.of(2026, 11, 9);

    private static Comparison of(String... productIds) {
        return new Comparison("client", List.of(productIds), TODAY);
    }

    @Test
    void rejectsRepeatedProducts() {
        Comparison comparison = of("a");

        assertThatIllegalStateException().isThrownBy(() -> comparison.addProduct("a"));
    }

    @Test
    void allowsUpToFourProducts() {
        Comparison comparison = of("a", "b", "c", "d");

        assertThatIllegalStateException().isThrownBy(() -> comparison.addProduct("e"));
    }

    @Test
    void cannotRemoveAProductThatIsNotThere() {
        Comparison comparison = of("a");

        assertThatIllegalArgumentException().isThrownBy(() -> comparison.removeProduct("b"));
    }

    @Test
    void tableNeedsAtLeastTwoProducts() {
        Comparison comparison = of("a");

        assertThatIllegalStateException().isThrownBy(() -> comparison.buildTable(List.of()));
    }

    @Test
    void tableFollowsTheProductOrderAndFillsMissingValues() {
        var table = of("a", "b").buildTable(List.of(
                new Specification("b", "RAM", "8", "GB"),
                new Specification("a", "RAM", "16", "GB"),
                new Specification("a", "CPU", "R5", null)));

        assertThat(table).containsEntry("RAM", List.of("16 GB", "8 GB")).containsEntry("CPU", List.of("R5", "-"));
    }
}
