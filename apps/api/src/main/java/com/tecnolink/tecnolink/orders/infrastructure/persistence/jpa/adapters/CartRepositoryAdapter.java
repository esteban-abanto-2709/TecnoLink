package com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.adapters;

import com.tecnolink.tecnolink.orders.domain.model.aggregates.Cart;
import com.tecnolink.tecnolink.orders.domain.repositories.CartRepository;
import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.mappers.CartPersistenceMapper;
import com.tecnolink.tecnolink.orders.infrastructure.persistence.jpa.repositories.CartJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CartRepositoryAdapter implements CartRepository {

    private final CartJpaRepository jpaRepository;

    public CartRepositoryAdapter(CartJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return jpaRepository.findById(id).map(CartPersistenceMapper::toDomain);
    }

    @Override
    public Cart save(Cart cart) {
        return CartPersistenceMapper.toDomain(jpaRepository.save(CartPersistenceMapper.toEntity(cart)));
    }
}
