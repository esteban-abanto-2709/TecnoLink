package com.tecnolink.tecnolink.proveedores.domain.aggregates;

import java.util.List;

public class Proveedor {
    private final String idProveedor;
    private final String ruc;
    private String razonSocial;
    private String region;
    private String descripcion;
    private double reputacion;

    public Proveedor(String idProveedor, String ruc, String razonSocial, String region, String descripcion) {
        if (idProveedor == null || idProveedor.isBlank()) {
            throw new IllegalArgumentException("Supplier id is required");
        }
        if (ruc == null || !ruc.matches("\\d{11}")) {
            throw new IllegalArgumentException("Supplier RUC must have 11 digits");
        }
        this.idProveedor = idProveedor;
        this.ruc = ruc;
        actualizarPerfil(razonSocial, region, descripcion);
    }

    public void actualizarPerfil(String razonSocial, String region, String descripcion) {
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

    public String getIdProveedor() {
        return idProveedor;
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
