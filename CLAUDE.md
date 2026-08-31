# CLAUDE.md

Guía para Claude Code en este repositorio.

## Qué es TecnoLink

Plataforma web que centraliza equipos y servicios tecnológicos de proveedores de
Lima Metropolitana: buscar, comparar, cotizar y comprar en un solo lugar.
Proyecto del curso Marcos de Desarrollo Web. Ver [README.md](README.md) para el
panorama y `docs/logbook/` para el estado.

**Al empezar cualquier conversación, leer primero
[`docs/informe-marcos-de-desarrollo-web.md`](docs/informe-marcos-de-desarrollo-web.md).**
Ahí está la idea completa del proyecto: problema, actores, módulos, alcance, Lean
Canvas y las 20 historias de usuario. Es el punto de partida, no un anexo.

**Etapa actual: prototipo, solo Front-End.** El objetivo inmediato es un prototipo
web navegable de punta a punta, que permita recorrer las experiencias de usuario
completas. Sin backend, sin pasarela de pago, datos mock. Todo vive en `apps/web`;
no hay app móvil ni la habrá en esta etapa.

## Estructura

- `apps/web/` — app **Next.js autónoma** y único deployable. Tiene su propio
  lockfile y `node_modules`; **NO es un monorepo pnpm**. El nombre `web` es fijo:
  si más adelante hay backend, entra como `apps/api/`.
- `docs/` — material conceptual del proyecto, en markdown.
- `docs/originales/` — los entregables tal cual salen del grupo (`.docx`, `.pdf`).
  No se editan acá; son el original.
- `docs/logbook/` — seguimiento del proyecto (ver abajo).

## Documentos derivados — regla dura

Cada documento de `docs/originales/` tiene su gemelo en markdown en `docs/`, con el
**mismo nombre base**:

```
docs/originales/informe-marcos-de-desarrollo-web.docx   ← original, lo edita el grupo
docs/informe-marcos-de-desarrollo-web.md                ← derivado, lo lee Claude
```

**Si el original se actualiza, el `.md` se regenera en el mismo commit.** Un `.md`
desfasado es peor que no tenerlo: es la fuente que se lee al arrancar. El markdown
nunca se edita por su cuenta para "mejorarlo" — refleja el original, y si algo del
original está mal, se corrige el original.

## Comandos (dentro de `apps/web`)

- `pnpm build` — build de producción; **es la forma de validar** (corre el
  type-check de Next). `pnpm lint` solo revisa estilo, no tipos.
- `pnpm check` — verifica los datos mock: ids únicos, referencias que existen,
  totales que cuadran. Correrlo al tocar `src/data/`.
- **No levantar el dev server** (`pnpm dev`); la validación se hace con `build`.

## Stack

- Next.js 16 + React 19 (App Router, `src/`)
- Tailwind CSS v4
- TypeScript (strict), pnpm

## Convenciones

- **Sin comentarios en el código** salvo que se pidan; la deuda técnica se
  registra en `docs/logbook/technical-debt.md`, nunca como comentario.
- **Sin emojis en la UI** ni en el código que la genera. Íconos por librería.
- **Idioma — regla dura:** todo lo que el usuario **no ve** va en **inglés**
  (funciones, tipos, variables, rutas, nombres de archivo). Todo lo que el usuario
  **sí ve** (textos de UI) va en **español**. No mezclar.
- **Trazabilidad con el informe:** las pantallas salen de las 20 historias de
  usuario (HU-01…HU-20), sección 13 del informe derivado. Al construir una,
  referenciar su código.
- **Es un prototipo:** el estado vive en memoria (React) sobre datos mock. No se
  arma persistencia, ni auth real, ni API. Si una pantalla necesita "guardar", el
  prototipo simula el resultado y sigue.
- **Datos en un solo lugar:** `src/data/` es la única fuente. Ninguna pantalla
  define sus propios datos ni los duplica; si falta algo, se agrega ahí. Está
  aislado a propósito: el día que haya API, se reemplaza esa capa sin tocar las
  pantallas.
- **Estado de cliente en stores de módulo, no en Context.** El carrito
  (`src/lib/cart.ts`) es el patrón: un módulo con su valor, `useSyncExternalStore`
  para leerlo y funciones sueltas para mutarlo. Sin provider en el layout, y con
  `getServerSnapshot` para que la hidratación no reviente. Lo que se guarda entre
  recargas va a `localStorage` dentro del mismo módulo.
- **Precios y fechas por `src/lib/format.ts`.** Soles con `Intl` en `es-PE`, y las
  fechas ISO se parsean a fecha local — construirlas con `new Date("2026-07-14")`
  corre el día en Lima.

## Next.js 16 — ojo

Esta versión trae breaking changes respecto a versiones previas (APIs,
convenciones, estructura). Ante dudas, consultar los docs de la versión instalada
en `apps/web/node_modules/next/dist/docs/` antes de escribir código
Next-específico. `apps/web/AGENTS.md` lo recuerda y lo regenera `next dev`.

## Logbook (`docs/logbook/`)

Trabajo comprometido en `roadmap.md` (`RM-###`), deuda en `technical-debt.md`
(`TD-###`), ideas en `wishlist.md` (`WL-###`), terminado en `changelog.md`. Al
empezar una tarea se marca `En progreso`; al terminar se mueve al `changelog`
conservando su código. Los códigos nunca se reutilizan.
