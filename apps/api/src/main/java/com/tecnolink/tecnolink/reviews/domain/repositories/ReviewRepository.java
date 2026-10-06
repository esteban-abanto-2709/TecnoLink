package com.tecnolink.tecnolink.reviews.domain.repositories;

import com.tecnolink.tecnolink.reviews.domain.model.aggregates.Review;

import java.util.Optional;

public interface ReviewRepository {
    Optional<Review> findById(Long id);
    Review save(Review review);
}
