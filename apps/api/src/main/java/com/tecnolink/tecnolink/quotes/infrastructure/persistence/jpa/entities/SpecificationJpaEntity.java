package com.tecnolink.tecnolink.quotes.infrastructure.persistence.jpa.entities;

import com.tecnolink.tecnolink.catalog.infrastructure.persistence.jpa.entities.ProductJpaEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specifications", uniqueConstraints = {
        @UniqueConstraint(name = "uk_specification_product_name", columnNames = {"product_id", "name"})
})
public class SpecificationJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductJpaEntity product;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(name = "spec_value", nullable = false, length = 120)
    private String value;

    @Column(length = 20)
    private String unit;
}
