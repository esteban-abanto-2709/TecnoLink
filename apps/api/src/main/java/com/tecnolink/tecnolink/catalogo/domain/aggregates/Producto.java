package com.tecnolink.tecnolink.catalogo.domain.aggregates;

import com.tecnolink.tecnolink.catalogo.domain.valueobjects.EstadoPublicacion;

import java.time.LocalDate;
import java.util.List;

public class Producto extends Publicacion {
    private final String marca;
    private final String modelo;
    private final int stock;

    public Producto(String idPublicacion, String proveedorId, String categoriaId, String titulo, String descripcion,
                    double precio, List<String> imagenes, LocalDate fechaPublicacion, EstadoPublicacion estado,
                    String marca, String modelo, int stock) {
        super(idPublicacion, proveedorId, categoriaId, titulo, descripcion, precio, imagenes, fechaPublicacion, estado);
        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("Product brand is required");
        }
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Product model is required");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }
        this.marca = marca;
        this.modelo = modelo;
        this.stock = stock;
    }

    public boolean hayStock(int cantidad) {
        return cantidad <= stock;
    }

    @Override
    protected String detalleEspecifico() {
        return String.format("Marca: %s · Modelo: %s · Stock: %d", marca, modelo, stock);
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getStock() {
        return stock;
    }
}
