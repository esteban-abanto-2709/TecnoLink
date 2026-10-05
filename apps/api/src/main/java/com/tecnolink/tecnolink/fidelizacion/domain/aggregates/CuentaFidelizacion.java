package com.tecnolink.tecnolink.fidelizacion.domain.aggregates;

import com.tecnolink.tecnolink.fidelizacion.domain.entities.MovimientoPuntos;
import com.tecnolink.tecnolink.fidelizacion.domain.valueobjects.NivelFidelizacion;
import com.tecnolink.tecnolink.fidelizacion.domain.valueobjects.TipoMovimiento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CuentaFidelizacion {
    private final String clienteId;
    private int puntosAcumulados;
    private NivelFidelizacion nivel;
    private final List<MovimientoPuntos> movimientos = new ArrayList<>();

    public CuentaFidelizacion(String clienteId) {
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("Account client is required");
        }
        this.clienteId = clienteId;
        this.puntosAcumulados = 0;
        this.nivel = NivelFidelizacion.BRONCE;
    }

    public void acumular(int puntos, String motivo, LocalDate fecha) {
        registrar(new MovimientoPuntos(fecha, TipoMovimiento.ACUMULACION, puntos, motivo));
    }

    public boolean canjear(int puntos, String motivo, LocalDate fecha) {
        if (puntos > puntosAcumulados) return false;
        registrar(new MovimientoPuntos(fecha, TipoMovimiento.CANJE, puntos, motivo));
        return true;
    }

    private void registrar(MovimientoPuntos movimiento) {
        movimientos.add(movimiento);
        puntosAcumulados += movimiento.efectoEnSaldo();
        nivel = NivelFidelizacion.segun(puntosAcumulados);
    }

    public String getClienteId() {
        return clienteId;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public NivelFidelizacion getNivel() {
        return nivel;
    }

    public List<MovimientoPuntos> getMovimientos() {
        return Collections.unmodifiableList(movimientos);
    }
}
