package com.tecnolink.tecnolink.usuarios.domain.aggregates;

import java.time.LocalDate;

public class Cliente extends Usuario {
    private final String dni;
    private String direccion;
    private String region;

    public Cliente(String idUsuario, String nombres, String apellidos, String correo, String contrasena,
                   String telefono, LocalDate fechaRegistro, boolean activo, String dni, String direccion,
                   String region) {
        super(idUsuario, nombres, apellidos, correo, contrasena, telefono, fechaRegistro, activo);
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("Client DNI must have 8 digits");
        }
        this.dni = dni;
        cambiarDireccion(direccion, region);
    }

    public void cambiarDireccion(String direccion, String region) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("Client address is required");
        }
        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Client region is required");
        }
        this.direccion = direccion;
        this.region = region;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getRegion() {
        return region;
    }
}
