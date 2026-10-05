package com.tecnolink.tecnolink.cotizaciones.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.cotizaciones.domain.entities.Especificacion;
import com.tecnolink.tecnolink.cotizaciones.domain.repositories.EspecificacionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class InMemoryEspecificacionRepository implements EspecificacionRepository {

    private final List<Especificacion> store = new ArrayList<>();

    public InMemoryEspecificacionRepository() {
        laptop("lenovo-ideapad-3", "Intel Core i5-1235U", "8", "512", "15.6", "7");
        laptop("hp-pavilion-14", "AMD Ryzen 5 7530U", "16", "512", "14", "9");
        laptop("asus-tuf-a15", "AMD Ryzen 7 7435HS", "16", "1024", "15.6", "5");
        laptop("acer-aspire-3", "Intel Core i3-1215U", "8", "256", "15.6", "6");
    }

    private void laptop(String productoId, String procesador, String ram, String almacenamiento,
                        String pantalla, String bateria) {
        store.add(new Especificacion(productoId, "Procesador", procesador, null));
        store.add(new Especificacion(productoId, "RAM", ram, "GB"));
        store.add(new Especificacion(productoId, "Almacenamiento", almacenamiento, "GB SSD"));
        store.add(new Especificacion(productoId, "Pantalla", pantalla, "pulgadas"));
        store.add(new Especificacion(productoId, "Batería", bateria, "horas"));
    }

    @Override
    public List<Especificacion> findByProductoIds(Collection<String> productoIds) {
        List<Especificacion> especificaciones = new ArrayList<>();
        for (Especificacion especificacion : store) {
            if (productoIds.contains(especificacion.getProductoId())) especificaciones.add(especificacion);
        }
        return especificaciones;
    }
}
