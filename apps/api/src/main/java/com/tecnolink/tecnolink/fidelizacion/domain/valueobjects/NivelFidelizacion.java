package com.tecnolink.tecnolink.fidelizacion.domain.valueobjects;

public enum NivelFidelizacion {
    BRONCE,
    PLATA,
    ORO;

    public static NivelFidelizacion segun(int puntos) {
        if (puntos >= 1500) return ORO;
        if (puntos >= 500) return PLATA;
        return BRONCE;
    }
}
