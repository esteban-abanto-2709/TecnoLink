# Changelog

Registro permanente de todo el trabajo terminado. Indexado por código de tarea
(`TD-`, `RM-`, `WL-`). Orden inverso: lo más nuevo arriba.

**Formato de cada entrada:**

```
## [CÓDIGO] Título (YYYY-MM-DD HH:MM)
Resumen en ≤2 líneas de lo que se hizo.
```

---

## [RM-013] Publicaciones del proveedor (2026-08-31 14:32)
Alta y edición de equipos y servicios con un solo formulario que cambia de campos según
el tipo, y lista de publicaciones que refleja los cambios. Las ediciones sobre datos de
ejemplo se guardan como superposición.

## [RM-012] Bandeja y respuesta de cotizaciones (2026-08-31 14:28)
Panel del proveedor con sus métricas y bandeja que lista las solicitudes recibidas y
permite responderlas con precio, vigencia, condiciones y observaciones. La respuesta se
guarda como superposición, así que también alcanza a las cotizaciones de ejemplo.

## [RM-011] Solicitud de cotización (2026-08-31 13:49)
Formulario de solicitud con cantidad y requerimiento desde la ficha de cualquier equipo o
servicio, y pantalla de cotizaciones con el estado de cada una y la respuesta del
proveedor en formato estándar.

## [RM-010] Perfil de proveedor y reseñas (2026-08-31 13:46)
Perfil público del proveedor con su oferta y valoración, historial de compras, y reseñas
propias que se suman al promedio en todas las pantallas. El checkout ahora guarda el
pedido, así que lo comprado aparece en "Mis compras" y habilita reseñarlo.

## [RM-009] Comparador de equipos (2026-08-31 13:20)
Selección de hasta 4 equipos desde tarjetas y fichas, con contador en el header, y tabla
que enfrenta precio, marca, proveedor, calificación y especificaciones resaltando las
filas que difieren.

## [RM-008] Acceso simulado (2026-08-31 13:12)
Registro e inicio de sesión con validación nativa, y sesión en un store igual al del
carrito. El menú del header muestra los accesos del rol activo y permite cambiarlo.

## [RM-007] Compra simulada (2026-08-31 13:04)
Carrito, checkout y confirmación con código de pedido y puntos ganados. El carrito es un
store con `useSyncExternalStore` respaldado en `localStorage`, sin provider ni contexto.

## [RM-006] Detalle de producto y de servicio (2026-08-31 12:57)
Fichas de producto (especificaciones) y de servicio (modalidad de cobro y cobertura), con
proveedor, reseñas y acciones de comprar, cotizar y comparar. Un id inexistente da 404.

## [RM-005] Resultados y filtros (2026-08-31 12:54)
Filtros de tipo, categoría y rango de precio en `/search`, resueltos con enlaces: cada
opción es una URL compartible y el servidor filtra. Se combinan entre sí y con el término.

## [RM-004] Inicio y búsqueda (2026-08-31 12:48)
Portada con buscador, las 9 categorías y destacados, y `/search` mostrando resultados
reales. La búsqueda ignora tildes y mayúsculas, exige todos los términos y hace prefijo
por palabra; el filtro por categoría entró junto con ella.

## [RM-003] Datos mock del catálogo (2026-08-31 12:41)
Fuente única en `src/data/`: 9 categorías, 5 proveedores, 14 productos con especificaciones
comparables, 6 servicios, reseñas, cotizaciones, compras y puntos. `pnpm check` valida
referencias y totales; precios y fechas se formatean en `src/lib/format.ts`.

## [RM-002] Shell de navegación y mapa de rutas (2026-08-31 12:35)
Header con buscador y accesos, y footer que hace de mapa a las 21 rutas del prototipo.
Cada pantalla pendiente existe con un estado vacío que dice qué RM la construye.

## [RM-001] Instalar shadcn/ui y fijar el sistema visual base (2026-08-31 12:29)
shadcn/ui con preset `base-nova` (primitivos Base UI, íconos lucide) sobre Tailwind v4,
y paleta propia en oklch —azul de marca, ámbar de acento— con tipografías Plus Jakarta
Sans para títulos e Inter para cuerpo. La portada quedó como página de muestra del tema.
