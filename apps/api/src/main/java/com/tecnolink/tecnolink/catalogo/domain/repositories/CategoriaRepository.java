package com.tecnolink.tecnolink.catalogo.domain.repositories;

import com.tecnolink.tecnolink.catalogo.domain.aggregates.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {
    Categoria save(Categoria categoria);
    Optional<Categoria> findById(String idCategoria);
    List<Categoria> findAll();
}
