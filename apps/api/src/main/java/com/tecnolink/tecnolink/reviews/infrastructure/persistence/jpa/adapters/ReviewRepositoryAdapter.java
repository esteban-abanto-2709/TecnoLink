package com.tecnolink.tecnolink.reviews.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.reviews.domain.model.aggregates.Review;
import com.tecnolink.tecnolink.reviews.domain.repositories.ReviewRepository;
import com.tecnolink.tecnolink.reviews.infrastructure.persistence.jpa.mappers.ReviewPersistenceMapper;
import com.tecnolink.tecnolink.reviews.infrastructure.persistence.jpa.repositories.ReviewJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ReviewRepositoryAdapter implements ReviewRepository {

    private final ReviewJpaRepository jpaRepository;

    public ReviewRepositoryAdapter(ReviewJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Review> findById(Long id) {
        return jpaRepository.findById(id).map(ReviewPersistenceMapper::toDomain);
    }

    @Override
    public Review save(Review review) {
        return ReviewPersistenceMapper.toDomain(jpaRepository.save(ReviewPersistenceMapper.toEntity(review)));
    }
}
