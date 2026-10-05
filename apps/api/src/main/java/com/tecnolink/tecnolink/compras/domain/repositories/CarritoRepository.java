package com.tecnolink.tecnolink.compras.domain.repositories;

import com.tecnolink.tecnolink.compras.domain.aggregates.Carrito;

import java.util.Optional;

public interface CarritoRepository {
    Carrito save(Carrito carrito);
    Optional<Carrito> findByClienteId(String clienteId);
    Long nextId();
}
