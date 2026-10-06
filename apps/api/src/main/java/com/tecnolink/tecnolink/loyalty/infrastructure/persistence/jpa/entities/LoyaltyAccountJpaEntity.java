package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.iam.infrastructure.persistence.jpa.entities.ClientJpaEntity;
import com.tecnolink.tecnolink.loyalty.domain.model.enums.LoyaltyLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loyalty_accounts")
public class LoyaltyAccountJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private ClientJpaEntity client;

    @Column(name = "accumulated_points", nullable = false)
    private int accumulatedPoints;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoyaltyLevel level;

    protected LoyaltyAccountJpaEntity() {
    }

    public LoyaltyAccountJpaEntity(Long id, String userId, int accumulatedPoints, LoyaltyLevel level) {
        this.id = id;
        this.userId = userId;
        this.accumulatedPoints = accumulatedPoints;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }

    public LoyaltyLevel getLevel() {
        return level;
    }
}
