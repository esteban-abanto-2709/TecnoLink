# CLAUDE.md

Guía para Claude Code en este repositorio.

## Qué es TecnoLink

Plataforma web que centraliza equipos y servicios tecnológicos de proveedores de
Lima Metropolitana: buscar, comparar, cotizar y comprar en un solo lugar.
Proyecto del curso Marcos de Desarrollo Web. Ver [README.md](README.md) para el
panorama, `docs/logbook/` para el estado del front y `docs/backend/` para el del
backend.

**Al empezar cualquier conversación, leer primero
[`docs/informe-marcos-de-desarrollo-web.md`](docs/informe-marcos-de-desarrollo-web.md).**
Ahí está la idea completa del proyecto: problema, actores, módulos, alcance, Lean
Canvas y las 20 historias de usuario. Es el punto de partida, no un anexo.

**Etapa actual.** El front (`apps/web`) es un prototipo navegable de punta a
punta con datos mock, sin pasarela de pago. El backend (`apps/api`) se construye
aparte siguiendo [`docs/backend/plan-backend.md`](docs/backend/plan-backend.md);
conectar ambos es el plan 11 de ese archivo. No hay app móvil.

## Estructura

- `apps/web/` — app **Next.js autónoma**. Tiene su propio lockfile y
  `node_modules`; **NO es un monorepo pnpm**.
- `apps/api/` — API **Spring Boot** (Maven). Antes vivía en el repo
  `TecnoLink-backend` del grupo; se trajo con su historial. Al trabajar en el
  backend, leer primero [`docs/backend/plan-backend.md`](docs/backend/plan-backend.md):
  reglas, estructura del profesor y forma de trabajar.
- `docs/` — material conceptual del proyecto, en markdown.
- `docs/backend/` — plan, modelo de datos, responsables, integración con el front
  y el logbook propio del backend.
- `docs/originales/` — los entregables tal cual salen del grupo (`.docx`, `.pdf`).
  No se editan acá; son el original.
- `docs/logbook/` — seguimiento del proyecto (ver abajo).

**Al agregar una ruta, agregarla también al mapa del pie de página**
(`src/components/site-footer.tsx`). Es cómo el equipo descubre las pantallas
mientras dure el prototipo, y `pnpm check` falla si alguna queda fuera.

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
- `pnpm check` — verifica los datos mock (ids únicos, referencias que existen,
  totales que cuadran) y que el mapa del pie de página cubra **todas** las rutas,
  una a una. Correrlo al tocar `src/data/` o al agregar una ruta.
- **No levantar el dev server** (`pnpm dev`); la validación se hace con `build`.

## Comandos (dentro de `apps/api`)

- `./mvnw test` — con Java 21 o superior:
  `JAVA_HOME=C:/Users/Esteban/.jdks/openjdk-24.0.1`. El Java por defecto es el 17.
- **No levantar el servidor**; Esteban lo corre desde IntelliJ.

## Stack

- Front: Next.js 16 + React 19 (App Router, `src/`), Tailwind CSS v4,
  TypeScript (strict), pnpm
- Back: Spring Boot 4.1 + Java 21, Maven, MySQL

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
  (`src/lib/cart.ts`) y la sesión (`src/lib/session.ts`) son el patrón: un módulo
  con su valor, `useSyncExternalStore` para leerlo y funciones sueltas para
  mutarlo. Sin provider en el layout, y con `getServerSnapshot` para que la
  hidratación no reviente. Lo que se guarda entre recargas va a `localStorage`
  dentro del mismo módulo.
- **Mock abajo, aportes del usuario encima.** `src/data/` es la base inmutable; lo
  que el usuario genera (pedidos, reseñas) vive en su store local y se fusiona con
  el mock **solo en componentes cliente** (`Rating`, `ReviewList`, `/orders`,
  `/compare`). Un componente servidor ve únicamente el mock, así que cualquier
  pantalla que deba reflejar lo que hizo el usuario tiene que ser cliente.
- **El proveedor activo sale de la sesión.** Las pantallas de `/supplier` usan
  `useActiveSupplierId()` (`src/lib/session.ts`): el proveedor que registró la
  cuenta, o `DEMO_SUPPLIER_ID` como respaldo cuando se entra sin registrarse. Los
  proveedores registrados viven en `src/lib/suppliers.ts` y se fusionan con los
  del mock; `isMockSupplier()` dice si tienen perfil público, porque
  `/suppliers/[id]` es servidor y solo ve el mock.
- **Sin guardas de rol.** Cualquier rol llega a cualquier ruta: es un prototipo y
  hay que poder recorrer las tres experiencias sin fricción. El rol activo decide
  qué muestra el menú del header y a dónde redirige al entrar, nada más.
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

El backend tiene su propio logbook en `docs/backend/logbook/`, con su propia
numeración. No se mezclan: el trabajo de `apps/api` va ahí, el de `apps/web` en
`docs/logbook/`.
