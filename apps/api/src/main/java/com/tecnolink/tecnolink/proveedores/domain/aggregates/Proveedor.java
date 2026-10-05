package com.tecnolink.tecnolink.proveedores.domain.aggregates;

import com.tecnolink.tecnolink.usuarios.domain.aggregates.Usuario;

import java.time.LocalDate;
import java.util.List;

public class Proveedor extends Usuario {
    private final String ruc;
    private String razonSocial;
    private String region;
    private String descripcion;
    private double reputacion;

    public Proveedor(String idUsuario, String nombres, String apellidos, String correo, String contrasena,
                     String telefono, LocalDate fechaRegistro, boolean activo, String ruc, String razonSocial,
                     String region, String descripcion) {
        super(idUsuario, nombres, apellidos, correo, contrasena, telefono, fechaRegistro, activo);
        if (ruc == null || !ruc.matches("\\d{11}")) {
            throw new IllegalArgumentException("Supplier RUC must have 11 digits");
        }
        this.ruc = ruc;
        actualizarDatosEmpresa(razonSocial, region, descripcion);
    }

    public void actualizarDatosEmpresa(String razonSocial, String region, String descripcion) {
        if (razonSocial == null || razonSocial.isBlank()) {
            throw new IllegalArgumentException("Supplier business name is required");
        }
        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Supplier region is required");
        }
        this.razonSocial = razonSocial;
        this.region = region;
        this.descripcion = descripcion == null ? "" : descripcion;
    }

    public double calcularReputacion(List<Resena> resenasDeSusPublicaciones) {
        if (resenasDeSusPublicaciones.isEmpty()) {
            reputacion = 0;
            return reputacion;
        }
        double suma = 0;
        for (Resena resena : resenasDeSusPublicaciones) suma += resena.getCalificacion();
        reputacion = Math.round(suma / resenasDeSusPublicaciones.size() * 10) / 10.0;
        return reputacion;
    }

    public String getRuc() {
        return ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getRegion() {
        return region;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getReputacion() {
        return reputacion;
    }
}
