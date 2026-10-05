package com.tecnolink.tecnolink.usuarios.domain.aggregates;

import java.time.LocalDate;

public abstract class Usuario {
    private final String idUsuario;
    private String nombres;
    private String apellidos;
    private final String correo;
    private final String contrasena;
    private String telefono;
    private LocalDate fechaRegistro;
    private boolean activo;
    private boolean sesionIniciada;

    protected Usuario(String idUsuario, String nombres, String apellidos, String correo, String contrasena,
                      String telefono, LocalDate fechaRegistro, boolean activo) {
        if (idUsuario == null || idUsuario.isBlank()) {
            throw new IllegalArgumentException("User id is required");
        }
        if (correo == null || !correo.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            throw new IllegalArgumentException("User email is not valid");
        }
        if (contrasena == null || contrasena.length() < 8) {
            throw new IllegalArgumentException("User password must have at least 8 characters");
        }
        if (activo && fechaRegistro == null) {
            throw new IllegalArgumentException("An active user needs a registration date");
        }
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
        actualizarPerfil(nombres, apellidos, telefono);
    }

    public void registrarse(LocalDate hoy) {
        if (fechaRegistro != null) {
            throw new IllegalArgumentException("User " + correo + " is already registered");
        }
        fechaRegistro = hoy;
        activo = true;
    }

    public boolean iniciarSesion(String correo, String contrasena) {
        sesionIniciada = activo && this.correo.equalsIgnoreCase(correo) && this.contrasena.equals(contrasena);
        return sesionIniciada;
    }

    public void cerrarSesion() {
        sesionIniciada = false;
    }

    public void actualizarPerfil(String nombres, String apellidos, String telefono) {
        if (nombres == null || nombres.isBlank()) {
            throw new IllegalArgumentException("User first name is required");
        }
        if (apellidos == null || apellidos.isBlank()) {
            throw new IllegalArgumentException("User last name is required");
        }
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono == null ? "" : telefono;
    }

    public void desactivar() {
        activo = false;
        sesionIniciada = false;
    }

    public void activar() {
        if (fechaRegistro == null) {
            throw new IllegalArgumentException("User " + correo + " is not registered");
        }
        activo = true;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public boolean isSesionIniciada() {
        return sesionIniciada;
    }
}
