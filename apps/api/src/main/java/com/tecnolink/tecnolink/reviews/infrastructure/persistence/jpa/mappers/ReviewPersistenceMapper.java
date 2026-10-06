package com.tecnolink.tecnolink.reviews.infrastructure.persistence.jpa.mappers;

import com.tecnolink.tecnolink.reviews.domain.model.aggregates.Review;
import com.tecnolink.tecnolink.reviews.infrastructure.persistence.jpa.entities.ReviewJpaEntity;

public final class ReviewPersistenceMapper {

    private ReviewPersistenceMapper() {
    }

    public static ReviewJpaEntity toEntity(Review review) {
        return new ReviewJpaEntity(review.getId(), review.getClientId(), review.getTargetId(), review.getRating(),
                review.getComment(), review.getDate());
    }

    public static Review toDomain(ReviewJpaEntity entity) {
        return new Review(entity.getId(), entity.getClientId(), entity.getTargetId(), entity.getRating(),
                entity.getComment(), entity.getDate());
    }
}
