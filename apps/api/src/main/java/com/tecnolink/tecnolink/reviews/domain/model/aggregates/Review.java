package com.tecnolink.tecnolink.reviews.domain.model.aggregates;

import java.time.LocalDate;

public class Review {
    private final Long id;
    private final String clientId;
    private final String targetId;
    private final int rating;
    private final String comment;
    private final LocalDate date;

    public Review(Long id, String clientId, String targetId, int rating, String comment, LocalDate date) {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Review client is required");
        }
        if (targetId == null || targetId.isBlank()) {
            throw new IllegalArgumentException("Review target is required");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Review rating must be between 1 and 5");
        }
        if (date == null) {
            throw new IllegalArgumentException("Review date is required");
        }
        this.id = id;
        this.clientId = clientId;
        this.targetId = targetId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getTargetId() {
        return targetId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getDate() {
        return date;
    }
}
