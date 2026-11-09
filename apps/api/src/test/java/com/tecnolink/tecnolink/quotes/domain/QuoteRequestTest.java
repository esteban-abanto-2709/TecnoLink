package com.tecnolink.tecnolink.quotes.domain;

import com.tecnolink.tecnolink.quotes.domain.aggregates.QuoteRequest;
import com.tecnolink.tecnolink.quotes.domain.events.QuoteAnsweredEvent;
import com.tecnolink.tecnolink.quotes.domain.events.QuoteRequestedEvent;
import com.tecnolink.tecnolink.quotes.domain.valueobjects.QuoteRequestStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class QuoteRequestTest {
    private static final LocalDate TODAY = LocalDate.of(2026, 11, 9);

    private static QuoteRequest saved() {
        return new QuoteRequest(1L, "client", "listing", TODAY, 2, "need", QuoteRequestStatus.SENT, null);
    }

    private static QuoteRequest answered() {
        QuoteRequest request = saved();
        request.answer(7, 100, "conditions", "notes", TODAY);
        return request;
    }

    @Test
    void startsAsSentWithTheGivenDate() {
        QuoteRequest request = new QuoteRequest("client", "listing", 2, "need", TODAY);

        assertThat(request.getStatus()).isEqualTo(QuoteRequestStatus.SENT);
        assertThat(request.getRequestedOn()).isEqualTo(TODAY);
    }

    @Test
    void rejectsAQuantityBelowOne() {
        assertThatIllegalArgumentException().isThrownBy(() -> new QuoteRequest("client", "listing", 0, "need", TODAY));
    }

    @Test
    void announcesOnlyOnceItHasAnId() {
        assertThatIllegalStateException().isThrownBy(new QuoteRequest("client", "listing", 2, "need", TODAY)::announceRequested);

        QuoteRequest request = saved();
        request.announceRequested();

        assertThat(request.pullDomainEvents()).singleElement()
                .isInstanceOfSatisfying(QuoteRequestedEvent.class, event -> assertThat(event.quoteRequestId()).isEqualTo(1L));
    }

    @Test
    void answeringCreatesTheQuoteAndRegistersTheEvent() {
        QuoteRequest request = answered();

        assertThat(request.getStatus()).isEqualTo(QuoteRequestStatus.ANSWERED);
        assertThat(request.getQuote().getTotal()).isEqualTo(118);
        assertThat(request.getQuote().getNotes()).isEqualTo("notes");
        assertThat(request.getQuote().validUntil()).isEqualTo(TODAY.plusDays(7));
        assertThat(request.pullDomainEvents()).singleElement().isInstanceOf(QuoteAnsweredEvent.class);
    }

    @Test
    void blankNotesAreStoredAsNull() {
        QuoteRequest request = saved();
        request.answer(7, 100, "conditions", "  ", TODAY);

        assertThat(request.getQuote().getNotes()).isNull();
    }

    @Test
    void cannotBeAnsweredTwice() {
        QuoteRequest request = answered();

        assertThatIllegalStateException().isThrownBy(() -> request.answer(7, 100, "conditions", null, TODAY));
    }

    @Test
    void statusIsDerivedFromTheDate() {
        QuoteRequest request = answered();

        assertThat(request.statusOn(TODAY.plusDays(7))).isEqualTo(QuoteRequestStatus.ANSWERED);
        assertThat(request.statusOn(TODAY.plusDays(8))).isEqualTo(QuoteRequestStatus.EXPIRED);
        assertThat(request.getStatus()).isEqualTo(QuoteRequestStatus.ANSWERED);
    }

    @Test
    void aSentRequestNeverExpiresByDate() {
        assertThat(saved().statusOn(TODAY.plusYears(1))).isEqualTo(QuoteRequestStatus.SENT);
    }

    @Test
    void expiresOnlyAfterTheValidity() {
        QuoteRequest request = answered();

        assertThatIllegalStateException().isThrownBy(() -> request.expire(TODAY.plusDays(7)));
        request.expire(TODAY.plusDays(8));
        assertThat(request.getStatus()).isEqualTo(QuoteRequestStatus.EXPIRED);
    }

    @Test
    void onlyAnAnsweredRequestCanExpire() {
        assertThatIllegalStateException().isThrownBy(() -> saved().expire(TODAY.plusDays(30)));
    }
}
