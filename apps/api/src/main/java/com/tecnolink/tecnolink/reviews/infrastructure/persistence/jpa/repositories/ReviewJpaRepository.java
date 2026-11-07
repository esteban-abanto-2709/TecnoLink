package com.tecnolink.tecnolink.reviews.infrastructure.persistence.jpa.repositories;

import com.tecnolink.tecnolink.reviews.infrastructure.persistence.jpa.entities.ReviewJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<ReviewJpaEntity, Long> {
}
