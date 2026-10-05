package com.tecnolink.tecnolink.proveedores.domain.repositories;

import com.tecnolink.tecnolink.proveedores.domain.aggregates.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository {
    Proveedor save(Proveedor proveedor);
    Optional<Proveedor> findById(String idProveedor);
    List<Proveedor> findAll();
}
