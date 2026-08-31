# Deuda Técnica

Registro de atajos, decisiones pendientes y riesgos a futuro de este proyecto.
Código `TD-###` (nunca se reutiliza). Al resolverse, la entrada se mueve al
changelog y se borra de aquí.

**Formato de cada entrada:**
- **Ubicación:** `archivo:línea` afectado.
- **Riesgo:** del 1 al 10 (1-3 cosmético · 4-6 ralentiza/moderado · 7-9 bug latente o seguridad · 10 crítico).
- **Problema:** qué está mal, sintetizado.
- **Impacto futuro:** qué puede causar si no se atiende.
- **Fecha** y **Estado** (Abierto / En progreso).

---

## [TD-003] Los controles táctiles quedan por debajo de 44 px
- **Ubicación:** `apps/web/src/components/ui/button.tsx`
- **Riesgo:** 3/10
- **Problema:** la escala de `base-nova` da botones de 28 a 32 px de alto. Las pautas de
  accesibilidad piden 44 px de área táctil. En el carrito y el comparador se subió a 36 px
  en móvil con una clase puntual, pero el resto de la interfaz sigue en la escala chica.
- **Impacto futuro:** en celular cuesta acertarle a los botones secundarios. Resolverlo
  bien es tocar la escala del sistema de diseño, no cada pantalla, así que conviene
  decidirlo de una vez antes de que haya más pantallas.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [TD-002] Lo que publica el proveedor no llega al catálogo público
- **Ubicación:** `apps/web/src/app/search/page.tsx`, `apps/web/src/app/products/[id]/page.tsx`
- **Riesgo:** 5/10
- **Problema:** las publicaciones nuevas y las ediciones viven en un store de cliente,
  pero el catálogo, las fichas de detalle y el perfil del proveedor son componentes
  servidor que solo leen `src/data/`. Un equipo recién publicado no aparece en la
  búsqueda ni tiene ficha propia, así que la lista de publicaciones no enlaza a la vista
  pública para no dejar enlaces rotos.
- **Impacto futuro:** en una demo, publicar algo y no encontrarlo en el catálogo se lee
  como un bug. Cerrarlo pide que la búsqueda y el detalle pasen a cliente y fusionen el
  store, o que exista una API de verdad detrás de `src/data/`.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [TD-001] El buscador del header no conserva el término
- **Ubicación:** `apps/web/src/components/site-header.tsx`
- **Riesgo:** 3/10
- **Problema:** el input del header siempre arranca vacío. Al llegar a `/search?q=laptop`
  el término se ve en el título de la pantalla pero no en la caja, así que refinar una
  búsqueda obliga a reescribirla completa.
- **Impacto futuro:** se nota apenas se encadenan dos búsquedas, que es lo que hace
  cualquiera comparando equipos. Resolverlo pide leer `useSearchParams` en un componente
  cliente con su frontera de Suspense, o mover el buscador dentro de cada página.
- **Fecha:** 2026-08-31 · **Estado:** Abierto
