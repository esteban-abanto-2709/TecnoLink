package com.tecnolink.tecnolink.compras.domain.repositories;

import com.tecnolink.tecnolink.compras.domain.aggregates.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    Pedido save(Pedido pedido);
    Optional<Pedido> findById(Long idPedido);
    List<Pedido> findByClienteId(String clienteId);
    Long nextId();
}
