package com.tecnolink.tecnolink.usuarios.domain.aggregates;

import java.time.LocalDate;

public class Administrador extends Usuario {
    private final String cargo;

    public Administrador(String idUsuario, String nombres, String apellidos, String correo, String contrasena,
                         String telefono, LocalDate fechaRegistro, boolean activo, String cargo) {
        super(idUsuario, nombres, apellidos, correo, contrasena, telefono, fechaRegistro, activo);
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("Administrator role is required");
        }
        this.cargo = cargo;
    }

    public void gestionarUsuario(Usuario usuario, boolean activo) {
        if (usuario == this) {
            throw new IllegalArgumentException("An administrator cannot change their own status");
        }
        if (activo) {
            usuario.activar();
        } else {
            usuario.desactivar();
        }
    }

    public String getCargo() {
        return cargo;
    }
}
