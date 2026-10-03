package com.tecnolink.tecnolink.suppliers.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.suppliers.domain.model.aggregates.Supplier;
import com.tecnolink.tecnolink.suppliers.domain.repositories.SupplierRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemorySupplierRepository implements SupplierRepository {

    private final Map<String, Supplier> store = new LinkedHashMap<>();

    public InMemorySupplierRepository() {
        save(new Supplier("techperu", "Importaciones TechPerú", "20553417802", "(01) 562 4471", "San Miguel",
                "Importador de equipos de cómputo con tienda física y garantía propia de 12 meses.", 2016, true));
        save(new Supplier("compuzone", "CompuZone", "20481936275", "(01) 428 9013", "Cercado de Lima",
                "Venta de componentes y armado de equipos a medida en la galería Wilson.", 2011, true));
        save(new Supplier("digitalstore", "Digital Store Perú", "20604158293", "987 314 220", "Miraflores",
                "Celulares y accesorios liberados, con planes de cambio y reparación.", 2019, false));
        save(new Supplier("redesandinas", "Redes Andinas", "20512873604", "(01) 476 8852", "San Borja",
                "Cableado estructurado, cámaras y redes para oficinas y locales comerciales.", 2014, true));
        save(new Supplier("soportelima", "Soporte Lima", "10457821936", "961 208 447", "Santiago de Surco",
                "Servicio técnico a domicilio para equipos de cómputo y periféricos.", 2020, false));
    }

    @Override
    public Supplier save(Supplier supplier) {
        store.put(supplier.getId(), supplier);
        return supplier;
    }

    @Override
    public List<Supplier> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Supplier> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
