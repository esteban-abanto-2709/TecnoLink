# TecnoLink

> Nombre de trabajo del proyecto descrito en *Plataforma Web para la Búsqueda,
> Comparación y Comercialización de Equipos y Servicios Tecnológicos*.

**TecnoLink** es una plataforma web que centraliza la oferta de equipos y
servicios tecnológicos de proveedores de Lima Metropolitana, para que un cliente
pueda buscar, comparar, cotizar y comprar desde un solo lugar.

Proyecto del curso **Marcos de Desarrollo Web**.

## El problema que resuelve

La oferta tecnológica de Lima está repartida entre redes sociales, páginas web,
marketplaces y tiendas físicas. El cliente tiene que revisar varios medios para
comparar precios, características y referencias de proveedores; y muchos usuarios
solo tienen conocimientos básicos de tecnología, así que comparar alternativas es
difícil.

Del otro lado, los proveedores pequeños y medianos no compiten en posicionamiento
digital contra las grandes plataformas de comercio electrónico, y sus productos
quedan sin exposición.

## Actores

| Actor | Qué hace |
|---|---|
| **Cliente** | Busca, filtra, compara equipos, consulta proveedores, solicita cotizaciones, compra (simulado), acumula puntos y deja reseñas. |
| **Proveedor** | Publica y administra sus productos y servicios, y responde solicitudes de cotización. |
| **Administrador** | Gestiona categorías, proveedores, usuarios y el contenido publicado. |

## Módulos

- **Catálogo y búsqueda** — categorías, búsqueda por texto y filtros.
- **Comparador** — atributos de varios equipos en columnas.
- **Cotizaciones** — solicitud y respuesta con un formato estandarizado.
- **Compra** — flujo completo, sin transacción monetaria real.
- **Proveedores** — perfil, oferta y reputación.
- **Reseñas** — calificación y comentario asociados a una compra.
- **Fidelización** — puntos y beneficios por compras.
- **Gestión del proveedor** — publicaciones y bandeja de cotizaciones.
- **Administración** — categorías, proveedores, usuarios y contenido.

## Alcance de esta etapa

Solo **Front-End**: interfaces y navegación simulada. Sin backend, sin logística
de entrega y **sin pasarela de pago real** — el flujo de compra se completa dentro
de la plataforma pero no mueve dinero. La primera versión apunta a proveedores y
clientes de Lima Metropolitana.

## Cómo está construido

- **Next.js 16** + **React 19** (App Router) — `apps/web`, único deployable.
- **Tailwind CSS v4** · **TypeScript** (strict) · **pnpm**.

> El informe entregado lista HTML5, CSS3, Bootstrap y JavaScript. El repositorio
> usa Next.js, React y Tailwind; falta reconciliar esa tabla con el docente.

## Estructura del repositorio

```
TecnoLink/
├── apps/
│   └── web/                                   # Aplicación Next.js (autónoma, con su propio lockfile)
│       ├── src/
│       │   └── app/                           # Rutas (App Router)
│       └── public/
└── docs/
    ├── informe-marcos-de-desarrollo-web.md    # El informe, legible y versionable
    ├── originales/                            # Los entregables tal cual (.docx, .pdf)
    └── logbook/                               # Seguimiento del proyecto
```

`docs/` es el punto de partida del proyecto. Cada entregable del grupo vive dos veces:
el original en `docs/originales/` y su derivado en markdown al lado, con el mismo
nombre. Si el original cambia, el markdown se regenera en el mismo commit.

## Estado

Arrancando. Proyecto Next.js inicializado; falta construir las pantallas.

Lo que sigue es un **prototipo navegable** de punta a punta con datos mock: recorrer
las experiencias de cliente, proveedor y administrador completas, sin backend. El
trabajo comprometido está en [`docs/logbook/roadmap.md`](docs/logbook/roadmap.md)
(`RM-001` … `RM-016`).

## Equipo

- Abanto García, Wilder Esteban — U23310744
- Gallardo Sicha, Benjamin Estuar — U24252186
- Condori Zegovia, Carlos Alberto
- Naragio Chávez, Leyla Viviana — U24219412

Docente: Robles Fernandez, Ivan · Sección 45315 · Lima, 2026
