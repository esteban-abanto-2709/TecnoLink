package com.tecnolink.tecnolink.catalog.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Category;
import com.tecnolink.tecnolink.catalog.domain.model.enums.CategoryKind;
import com.tecnolink.tecnolink.catalog.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryCategoryRepository implements CategoryRepository {

    private final Map<String, Category> store = new LinkedHashMap<>();

    public InMemoryCategoryRepository() {
        save(new Category("laptops", "Laptops", CategoryKind.PRODUCT));
        save(new Category("smartphones", "Celulares", CategoryKind.PRODUCT));
        save(new Category("monitors", "Monitores", CategoryKind.PRODUCT));
        save(new Category("printers", "Impresoras", CategoryKind.PRODUCT));
        save(new Category("components", "Componentes", CategoryKind.PRODUCT));
        save(new Category("networking", "Redes", CategoryKind.PRODUCT));
        save(new Category("support", "Soporte técnico", CategoryKind.SERVICE));
        save(new Category("installation", "Instalación", CategoryKind.SERVICE));
        save(new Category("development", "Desarrollo", CategoryKind.SERVICE));
    }

    private void save(Category category) {
        store.put(category.getId(), category);
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Category> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
