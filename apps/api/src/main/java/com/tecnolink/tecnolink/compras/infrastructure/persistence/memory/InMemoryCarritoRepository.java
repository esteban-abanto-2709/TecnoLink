package com.tecnolink.tecnolink.compras.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.compras.domain.aggregates.Carrito;
import com.tecnolink.tecnolink.compras.domain.repositories.CarritoRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCarritoRepository implements CarritoRepository {

    private final Map<String, Carrito> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(2);

    public InMemoryCarritoRepository() {
        Carrito carrito = new Carrito(1L, "demo-client");
        carrito.agregarItem("samsung-galaxy-a55", 1, 1599);
        carrito.agregarItem("kingston-nv2-1tb", 2, 299);
        save(carrito);
    }

    @Override
    public Carrito save(Carrito carrito) {
        store.put(carrito.getClienteId(), carrito);
        return carrito;
    }

    @Override
    public Optional<Carrito> findByClienteId(String clienteId) {
        return Optional.ofNullable(store.get(clienteId));
    }

    @Override
    public Long nextId() {
        return sequence.getAndIncrement();
    }
}
