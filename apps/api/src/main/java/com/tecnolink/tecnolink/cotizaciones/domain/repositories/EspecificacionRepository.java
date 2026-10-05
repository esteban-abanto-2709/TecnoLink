package com.tecnolink.tecnolink.cotizaciones.domain.repositories;

import com.tecnolink.tecnolink.cotizaciones.domain.entities.Especificacion;

import java.util.Collection;
import java.util.List;

public interface EspecificacionRepository {
    List<Especificacion> findByProductoIds(Collection<String> productoIds);
}
