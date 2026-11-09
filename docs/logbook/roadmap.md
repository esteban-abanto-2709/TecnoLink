# Roadmap

Trabajo comprometido: lo que sí se va a hacer. Código `RM-###` (nunca se reutiliza).
Al terminar una tarea se mueve al changelog y se borra de aquí.

**Formato de cada entrada:**
- **Objetivo:** qué se quiere lograr.
- **Hecho cuando:** criterio claro de finalización.
- **Fecha** y **Estado** (Abierto / En progreso).

---

**Front (cumplido):** prototipo web navegable de punta a punta en `apps/web`, con datos mock,
que permite recorrer las experiencias completas de cliente, proveedor y administrador
(RM-001 a RM-018). Lo siguiente del front es conectarlo a la API (plan 11 del macro plan).

**Meta de la API:** la API REST que necesita el frontend (`apps/web`) para dejar los datos mock.
Los endpoints salen de lo que hoy resuelven `src/data/catalog.ts` y los stores de `src/lib/*.ts`.
El contrato JSON usa los **mismos nombres de campos** que `src/data/types.ts`, para que el front
solo cambie de dónde lee y no cómo.

Criterios de toda la API:
- Prefijo `/api/v1`. JSON en inglés; los textos que ve el usuario se quedan en el front.
- Arquitectura DDD + hexagonal: cada módulo con `domain/`, `application/`, `infrastructure/` e `interfaces/rest/`.
  Se sigue el buen estándar de Spring Boot; el estilo del profesor ya no obliga.
- **APF2 (semana 8):** datos sintéticos en repositorios en memoria, cargados con el mismo contenido de `catalog.ts`.
  **APF3 (semana 12):** MySQL con Spring Data (hoy todos los módulos ya persisten así, ver RM-029).
  **Final (semana 18):** Spring Security.
- **Lo del curso es la base, no el límite.** Se puede usar lo que no se vio en clase si se entiende y se sabe
  explicar. Swagger UI es parte del curso (semana 11) y la sustentación se hace con él.
- Errores como en el lab de la semana 6: `NotFoundException` → `404 {"error": ...}`, validación →
  `400 {"errors": [...]}`, reglas de dominio (`IllegalArgumentException`) → `400 {"error": ...}`, y conflictos de
  estado (`IllegalStateException`) → `409 {"error": ...}`, agregado con el módulo `quotes`.
- El usuario va **en la ruta**: `/api/v1/users/{userId}/...` (puntos, compras, cotizaciones del cliente).
  Los datos de ejemplo son del usuario `demo-client`. Hasta RM-028 cualquier `userId` es válido.
- CORS se agrega al conectar el front, con `@CrossOrigin` en los controllers.
- Se quedan en el cliente (`localStorage`), sin endpoint: **carrito** (`lib/cart.ts`) y el cambio de rol del menú
  (`switchRole`, propio del prototipo). El comparador sí tiene API: `/comparisons` del módulo `quotes` (RM-023).
- Ids: se exponen los mismos ids **string tipo slug** del front (`"laptops"`, `"techperu"`), para no tocar el front.
- Enums en mayúsculas, como los del profesor (`PRODUCT`, `FIXED`, `SENT`...). La capa `src/data` del front los
  pasa a minúsculas al conectarse.

## Macro plan

Cada fila es un plan; cada paso de un plan es un commit. Se avanza en este orden porque
cada módulo usa lo del anterior. Los dueños son los de [`responsabilidades.md`](../responsabilidades.md).

| # | Módulo (dueño) | Plan | RM | Estado |
|---|---|---|---|---|
| 1 | `shared` (Esteban) | Base compartida: manejo de errores | RM-019 | Hecho |
| 2 | `catalog` (Carlos) | Lectura del catálogo: categorías, producto, servicio, búsqueda | RM-020 | Hecho |
| 3 | `catalog` (Carlos) | Escrituras del catálogo: (1) admin de categorías · (2) crear y editar productos y servicios | RM-027 (categorías) | Hecho |
| 4 | `suppliers` (Esperanza) | Proveedores: perfil, su oferta y el nombre del proveedor en la búsqueda | RM-021 | Hecho |
| 5 | `suppliers` (Esperanza) | Publicaciones del proveedor y verificación por el admin | RM-026 + RM-027 (proveedores) | Hecho |
| 6 | `loyalty` (Esperanza) | Puntos y beneficios. Antes que reseñas y compras, porque ambas suman puntos | RM-025 | Hecho |
| 7 | `reviews` (Esperanza) | Reseñas (+50 puntos) | RM-022 | Parcial: dominio y JPA; faltan servicio y endpoints |
| 8 | `quotes` (Esteban) | Cotizaciones y comparador | RM-023 | Hecho |
| 9 | `orders` (Leyla) | Compra simulada (+ puntos) | RM-024 | Parcial: dominio y JPA; faltan servicio y endpoints |
| 10 | `iam` (Benjamin) | Registro e inicio de sesión | RM-028 | Parcial: dominio y JPA; faltan servicio y endpoints |
| 11 | Front (Esteban) | Conectar `apps/web` a la API, aplicando [`api.md`](../api.md), y CORS con `@CrossOrigin` | — | Pendiente |

Fuera de este plan: RM-029 (MySQL, APF3; en progreso) y RM-030 (Spring Security, entrega final).

Usuario de cada petición (decidido en el plan 6): va en la ruta, `/api/v1/users/{userId}/...`, leído con
`@PathVariable`. Los datos de ejemplo (movimientos, compras) son del usuario `demo-client`. Hasta RM-028 cualquier
`userId` es válido (uno desconocido tiene 0 puntos y nada registrado).

