# Changelog

Registro permanente de todo el trabajo terminado. Indexado por código de tarea
(`TD-`, `RM-`, `WL-`). Orden inverso: lo más nuevo arriba.

**Formato de cada entrada:**

```
## [CÓDIGO] Título (YYYY-MM-DD HH:MM)
Resumen en ≤2 líneas de lo que se hizo.
```

---

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
