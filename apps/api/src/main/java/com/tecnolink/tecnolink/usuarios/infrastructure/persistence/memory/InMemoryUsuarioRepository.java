package com.tecnolink.tecnolink.usuarios.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.usuarios.domain.aggregates.Administrador;
import com.tecnolink.tecnolink.usuarios.domain.aggregates.Cliente;
import com.tecnolink.tecnolink.usuarios.domain.aggregates.Usuario;
import com.tecnolink.tecnolink.usuarios.domain.repositories.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUsuarioRepository implements UsuarioRepository {

    private final Map<String, Usuario> store = new LinkedHashMap<>();

    public InMemoryUsuarioRepository() {
        save(new Cliente("demo-client", "Ana", "Torres Quispe", "ana.torres@correo.com", "demo1234",
                "987 654 321", LocalDate.of(2026, 5, 20), true, "45871236", "Av. Arequipa 1250", "Lima - Lince"));
        save(new Cliente("marco-t", "Marco", "Tello Ramos", "marco.tello@correo.com", "demo1234",
                "956 112 340", LocalDate.of(2026, 6, 15), true, "70214589", "Jr. Huallaga 455", "Lima - Cercado de Lima"));
        save(new Cliente("lucia-r", "Lucía", "Rojas Vega", "lucia.rojas@correo.com", "demo1234",
                "944 870 215", LocalDate.of(2026, 7, 2), true, "72365410", "Calle Los Pinos 128", "Lima - Surco"));
        save(new Cliente("andrea-p", "Andrea", "Paredes León", "andrea.paredes@correo.com", "demo1234",
                "923 410 778", LocalDate.of(2026, 8, 5), true, "46120397", "Av. Brasil 2033", "Lima - Pueblo Libre"));
        save(new Administrador("admin", "Carla", "Mendoza Ríos", "admin@tecnolink.pe", "admin1234",
                "(01) 700 1200", LocalDate.of(2026, 5, 1), true, "Administradora de la plataforma"));
    }

    @Override
    public Usuario save(Usuario usuario) {
        store.put(usuario.getIdUsuario(), usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> findById(String idUsuario) {
        return Optional.ofNullable(store.get(idUsuario));
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        for (Usuario usuario : store.values()) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) return Optional.of(usuario);
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(store.values());
    }
}
