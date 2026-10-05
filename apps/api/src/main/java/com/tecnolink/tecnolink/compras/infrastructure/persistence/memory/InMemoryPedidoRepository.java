package com.tecnolink.tecnolink.compras.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.compras.domain.aggregates.Pedido;
import com.tecnolink.tecnolink.compras.domain.entities.DetallePedido;
import com.tecnolink.tecnolink.compras.domain.repositories.PedidoRepository;
import com.tecnolink.tecnolink.compras.domain.valueobjects.EstadoPedido;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPedidoRepository implements PedidoRepository {

    private final Map<Long, Pedido> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(3);

    public InMemoryPedidoRepository() {
        save(new Pedido(1L, "demo-client", LocalDate.of(2026, 8, 18),
                List.of(new DetallePedido("lg-ultragear-24", 1, 749)),
                EstadoPedido.CONFIRMADO, "Tarjeta simulada"));
        save(new Pedido(2L, "demo-client", LocalDate.of(2026, 7, 14),
                List.of(new DetallePedido("lenovo-ideapad-3", 1, 1899),
                        new DetallePedido("kingston-fury-16", 1, 279)),
                EstadoPedido.CONFIRMADO, "Yape simulado"));
    }

    @Override
    public Pedido save(Pedido pedido) {
        store.put(pedido.getIdPedido(), pedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> findById(Long idPedido) {
        return Optional.ofNullable(store.get(idPedido));
    }

    @Override
    public List<Pedido> findByClienteId(String clienteId) {
        List<Pedido> pedidos = new ArrayList<>();
        for (Pedido pedido : store.values()) {
            if (pedido.getClienteId().equals(clienteId)) pedidos.add(pedido);
        }
        return pedidos;
    }

    @Override
    public Long nextId() {
        return sequence.getAndIncrement();
    }
}
