package com.tecnolink.tecnolink.catalogo.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.catalogo.domain.aggregates.Categoria;
import com.tecnolink.tecnolink.catalogo.domain.repositories.CategoriaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryCategoriaRepository implements CategoriaRepository {

    private final Map<String, Categoria> store = new LinkedHashMap<>();

    public InMemoryCategoriaRepository() {
        save(new Categoria("laptops", "Laptops", "Equipos portátiles para estudio, oficina y gaming"));
        save(new Categoria("smartphones", "Celulares", "Smartphones de gama media y alta"));
        save(new Categoria("monitors", "Monitores", "Pantallas para oficina, diseño y juegos"));
        save(new Categoria("printers", "Impresoras", "Impresoras de tinta continua y láser"));
        save(new Categoria("components", "Componentes", "Memorias, discos y piezas para mejorar equipos"));
        save(new Categoria("networking", "Redes", "Routers y equipos de conectividad"));
        save(new Categoria("support", "Soporte técnico", "Reparación y mantenimiento de equipos"));
        save(new Categoria("installation", "Instalación", "Redes, cámaras y cableado"));
        save(new Categoria("development", "Desarrollo", "Páginas web y software a medida"));
    }

    @Override
    public Categoria save(Categoria categoria) {
        store.put(categoria.getIdCategoria(), categoria);
        return categoria;
    }

    @Override
    public Optional<Categoria> findById(String idCategoria) {
        return Optional.ofNullable(store.get(idCategoria));
    }

    @Override
    public List<Categoria> findAll() {
        return new ArrayList<>(store.values());
    }
}
