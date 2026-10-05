package com.tecnolink.tecnolink.proveedores.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.proveedores.domain.aggregates.Proveedor;
import com.tecnolink.tecnolink.proveedores.domain.repositories.ProveedorRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryProveedorRepository implements ProveedorRepository {

    private final Map<String, Proveedor> store = new LinkedHashMap<>();

    public InMemoryProveedorRepository() {
        save(new Proveedor("techperu", "Jorge", "Salas Huamán", "ventas@techperu.pe", "demo1234",
                "(01) 562 4471", LocalDate.of(2026, 5, 10), true, "20553417802", "Importaciones TechPerú",
                "Lima - San Miguel",
                "Importador de equipos de cómputo con tienda física y garantía propia de 12 meses."));
        save(new Proveedor("compuzone", "Rosa", "Huerta Cárdenas", "contacto@compuzone.pe", "demo1234",
                "(01) 428 9013", LocalDate.of(2026, 5, 12), true, "20481936275", "CompuZone",
                "Lima - Cercado de Lima",
                "Venta de componentes y armado de equipos a medida en la galería Wilson."));
        save(new Proveedor("digitalstore", "Kevin", "Lozano Prado", "hola@digitalstore.pe", "demo1234",
                "987 314 220", LocalDate.of(2026, 6, 3), true, "20604158293", "Digital Store Perú",
                "Lima - Miraflores",
                "Celulares y accesorios liberados, con planes de cambio y reparación."));
        save(new Proveedor("redesandinas", "Miguel", "Quispe Ayala", "proyectos@redesandinas.pe", "demo1234",
                "(01) 476 8852", LocalDate.of(2026, 5, 18), true, "20512873604", "Redes Andinas",
                "Lima - San Borja",
                "Cableado estructurado, cámaras y redes para oficinas y locales comerciales."));
        save(new Proveedor("soportelima", "Luis", "Fernández Castro", "luis@soportelima.pe", "demo1234",
                "961 208 447", LocalDate.of(2026, 7, 8), true, "10457821936", "Soporte Lima",
                "Lima - Santiago de Surco",
                "Servicio técnico a domicilio para equipos de cómputo y periféricos."));
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        store.put(proveedor.getIdUsuario(), proveedor);
        return proveedor;
    }

    @Override
    public Optional<Proveedor> findById(String idProveedor) {
        return Optional.ofNullable(store.get(idProveedor));
    }

    @Override
    public List<Proveedor> findAll() {
        return new ArrayList<>(store.values());
    }
}
