package com.tecnolink.tecnolink.catalogo.domain.repositories;

import com.tecnolink.tecnolink.catalogo.domain.aggregates.Publicacion;

import java.util.List;
import java.util.Optional;

public interface PublicacionRepository {
    Publicacion save(Publicacion publicacion);
    Optional<Publicacion> findById(String idPublicacion);
    List<Publicacion> findAll();
    List<Publicacion> findByCategoriaId(String categoriaId);
    List<Publicacion> findByProveedorId(String proveedorId);
}
