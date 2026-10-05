package com.tecnolink.tecnolink.catalogo.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.catalogo.domain.aggregates.Producto;
import com.tecnolink.tecnolink.catalogo.domain.aggregates.Publicacion;
import com.tecnolink.tecnolink.catalogo.domain.aggregates.Servicio;
import com.tecnolink.tecnolink.catalogo.domain.repositories.PublicacionRepository;
import com.tecnolink.tecnolink.catalogo.domain.valueobjects.EstadoPublicacion;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryPublicacionRepository implements PublicacionRepository {

    private final Map<String, Publicacion> store = new LinkedHashMap<>();

    public InMemoryPublicacionRepository() {
        producto("lenovo-ideapad-3", "techperu", "laptops", "Laptop Lenovo IdeaPad 3 15",
                "Laptop para estudio y trabajo de oficina, liviana y con buena autonomía.", 1899,
                "Lenovo", "IdeaPad 3 15IAU7", 12);
        producto("hp-pavilion-14", "techperu", "laptops", "Laptop HP Pavilion 14",
                "Equipo compacto de 14 pulgadas, pensado para quien se mueve todo el día.", 2399,
                "HP", "Pavilion 14-ec1", 8);
        producto("asus-tuf-a15", "compuzone", "laptops", "Laptop ASUS TUF Gaming A15",
                "Laptop gamer con gráfica dedicada, también usada para diseño y render.", 4290,
                "ASUS", "TUF A15 FA507NU", 5);
        producto("acer-aspire-3", "compuzone", "laptops", "Laptop Acer Aspire 3",
                "La opción de entrada para tareas de ofimática, navegación y clases virtuales.", 1549,
                "Acer", "Aspire 3 A315-59", 15);
        producto("samsung-galaxy-a55", "digitalstore", "smartphones", "Samsung Galaxy A55",
                "Gama media con pantalla AMOLED y cámara estabilizada.", 1599,
                "Samsung", "SM-A556E", 20);
        producto("xiaomi-redmi-note-13", "digitalstore", "smartphones", "Xiaomi Redmi Note 13",
                "Equipo de precio contenido con buena batería y carga rápida.", 899,
                "Xiaomi", "Redmi Note 13 4G", 25);
        producto("motorola-g84", "digitalstore", "smartphones", "Motorola Moto G84",
                "Android limpio, sin capa pesada, con buena autonomía.", 1049,
                "Motorola", "Moto G84 5G", 18);
        producto("lg-ultragear-24", "compuzone", "monitors", "Monitor LG UltraGear 24",
                "Monitor de 24 pulgadas con alta frecuencia de refresco.", 749,
                "LG", "24GN60R-B", 10);
        producto("samsung-monitor-27", "techperu", "monitors", "Monitor Samsung Essential 27",
                "Pantalla amplia para oficina, con marco delgado en tres lados.", 899,
                "Samsung", "LS27C310EALXPE", 9);
        producto("epson-l3250", "techperu", "printers", "Impresora Epson EcoTank L3250",
                "Multifuncional de tinta continua, la más usada en oficinas pequeñas.", 749,
                "Epson", "EcoTank L3250", 14);
        producto("hp-laserjet-m141w", "compuzone", "printers", "Impresora HP LaserJet M141w",
                "Láser monocromática para alto volumen de documentos.", 649,
                "HP", "LaserJet MFP M141w", 6);
        producto("kingston-fury-16", "compuzone", "components", "Memoria RAM Kingston Fury 16 GB",
                "Módulo DDR4 para ampliar la memoria de laptops y PC de escritorio.", 279,
                "Kingston", "KF432C16BB/16", 40);
        producto("kingston-nv2-1tb", "compuzone", "components", "SSD Kingston NV2 1 TB",
                "Unidad NVMe para revivir equipos con disco mecánico.", 299,
                "Kingston", "SNV2S/1000G", 35);
        producto("tplink-archer-c80", "redesandinas", "networking", "Router TP-Link Archer C80",
                "Router de doble banda para departamentos y oficinas pequeñas.", 249,
                "TP-Link", "Archer C80 AC1900", 22);

        servicio("soporte-domicilio", "soportelima", "support", "Soporte técnico a domicilio",
                "Diagnóstico y solución de fallas de software o hardware en el lugar del cliente.", 60,
                "A domicilio", "1 a 2 horas", "Lima Metropolitana");
        servicio("mantenimiento-laptop", "soportelima", "support", "Mantenimiento preventivo de laptop",
                "Limpieza interna, cambio de pasta térmica y revisión general del equipo.", 90,
                "A domicilio", "2 horas", "Surco, San Borja, Miraflores y alrededores");
        servicio("recuperacion-datos", "compuzone", "support", "Recuperación de datos",
                "Rescate de archivos en discos dañados o unidades formateadas por error.", 150,
                "En taller", "2 a 5 días", "Solo en taller, Cercado de Lima");
        servicio("cableado-oficina", "redesandinas", "installation", "Instalación de red cableada para oficina",
                "Cableado estructurado, certificación de puntos y configuración de equipos.", 450,
                "En sede del cliente", "1 a 3 días", "Lima Metropolitana y Callao");
        servicio("camaras-seguridad", "redesandinas", "installation", "Instalación de cámaras de seguridad",
                "Instalación de cámaras, grabador y acceso remoto desde el celular.", 600,
                "En sede del cliente", "1 día", "Lima Metropolitana");
        servicio("web-informativa", "digitalstore", "development", "Desarrollo de página web informativa",
                "Sitio de hasta cinco secciones, adaptable a celular, con dominio y hosting del primer año.", 1200,
                "Remoto", "2 a 3 semanas", "Remoto");
    }

    private void producto(String id, String proveedorId, String categoriaId, String titulo, String descripcion,
                          double precio, String marca, String modelo, int stock) {
        save(new Producto(id, proveedorId, categoriaId, titulo, descripcion, precio, List.of("/images/" + id + ".jpg"),
                LocalDate.of(2026, 9, 1), EstadoPublicacion.ACTIVA, marca, modelo, stock));
    }

    private void servicio(String id, String proveedorId, String categoriaId, String titulo, String descripcion,
                          double precio, String modalidad, String duracionEstimada, String cobertura) {
        save(new Servicio(id, proveedorId, categoriaId, titulo, descripcion, precio, List.of("/images/" + id + ".jpg"),
                LocalDate.of(2026, 9, 1), EstadoPublicacion.ACTIVA, modalidad, duracionEstimada, cobertura));
    }

    @Override
    public Publicacion save(Publicacion publicacion) {
        store.put(publicacion.getIdPublicacion(), publicacion);
        return publicacion;
    }

    @Override
    public Optional<Publicacion> findById(String idPublicacion) {
        return Optional.ofNullable(store.get(idPublicacion));
    }

    @Override
    public List<Publicacion> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Publicacion> findByCategoriaId(String categoriaId) {
        List<Publicacion> publicaciones = new ArrayList<>();
        for (Publicacion publicacion : store.values()) {
            if (publicacion.getCategoriaId().equals(categoriaId)) publicaciones.add(publicacion);
        }
        return publicaciones;
    }

    @Override
    public List<Publicacion> findByProveedorId(String proveedorId) {
        List<Publicacion> publicaciones = new ArrayList<>();
        for (Publicacion publicacion : store.values()) {
            if (publicacion.getProveedorId().equals(proveedorId)) publicaciones.add(publicacion);
        }
        return publicaciones;
    }
}
