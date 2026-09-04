import type {
  Benefit,
  CatalogItem,
  Category,
  CategoryKind,
  Order,
  PointsMovement,
  Product,
  Quote,
  Review,
  Service,
  Supplier,
} from "./types";

export const categories: Category[] = [
  { id: "laptops", name: "Laptops", kind: "product" },
  { id: "smartphones", name: "Celulares", kind: "product" },
  { id: "monitors", name: "Monitores", kind: "product" },
  { id: "printers", name: "Impresoras", kind: "product" },
  { id: "components", name: "Componentes", kind: "product" },
  { id: "networking", name: "Redes", kind: "product" },
  { id: "support", name: "Soporte técnico", kind: "service" },
  { id: "installation", name: "Instalación", kind: "service" },
  { id: "development", name: "Desarrollo", kind: "service" },
];

export const suppliers: Supplier[] = [
  {
    id: "techperu",
    name: "Importaciones TechPerú",
    ruc: "20553417802",
    phone: "(01) 562 4471",
    district: "San Miguel",
    description:
      "Importador de equipos de cómputo con tienda física y garantía propia de 12 meses.",
    since: 2016,
    verified: true,
  },
  {
    id: "compuzone",
    name: "CompuZone",
    ruc: "20481936275",
    phone: "(01) 428 9013",
    district: "Cercado de Lima",
    description:
      "Venta de componentes y armado de equipos a medida en la galería Wilson.",
    since: 2011,
    verified: true,
  },
  {
    id: "digitalstore",
    name: "Digital Store Perú",
    ruc: "20604158293",
    phone: "987 314 220",
    district: "Miraflores",
    description:
      "Celulares y accesorios liberados, con planes de cambio y reparación.",
    since: 2019,
    verified: false,
  },
  {
    id: "redesandinas",
    name: "Redes Andinas",
    ruc: "20512873604",
    phone: "(01) 476 8852",
    district: "San Borja",
    description:
      "Cableado estructurado, cámaras y redes para oficinas y locales comerciales.",
    since: 2014,
    verified: true,
  },
  {
    id: "soportelima",
    name: "Soporte Lima",
    ruc: "10457821936",
    phone: "961 208 447",
    district: "Santiago de Surco",
    description:
      "Servicio técnico a domicilio para equipos de cómputo y periféricos.",
    since: 2020,
    verified: false,
  },
];

