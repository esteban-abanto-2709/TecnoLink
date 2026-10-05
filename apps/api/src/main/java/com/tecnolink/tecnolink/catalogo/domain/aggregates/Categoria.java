package com.tecnolink.tecnolink.catalogo.domain.aggregates;

public class Categoria {
    private final String idCategoria;
    private String nombre;
    private String descripcion;

    public Categoria(String idCategoria, String nombre, String descripcion) {
        if (idCategoria == null || idCategoria.isBlank()) {
            throw new IllegalArgumentException("Category id is required");
        }
        this.idCategoria = idCategoria;
        renombrar(nombre, descripcion);
    }

    public void renombrar(String nombre, String descripcion) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
        this.nombre = nombre;
        this.descripcion = descripcion == null ? "" : descripcion;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