## [RM-022] Reseñas y valoraciones
- **Objetivo:** reemplazar `reviewsFor`, `ratingFor` y el store `lib/reviews.ts` (`ReviewList`, `Rating`,
  `ReviewDialog`, `SupplierReviewDialog`). `targetId` puede ser un producto, un servicio o un proveedor.

  | Método | Ruta | Body / Respuesta |
  |---|---|---|
  | GET | `/api/v1/reviews?targetId={id}` | `{ reviews: Review[], average: number \| null }`, con el promedio redondeado a 1 decimal |
  | POST | `/api/v1/reviews` | `{ targetId, rating, comment }` → `201 Review`. El autor es el usuario actual |

  Regla: una reseña por usuario y por destino (`400` si repite). Publicar una reseña suma **50 puntos** (`REVIEW_POINTS`, ver RM-025).
- **Hecho cuando:** una reseña creada aparece en el GET de su destino, el promedio cambia y no se puede reseñar dos veces lo mismo.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

## [RM-024] Pedidos: compra simulada
- **Objetivo:** reemplazar `placeOrder` y `useOrders` de `lib/orders.ts` (`/checkout`, `/orders`).
  Sin pasarela de pago: el pedido se registra como hecho.

  | Método | Ruta | Body / Respuesta |
  |---|---|---|
  | POST | `/api/v1/orders` | `{ lines: [{ itemId, quantity }] }` → `201 Order` (`id, date, lines[{itemId, name, quantity, price}], total, pointsEarned`) |
  | GET | `/api/v1/orders` | `Order[]` del cliente actual, del más reciente al más antiguo |

  El **servidor** toma nombre y precio del catálogo (no confía en el precio que manda el cliente), calcula `total`,
  `pointsEarned = floor(total / 10)` (`pointsFor`) y genera el código `TL-AAAA-####`.
- **Hecho cuando:** un pedido creado aparece en `/orders` con el total y los puntos correctos, y un `itemId` inexistente da 400.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

## [RM-028] Registro e inicio de sesión
- **Objetivo:** reemplazar `signIn` y `registerSupplier` (`/login`, `/register`) y resolver quién hace cada petición.

  | Método | Ruta | Body / Respuesta |
  |---|---|---|
  | POST | `/api/v1/auth/register` | `{ name, email, password, role }` y, si `role = "supplier"`, además `{ ruc, phone, district, description }` → `201 Session` |
  | POST | `/api/v1/auth/login` | `{ email, password }` → `Session` (`name, email, role, supplierId?`). `401` si no coincide |
  | GET | `/api/v1/auth/me` | `Session` del usuario actual |

  `role` ∈ `client | supplier | admin`. Registrar un proveedor crea también su `Supplier` (`since` = año actual,
  `verified = false`). Las contraseñas se guardan hasheadas. Los tokens y la protección por rol se hacen en RM-030.
- **Hecho cuando:** se puede registrar una cuenta de cada rol, iniciar sesión con ella, y `me` devuelve la sesión.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

## [RM-029] Persistencia en MySQL (APF3)
- **Objetivo:** pasar los repositorios en memoria a Spring Data JPA + MySQL con las 4 piezas del profesor
  (entity, JpaRepository, assembler, adapter) sin tocar el dominio ni los controllers, y dejar los scripts
  de creación y carga inicial en `/scripts`, como pide la rúbrica.
- **Avance:** todos los módulos ya persisten con JPA en MySQL (las tablas las crea Hibernate con `ddl-auto=update`).
  MySQL se levanta con Docker Compose desde `apps/docker` y los datos de prueba están en `scripts/seed.sql`.
  Faltan: el script de creación de las tablas en `/scripts` y borrar los repositorios en memoria que quedaron sin
  uso (TD-009).
- **Hecho cuando:** con MySQL levantado y los scripts aplicados, todos los endpoints de RM-020 a RM-028 responden igual que en memoria y los datos sobreviven a un reinicio.
- **Fecha:** 2026-10-02 · **Estado:** En progreso

## [RM-030] Seguridad con Spring Security y JWT (entrega final)
- **Objetivo:** proteger la API: el login devuelve un JWT, el usuario actual sale del token, y cada ruta exige
  su rol (`/admin/**` solo admin; publicar y responder cotizaciones solo el proveedor dueño; pedidos, puntos y
  cotizaciones propias solo el cliente). El catálogo, los proveedores y las reseñas siguen siendo públicos en lectura.
- **Hecho cuando:** sin token, las rutas protegidas dan 401; con el rol equivocado dan 403; con el rol correcto responden.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

## [RM-037] Calidad técnica del módulo `quotes`
- **Objetivo:** dejar `quotes` correcto en DDD, hexagonal y SOLID sin agregar lo que el alcance no necesita: errores
  consistentes, reglas entre módulos fuera de los adaptadores, consultas agrupadas por agregado y dominio completo
  (montos como value object, vencimiento por fecha, observaciones de HU-09 y tests).
- **Hecho cuando:** ningún dato inválido da 500, `quotes` no importa repositorios de otros módulos y las reglas del
  dominio tienen tests que corren sin MySQL.
- **Fecha:** 2026-11-08 · **Estado:** En progreso (2026-11-08)

> Las entradas de esta página y el macro plan vienen del repo `TecnoLink-backend` del grupo, importado en `apps/api` el 2026-11-07. Se renumeraron a continuación de las del front: `RM-n` pasó a `RM-(n+18)`.