export const products: Product[] = [
  {
    id: "lenovo-ideapad-3",
    name: "Laptop Lenovo IdeaPad 3 15",
    brand: "Lenovo",
    categoryId: "laptops",
    supplierId: "techperu",
    price: 1899,
    description:
      "Laptop para estudio y trabajo de oficina, liviana y con buena autonomía.",
    specs: {
      Procesador: "Intel Core i5-1235U",
      RAM: "8 GB DDR4",
      Almacenamiento: "512 GB SSD",
      Pantalla: "15.6\" Full HD",
      "Tarjeta gráfica": "Intel Iris Xe",
      Batería: "Hasta 7 horas",
    },
  },
  {
    id: "hp-pavilion-14",
    name: "Laptop HP Pavilion 14",
    brand: "HP",
    categoryId: "laptops",
    supplierId: "techperu",
    price: 2399,
    description:
      "Equipo compacto de 14 pulgadas, pensado para quien se mueve todo el día.",
    specs: {
      Procesador: "AMD Ryzen 5 7530U",
      RAM: "16 GB DDR4",
      Almacenamiento: "512 GB SSD",
      Pantalla: "14\" Full HD",
      "Tarjeta gráfica": "AMD Radeon integrada",
      Batería: "Hasta 9 horas",
    },
  },
  {
    id: "asus-tuf-a15",
    name: "Laptop ASUS TUF Gaming A15",
    brand: "ASUS",
    categoryId: "laptops",
    supplierId: "compuzone",
    price: 4290,
    description:
      "Laptop gamer con gráfica dedicada, también usada para diseño y render.",
    specs: {
      Procesador: "AMD Ryzen 7 7435HS",
      RAM: "16 GB DDR5",
      Almacenamiento: "1 TB SSD",
      Pantalla: "15.6\" Full HD 144 Hz",
      "Tarjeta gráfica": "NVIDIA RTX 4050 6 GB",
      Batería: "Hasta 5 horas",
    },
  },
  {
    id: "acer-aspire-3",
    name: "Laptop Acer Aspire 3",
    brand: "Acer",
    categoryId: "laptops",
    supplierId: "compuzone",
    price: 1549,
    description:
      "La opción de entrada para tareas de ofimática, navegación y clases virtuales.",
    specs: {
      Procesador: "Intel Core i3-1215U",
      RAM: "8 GB DDR4",
      Almacenamiento: "256 GB SSD",
      Pantalla: "15.6\" Full HD",
      "Tarjeta gráfica": "Intel UHD",
      Batería: "Hasta 6 horas",
    },
  },
  {
    id: "samsung-galaxy-a55",
    name: "Samsung Galaxy A55",
    brand: "Samsung",
    categoryId: "smartphones",
    supplierId: "digitalstore",
    price: 1599,
    description: "Gama media con pantalla AMOLED y cámara estabilizada.",
    specs: {
      Pantalla: "6.6\" Super AMOLED 120 Hz",
      Procesador: "Exynos 1480",
      RAM: "8 GB",
      Almacenamiento: "256 GB",
      "Cámara principal": "50 MP con OIS",
      Batería: "5000 mAh",
    },
  },
  {
    id: "xiaomi-redmi-note-13",
    name: "Xiaomi Redmi Note 13",
    brand: "Xiaomi",
    categoryId: "smartphones",
    supplierId: "digitalstore",
    price: 899,
    description: "Equipo de precio contenido con buena batería y carga rápida.",
    specs: {
      Pantalla: "6.67\" AMOLED 120 Hz",
      Procesador: "Snapdragon 685",
      RAM: "8 GB",
      Almacenamiento: "256 GB",
      "Cámara principal": "108 MP",
      Batería: "5000 mAh",
    },
  },
  {
    id: "motorola-g84",
    name: "Motorola Moto G84",
    brand: "Motorola",
    categoryId: "smartphones",
    supplierId: "digitalstore",
    price: 1049,
    description: "Android limpio, sin capa pesada, con buena autonomía.",
    specs: {
      Pantalla: "6.5\" pOLED 120 Hz",
      Procesador: "Snapdragon 695",
      RAM: "12 GB",
      Almacenamiento: "256 GB",
      "Cámara principal": "50 MP con OIS",
      Batería: "5000 mAh",
    },
  },
  {
    id: "lg-ultragear-24",
    name: "Monitor LG UltraGear 24",
    brand: "LG",
    categoryId: "monitors",
    supplierId: "compuzone",
    price: 749,
    description: "Monitor de 24 pulgadas con alta frecuencia de refresco.",
    specs: {
      Tamaño: "23.8 pulgadas",
      Resolución: "1920 x 1080",
      Frecuencia: "144 Hz",
      Panel: "IPS",
      Conexiones: "HDMI x2, DisplayPort",
    },
  },
  {
    id: "samsung-monitor-27",
    name: "Monitor Samsung Essential 27",
    brand: "Samsung",
    categoryId: "monitors",
    supplierId: "techperu",
    price: 899,
    description: "Pantalla amplia para oficina, con marco delgado en tres lados.",
    specs: {
      Tamaño: "27 pulgadas",
      Resolución: "1920 x 1080",
      Frecuencia: "75 Hz",
      Panel: "IPS",
      Conexiones: "HDMI, VGA",
    },
  },
  {
    id: "epson-l3250",
    name: "Impresora Epson EcoTank L3250",
    brand: "Epson",
    categoryId: "printers",
    supplierId: "techperu",
    price: 749,
    description:
      "Multifuncional de tinta continua, la más usada en oficinas pequeñas.",
    specs: {
      Tipo: "Multifuncional a color",
      Velocidad: "33 ppm en negro",
      Conectividad: "USB, Wi-Fi",
      Dúplex: "Manual",
    },
  },
  {
    id: "hp-laserjet-m141w",
    name: "Impresora HP LaserJet M141w",
    brand: "HP",
    categoryId: "printers",
    supplierId: "compuzone",
    price: 649,
    description: "Láser monocromática para alto volumen de documentos.",
    specs: {
      Tipo: "Multifuncional monocromática",
      Velocidad: "20 ppm",
      Conectividad: "USB, Wi-Fi",
      Dúplex: "Manual",
    },
  },
  {
    id: "kingston-fury-16",
    name: "Memoria RAM Kingston Fury 16 GB",
    brand: "Kingston",
    categoryId: "components",
    supplierId: "compuzone",
    price: 279,
    description: "Módulo DDR4 para ampliar la memoria de laptops y PC de escritorio.",
    specs: {
      Capacidad: "16 GB",
      Tipo: "DDR4",
      Velocidad: "3200 MHz",
      Formato: "SODIMM",
    },
  },
  {
    id: "kingston-nv2-1tb",
    name: "SSD Kingston NV2 1 TB",
    brand: "Kingston",
    categoryId: "components",
    supplierId: "compuzone",
    price: 299,
    description: "Unidad NVMe para revivir equipos con disco mecánico.",
    specs: {
      Capacidad: "1 TB",
      Interfaz: "PCIe 4.0 NVMe",
      Lectura: "3500 MB/s",
      Formato: "M.2 2280",
    },
  },
  {
    id: "tplink-archer-c80",
    name: "Router TP-Link Archer C80",
    brand: "TP-Link",
    categoryId: "networking",
    supplierId: "redesandinas",
    price: 249,
    description: "Router de doble banda para departamentos y oficinas pequeñas.",
    specs: {
      Bandas: "2.4 GHz y 5 GHz",
      Velocidad: "1900 Mbps",
      Puertos: "4 LAN Gigabit",
      Antenas: "4 externas",
    },
  },
];

