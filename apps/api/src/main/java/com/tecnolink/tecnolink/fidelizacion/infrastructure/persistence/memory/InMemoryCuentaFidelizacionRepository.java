package com.tecnolink.tecnolink.fidelizacion.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.fidelizacion.domain.aggregates.CuentaFidelizacion;
import com.tecnolink.tecnolink.fidelizacion.domain.repositories.CuentaFidelizacionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryCuentaFidelizacionRepository implements CuentaFidelizacionRepository {

    private final Map<String, CuentaFidelizacion> store = new LinkedHashMap<>();

    public InMemoryCuentaFidelizacionRepository() {
        CuentaFidelizacion cuenta = new CuentaFidelizacion("demo-client");
        cuenta.acumular(50, "Reseña de Laptop Lenovo IdeaPad 3 15", LocalDate.of(2026, 6, 1));
        cuenta.acumular(217, "Compra del pedido 2", LocalDate.of(2026, 7, 14));
        cuenta.canjear(150, "Canje: envío gratis en tu próxima compra", LocalDate.of(2026, 7, 20));
        cuenta.acumular(74, "Compra del pedido 1", LocalDate.of(2026, 8, 18));
        save(cuenta);
    }

    @Override
    public CuentaFidelizacion save(CuentaFidelizacion cuenta) {
        store.put(cuenta.getClienteId(), cuenta);
        return cuenta;
    }

    @Override
    public Optional<CuentaFidelizacion> findByClienteId(String clienteId) {
        return Optional.ofNullable(store.get(clienteId));
    }
}
