package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.UserJpaEntity;
import com.tecnolink.tecnolink.loyalty.domain.model.enums.MovementType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "points_movements")
public class PointsMovementJpaEntity {

    @Id
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "movement_date", nullable = false)
    private LocalDate date;

    private String description;

    @Column(nullable = false)
    private int points;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @Column(name = "benefit_id")
    private String benefitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "benefit_id", insertable = false, updatable = false)
    private BenefitJpaEntity benefit;

    protected PointsMovementJpaEntity() {
    }

    public PointsMovementJpaEntity(String id, String userId, LocalDate date, String description, int points,
                                   MovementType type, String benefitId) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.description = description;
        this.points = points;
        this.type = type;
        this.benefitId = benefitId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getPoints() {
        return points;
    }

    public MovementType getType() {
        return type;
    }

    public String getBenefitId() {
        return benefitId;
    }
}