export const services: Service[] = [
  {
    id: "soporte-domicilio",
    name: "Soporte técnico a domicilio",
    categoryId: "support",
    supplierId: "soportelima",
    price: 60,
    pricing: "hourly",
    description:
      "Diagnóstico y solución de fallas de software o hardware en el lugar del cliente.",
    coverage: "Lima Metropolitana",
  },
  {
    id: "mantenimiento-laptop",
    name: "Mantenimiento preventivo de laptop",
    categoryId: "support",
    supplierId: "soportelima",
    price: 90,
    pricing: "fixed",
    description:
      "Limpieza interna, cambio de pasta térmica y revisión general del equipo.",
    coverage: "Surco, San Borja, Miraflores y alrededores",
  },
  {
    id: "recuperacion-datos",
    name: "Recuperación de datos",
    categoryId: "support",
    supplierId: "compuzone",
    price: 150,
    pricing: "from",
    description:
      "Rescate de archivos en discos dañados o unidades formateadas por error.",
    coverage: "Solo en taller, Cercado de Lima",
  },
  {
    id: "cableado-oficina",
    name: "Instalación de red cableada para oficina",
    categoryId: "installation",
    supplierId: "redesandinas",
    price: 450,
    pricing: "from",
    description:
      "Cableado estructurado, certificación de puntos y configuración de equipos.",
    coverage: "Lima Metropolitana y Callao",
  },
  {
    id: "camaras-seguridad",
    name: "Instalación de cámaras de seguridad",
    categoryId: "installation",
    supplierId: "redesandinas",
    price: 600,
    pricing: "from",
    description:
      "Instalación de cámaras, grabador y acceso remoto desde el celular.",
    coverage: "Lima Metropolitana",
  },
  {
    id: "web-informativa",
    name: "Desarrollo de página web informativa",
    categoryId: "development",
    supplierId: "digitalstore",
    price: 1200,
    pricing: "from",
    description:
      "Sitio de hasta cinco secciones, adaptable a celular, con dominio y hosting del primer año.",
    coverage: "Remoto",
  },
];

