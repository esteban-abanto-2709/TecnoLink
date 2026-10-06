package com.tecnolink.tecnolink.loyalty.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.loyalty.domain.model.aggregates.PointsMovement;
import com.tecnolink.tecnolink.loyalty.domain.repositories.PointsMovementRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryPointsMovementRepository implements PointsMovementRepository {

    private final Map<String, PointsMovement> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(5);

    public InMemoryPointsMovementRepository() {
        save(new PointsMovement("pts-001", "demo-client", LocalDate.of(2026, 8, 18),
                "Compra ord-001", 74, null));
        save(new PointsMovement("pts-002", "demo-client", LocalDate.of(2026, 7, 20),
                "Canje: Envío gratis en tu próxima compra", -150, "free-shipping"));
        save(new PointsMovement("pts-003", "demo-client", LocalDate.of(2026, 7, 14),
                "Compra ord-002", 217, null));
        save(new PointsMovement("pts-004", "demo-client", LocalDate.of(2026, 6, 1),
                "Bono por reseña publicada", 50, null));
    }

    @Override
    public PointsMovement save(PointsMovement movement) {
        store.put(movement.getId(), movement);
        return movement;
    }

    @Override
    public String nextId() {
        return String.format("pts-%03d", sequence.getAndIncrement());
    }

    @Override
    public List<PointsMovement> findByUserId(String userId) {
        List<PointsMovement> movements = new ArrayList<>();
        for (PointsMovement movement : store.values()) {
            if (movement.getUserId().equals(userId)) movements.add(movement);
        }
        return movements;
    }
}
