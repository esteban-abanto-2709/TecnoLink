package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.Benefit;
import com.tecnolink.tecnolink.loyalty.domain.repositories.BenefitRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryBenefitRepository implements BenefitRepository {

    private final Map<String, Benefit> store = new LinkedHashMap<>();

    public InMemoryBenefitRepository() {
        save(new Benefit("priority-quotes", "Respuesta prioritaria a tus cotizaciones",
                "Tus solicitudes se muestran primero en la bandeja del proveedor durante un mes.", 150));
        save(new Benefit("free-shipping", "Envío gratis en tu próxima compra",
                "Cubre el envío dentro de Lima Metropolitana, sin monto mínimo.", 150));
        save(new Benefit("coupon-50", "Cupón de S/ 50 de descuento",
                "Se aplica sobre compras mayores a S/ 300.", 500));
        save(new Benefit("extended-warranty", "Garantía extendida de 6 meses",
                "Extiende la garantía del proveedor en un equipo que ya compraste.", 800));
        save(new Benefit("maintenance", "Mantenimiento preventivo gratis",
                "Una visita técnica a domicilio sin costo, coordinada contigo.", 1200));
    }

    private void save(Benefit benefit) {
        store.put(benefit.getId(), benefit);
    }

    @Override
    public List<Benefit> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Benefit> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