export const reviews: Review[] = [
  {
    id: "rev-01",
    targetId: "lenovo-ideapad-3",
    author: "Marco T.",
    rating: 5,
    comment: "Llegó sellada y con boleta. Va bien para trabajo de oficina.",
    date: "2026-07-14",
  },
  {
    id: "rev-02",
    targetId: "lenovo-ideapad-3",
    author: "Karina P.",
    rating: 4,
    comment: "Buena laptop, aunque con 8 GB se queda corta si abres mucho Chrome.",
    date: "2026-06-30",
  },
  {
    id: "rev-03",
    targetId: "lenovo-ideapad-3",
    author: "Jhon A.",
    rating: 5,
    comment: "El proveedor respondió rápido las dudas antes de comprar.",
    date: "2026-06-11",
  },
  {
    id: "rev-04",
    targetId: "asus-tuf-a15",
    author: "Diego R.",
    rating: 5,
    comment: "Corre todo lo que le pongo. Calienta, pero es normal en gamer.",
    date: "2026-08-02",
  },
  {
    id: "rev-05",
    targetId: "asus-tuf-a15",
    author: "Fiorella S.",
    rating: 4,
    comment: "Excelente rendimiento, la batería dura poco fuera del cargador.",
    date: "2026-07-21",
  },
  {
    id: "rev-06",
    targetId: "samsung-galaxy-a55",
    author: "Luis M.",
    rating: 4,
    comment: "La pantalla se ve muy bien. El equipo vino liberado como decía.",
    date: "2026-08-09",
  },
  {
    id: "rev-07",
    targetId: "samsung-galaxy-a55",
    author: "Ana C.",
    rating: 3,
    comment: "Buen celular, pero demoraron dos días más de lo acordado.",
    date: "2026-07-05",
  },
  {
    id: "rev-08",
    targetId: "epson-l3250",
    author: "Rosa V.",
    rating: 5,
    comment: "La tinta rinde muchísimo, ya no compro cartuchos.",
    date: "2026-08-18",
  },
  {
    id: "rev-09",
    targetId: "epson-l3250",
    author: "Pedro Q.",
    rating: 4,
    comment: "Fácil de configurar por Wi-Fi. Imprime lento a color.",
    date: "2026-05-27",
  },
  {
    id: "rev-10",
    targetId: "soporte-domicilio",
    author: "Elena G.",
    rating: 5,
    comment: "Vino el mismo día y dejó la PC funcionando. Cobró lo acordado.",
    date: "2026-08-12",
  },
  {
    id: "rev-11",
    targetId: "cableado-oficina",
    author: "Estudio Ramírez",
    rating: 5,
    comment: "Dejaron doce puntos certificados y ordenados. Muy profesional.",
    date: "2026-07-29",
  },
  {
    id: "rev-12",
    targetId: "techperu",
    author: "Carlos B.",
    rating: 5,
    comment: "Tres compras y ningún problema. Respetan la garantía.",
    date: "2026-08-20",
  },
  {
    id: "rev-13",
    targetId: "techperu",
    author: "Milagros H.",
    rating: 4,
    comment: "Precios competitivos, la tienda es pequeña pero atienden bien.",
    date: "2026-06-15",
  },
  {
    id: "rev-14",
    targetId: "digitalstore",
    author: "Sergio N.",
    rating: 3,
    comment: "El equipo llegó bien, pero la comunicación fue lenta.",
    date: "2026-07-08",
  },
];

export const quotes: Quote[] = [
  {
    id: "cot-001",
    itemId: "lenovo-ideapad-3",
    supplierId: "techperu",
    quantity: 5,
    requirement: "Cinco equipos para el área administrativa, con factura.",
    status: "answered",
    requestedOn: "2026-08-24",
    answer: {
      price: 8995,
      validUntil: "2026-09-15",
      conditions: "Entrega en 5 días hábiles. Garantía de 12 meses por equipo.",
      notes: "Incluye instalación de Office en los cinco equipos.",
    },
  },
  {
    id: "cot-002",
    itemId: "cableado-oficina",
    supplierId: "redesandinas",
    quantity: 1,
    requirement: "Oficina de 120 m² en San Isidro, aproximadamente 14 puntos.",
    status: "sent",
    requestedOn: "2026-08-29",
  },
  {
    id: "cot-004",
    itemId: "epson-l3250",
    supplierId: "techperu",
    quantity: 3,
    requirement:
      "Tres impresoras para un estudio contable en Jesús María. Necesito factura y entrega esta semana.",
    status: "sent",
    requestedOn: "2026-08-30",
  },
  {
    id: "cot-003",
    itemId: "epson-l3250",
    supplierId: "techperu",
    quantity: 2,
    requirement: "Dos impresoras para sede secundaria.",
    status: "expired",
    requestedOn: "2026-06-02",
    answer: {
      price: 1450,
      validUntil: "2026-06-30",
      conditions: "Recojo en tienda. No incluye envío.",
      notes: "Precio sujeto a disponibilidad de stock.",
    },
  },
];

export const orders: Order[] = [
  {
    id: "ord-001",
    date: "2026-08-18",
    lines: [
      {
        itemId: "epson-l3250",
        name: "Impresora Epson EcoTank L3250",
        quantity: 1,
        price: 749,
      },
    ],
    total: 749,
    pointsEarned: 74,
  },
  {
    id: "ord-002",
    date: "2026-07-14",
    lines: [
      {
        itemId: "lenovo-ideapad-3",
        name: "Laptop Lenovo IdeaPad 3 15",
        quantity: 1,
        price: 1899,
      },
      {
        itemId: "kingston-fury-16",
        name: "Memoria RAM Kingston Fury 16 GB",
        quantity: 1,
        price: 279,
      },
    ],
    total: 2178,
    pointsEarned: 217,
  },
];

