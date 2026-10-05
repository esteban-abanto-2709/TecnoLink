package com.tecnolink.tecnolink.usuarios.domain.repositories;

import com.tecnolink.tecnolink.usuarios.domain.aggregates.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(String idUsuario);
    Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findAll();
}
