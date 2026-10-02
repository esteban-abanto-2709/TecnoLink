package com.tecnolink.tecnolink.catalog.infrastructure.persistence.memory;

import com.tecnolink.tecnolink.catalog.domain.model.aggregates.Product;
import com.tecnolink.tecnolink.catalog.domain.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> store = new LinkedHashMap<>();

    public InMemoryProductRepository() {
        save(new Product("lenovo-ideapad-3", "Laptop Lenovo IdeaPad 3 15", "Lenovo", "laptops", "techperu", 1899,
                "Laptop para estudio y trabajo de oficina, liviana y con buena autonomía.",
                specs("Procesador", "Intel Core i5-1235U", "RAM", "8 GB DDR4", "Almacenamiento", "512 GB SSD",
                        "Pantalla", "15.6\" Full HD", "Tarjeta gráfica", "Intel Iris Xe", "Batería", "Hasta 7 horas")));
        save(new Product("hp-pavilion-14", "Laptop HP Pavilion 14", "HP", "laptops", "techperu", 2399,
                "Equipo compacto de 14 pulgadas, pensado para quien se mueve todo el día.",
                specs("Procesador", "AMD Ryzen 5 7530U", "RAM", "16 GB DDR4", "Almacenamiento", "512 GB SSD",
                        "Pantalla", "14\" Full HD", "Tarjeta gráfica", "AMD Radeon integrada", "Batería", "Hasta 9 horas")));
        save(new Product("asus-tuf-a15", "Laptop ASUS TUF Gaming A15", "ASUS", "laptops", "compuzone", 4290,
                "Laptop gamer con gráfica dedicada, también usada para diseño y render.",
                specs("Procesador", "AMD Ryzen 7 7435HS", "RAM", "16 GB DDR5", "Almacenamiento", "1 TB SSD",
                        "Pantalla", "15.6\" Full HD 144 Hz", "Tarjeta gráfica", "NVIDIA RTX 4050 6 GB", "Batería", "Hasta 5 horas")));
        save(new Product("acer-aspire-3", "Laptop Acer Aspire 3", "Acer", "laptops", "compuzone", 1549,
                "La opción de entrada para tareas de ofimática, navegación y clases virtuales.",
                specs("Procesador", "Intel Core i3-1215U", "RAM", "8 GB DDR4", "Almacenamiento", "256 GB SSD",
                        "Pantalla", "15.6\" Full HD", "Tarjeta gráfica", "Intel UHD", "Batería", "Hasta 6 horas")));
        save(new Product("samsung-galaxy-a55", "Samsung Galaxy A55", "Samsung", "smartphones", "digitalstore", 1599,
                "Gama media con pantalla AMOLED y cámara estabilizada.",
                specs("Pantalla", "6.6\" Super AMOLED 120 Hz", "Procesador", "Exynos 1480", "RAM", "8 GB",
                        "Almacenamiento", "256 GB", "Cámara principal", "50 MP con OIS", "Batería", "5000 mAh")));
        save(new Product("xiaomi-redmi-note-13", "Xiaomi Redmi Note 13", "Xiaomi", "smartphones", "digitalstore", 899,
                "Equipo de precio contenido con buena batería y carga rápida.",
                specs("Pantalla", "6.67\" AMOLED 120 Hz", "Procesador", "Snapdragon 685", "RAM", "8 GB",
                        "Almacenamiento", "256 GB", "Cámara principal", "108 MP", "Batería", "5000 mAh")));
        save(new Product("motorola-g84", "Motorola Moto G84", "Motorola", "smartphones", "digitalstore", 1049,
                "Android limpio, sin capa pesada, con buena autonomía.",
                specs("Pantalla", "6.5\" pOLED 120 Hz", "Procesador", "Snapdragon 695", "RAM", "12 GB",
                        "Almacenamiento", "256 GB", "Cámara principal", "50 MP con OIS", "Batería", "5000 mAh")));
        save(new Product("lg-ultragear-24", "Monitor LG UltraGear 24", "LG", "monitors", "compuzone", 749,
                "Monitor de 24 pulgadas con alta frecuencia de refresco.",
                specs("Tamaño", "23.8 pulgadas", "Resolución", "1920 x 1080", "Frecuencia", "144 Hz",
                        "Panel", "IPS", "Conexiones", "HDMI x2, DisplayPort")));
        save(new Product("samsung-monitor-27", "Monitor Samsung Essential 27", "Samsung", "monitors", "techperu", 899,
                "Pantalla amplia para oficina, con marco delgado en tres lados.",
                specs("Tamaño", "27 pulgadas", "Resolución", "1920 x 1080", "Frecuencia", "75 Hz",
                        "Panel", "IPS", "Conexiones", "HDMI, VGA")));
        save(new Product("epson-l3250", "Impresora Epson EcoTank L3250", "Epson", "printers", "techperu", 749,
                "Multifuncional de tinta continua, la más usada en oficinas pequeñas.",
                specs("Tipo", "Multifuncional a color", "Velocidad", "33 ppm en negro", "Conectividad", "USB, Wi-Fi",
                        "Dúplex", "Manual")));
        save(new Product("hp-laserjet-m141w", "Impresora HP LaserJet M141w", "HP", "printers", "compuzone", 649,
                "Láser monocromática para alto volumen de documentos.",
                specs("Tipo", "Multifuncional monocromática", "Velocidad", "20 ppm", "Conectividad", "USB, Wi-Fi",
                        "Dúplex", "Manual")));
        save(new Product("kingston-fury-16", "Memoria RAM Kingston Fury 16 GB", "Kingston", "components", "compuzone", 279,
                "Módulo DDR4 para ampliar la memoria de laptops y PC de escritorio.",
                specs("Capacidad", "16 GB", "Tipo", "DDR4", "Velocidad", "3200 MHz", "Formato", "SODIMM")));
        save(new Product("kingston-nv2-1tb", "SSD Kingston NV2 1 TB", "Kingston", "components", "compuzone", 299,
                "Unidad NVMe para revivir equipos con disco mecánico.",
                specs("Capacidad", "1 TB", "Interfaz", "PCIe 4.0 NVMe", "Lectura", "3500 MB/s", "Formato", "M.2 2280")));
        save(new Product("tplink-archer-c80", "Router TP-Link Archer C80", "TP-Link", "networking", "redesandinas", 249,
                "Router de doble banda para departamentos y oficinas pequeñas.",
                specs("Bandas", "2.4 GHz y 5 GHz", "Velocidad", "1900 Mbps", "Puertos", "4 LAN Gigabit",
                        "Antenas", "4 externas")));
    }

    private static Map<String, String> specs(String... keysAndValues) {
        Map<String, String> specs = new LinkedHashMap<>();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            specs.put(keysAndValues[i], keysAndValues[i + 1]);
        }
        return specs;
    }

    private void save(Product product) {
        store.put(product.getId(), product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