export const benefits: Benefit[] = [
  {
    id: "priority-quotes",
    name: "Respuesta prioritaria a tus cotizaciones",
    description:
      "Tus solicitudes se muestran primero en la bandeja del proveedor durante un mes.",
    cost: 150,
  },
  {
    id: "free-shipping",
    name: "Envío gratis en tu próxima compra",
    description: "Cubre el envío dentro de Lima Metropolitana, sin monto mínimo.",
    cost: 150,
  },
  {
    id: "coupon-50",
    name: "Cupón de S/ 50 de descuento",
    description: "Se aplica sobre compras mayores a S/ 300.",
    cost: 500,
  },
  {
    id: "extended-warranty",
    name: "Garantía extendida de 6 meses",
    description:
      "Extiende la garantía del proveedor en un equipo que ya compraste.",
    cost: 800,
  },
  {
    id: "maintenance",
    name: "Mantenimiento preventivo gratis",
    description: "Una visita técnica a domicilio sin costo, coordinada contigo.",
    cost: 1200,
  },
];

export const pointsMovements: PointsMovement[] = [
  {
    id: "pts-001",
    date: "2026-08-18",
    description: "Compra ord-001",
    points: 74,
  },
  {
    id: "pts-002",
    date: "2026-07-20",
    description: "Canje: Envío gratis en tu próxima compra",
    points: -150,
    benefitId: "free-shipping",
  },
  {
    id: "pts-003",
    date: "2026-07-14",
    description: "Compra ord-002",
    points: 217,
  },
  {
    id: "pts-004",
    date: "2026-06-01",
    description: "Bono por reseña publicada",
    points: 50,
  },
];

export function getCategory(id: string): Category | undefined {
  return categories.find((category) => category.id === id);
}

export function getSupplier(id: string): Supplier | undefined {
  return suppliers.find((supplier) => supplier.id === id);
}

export function getProduct(id: string): Product | undefined {
  return products.find((product) => product.id === id);
}

export function getService(id: string): Service | undefined {
  return services.find((service) => service.id === id);
}

export function productsBySupplier(supplierId: string): Product[] {
  return products.filter((product) => product.supplierId === supplierId);
}

export function servicesBySupplier(supplierId: string): Service[] {
  return services.filter((service) => service.supplierId === supplierId);
}

export function reviewsFor(targetId: string): Review[] {
  return reviews.filter((review) => review.targetId === targetId);
}

export function ratingFor(targetId: string): number | null {
  const found = reviewsFor(targetId);
  if (found.length === 0) return null;
  const total = found.reduce((sum, review) => sum + review.rating, 0);
  return Math.round((total / found.length) * 10) / 10;
}

export function pointsBalance(): number {
  return pointsMovements.reduce((sum, movement) => sum + movement.points, 0);
}

export function catalogItems(): CatalogItem[] {
  return [
    ...products.map((product) => ({
      id: product.id,
      kind: "product" as const,
      name: product.name,
      categoryId: product.categoryId,
      supplierId: product.supplierId,
      price: product.price,
      description: product.description,
    })),
    ...services.map((service) => ({
      id: service.id,
      kind: "service" as const,
      name: service.name,
      categoryId: service.categoryId,
      supplierId: service.supplierId,
      price: service.price,
      pricing: service.pricing,
      description: service.description,
    })),
  ];
}

function normalize(value: string): string {
  return value
    .normalize("NFD")
    .replace(/[̀-ͯ]/g, "")
    .toLowerCase();
}

function words(value: string): string[] {
  return normalize(value)
    .split(/[^a-z0-9]+/)
    .filter(Boolean);
}

function haystack(item: CatalogItem): string[] {
  const brand = getProduct(item.id)?.brand ?? "";
  const category = getCategory(item.categoryId)?.name ?? "";
  const supplier = getSupplier(item.supplierId)?.name ?? "";
  return words([item.name, item.description, brand, category, supplier].join(" "));
}

export type CatalogQuery = {
  query?: string;
  categoryId?: string;
  kind?: CategoryKind;
  minPrice?: number;
  maxPrice?: number;
};

export function searchCatalog({
  query,
  categoryId,
  kind,
  minPrice,
  maxPrice,
}: CatalogQuery): CatalogItem[] {
  const terms = words(query ?? "");

  return catalogItems().filter((item) => {
    if (categoryId && item.categoryId !== categoryId) return false;
    if (kind && item.kind !== kind) return false;
    if (minPrice !== undefined && item.price < minPrice) return false;
    if (maxPrice !== undefined && item.price > maxPrice) return false;
    if (terms.length === 0) return true;
    const found = haystack(item);
    return terms.every((term) =>
      found.some((word) => word.startsWith(term))
    );
  });
}
