package com.tecnolink.tecnolink.fidelizacion.domain.repositories;

import com.tecnolink.tecnolink.fidelizacion.domain.aggregates.CuentaFidelizacion;

import java.util.Optional;

public interface CuentaFidelizacionRepository {
    CuentaFidelizacion save(CuentaFidelizacion cuenta);
    Optional<CuentaFidelizacion> findByClienteId(String clienteId);
}
