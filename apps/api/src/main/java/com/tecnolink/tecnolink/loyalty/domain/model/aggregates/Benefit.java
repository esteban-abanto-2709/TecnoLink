package com.tecnolink.tecnolink.loyalty.domain.model.aggregates;

public class Benefit {
    private final String id;
    private final String name;
    private final String description;
    private final int cost;

    public Benefit(String id, String name, String description, int cost) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Benefit id is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Benefit name is required");
        }
        if (cost <= 0) {
            throw new IllegalArgumentException("Benefit cost must be greater than zero");
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }
}
