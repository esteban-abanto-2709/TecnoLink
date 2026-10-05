# Roadmap

Trabajo comprometido: lo que sí se va a hacer. Código `RM-###` (nunca se reutiliza).
Al terminar una tarea se mueve al changelog y se borra de aquí.

**Formato de cada entrada:**
- **Objetivo:** qué se quiere lograr.
- **Hecho cuando:** criterio claro de finalización.
- **Fecha** y **Estado** (Abierto / En progreso).

---

**Meta:** la API REST que necesita el frontend (`tecnolink-frontend/apps/web`) para dejar los datos mock.
Los endpoints salen de lo que hoy resuelven `src/data/catalog.ts` y los stores de `src/lib/*.ts`.
El contrato JSON usa los **mismos nombres de campos** que `src/data/types.ts`, para que el front
solo cambie de dónde lee y no cómo.

Criterios de toda la API:
- Prefijo `/api/v1`. JSON en inglés; los textos que ve el usuario se quedan en el front.
- Arquitectura del profesor (DDD + hexagonal): cada módulo con `domain/`, `application/`,
  `infrastructure/` e `interfaces/rest/`. Ver `marcos-de-desarrollo-web/procesado/estructura-backend.md`.
- **APF2 (semana 8):** datos sintéticos en repositorios en memoria, cargados con el mismo contenido de `catalog.ts`.
  **APF3 (semana 12):** MySQL con Spring Data. **Final (semana 18):** Spring Security.
- **Solo lo que enseña el curso.** Nada de librerías ni clases que no salgan en las clases o labs
  (sin Swagger, sin configuración extra). Las pruebas se hacen con el navegador, Postman o el cliente HTTP de IntelliJ.
- Errores como en el lab de la semana 6: `NotFoundException` → `404 {"error": ...}`, validación →
  `400 {"errors": [...]}`, reglas de dominio (`IllegalArgumentException`) → `400 {"error": ...}`.
- El usuario va **en la ruta**: `/api/v1/users/{userId}/...` (puntos, compras, cotizaciones del cliente).
  Los datos de ejemplo son del usuario `demo-client`. Hasta RM-010 cualquier `userId` es válido.
- CORS se agrega al conectar el front, con `@CrossOrigin` en los controllers.
- Se quedan en el cliente (`localStorage`), sin endpoint: **carrito** (`lib/cart.ts`), **comparador**
  (`lib/compare.ts`, solo guarda ids) y el cambio de rol del menú (`switchRole`, propio del prototipo).
- Ids: se exponen los mismos ids **string tipo slug** del front (`"laptops"`, `"techperu"`), para no tocar el front.
- Enums en mayúsculas, como los del profesor (`PRODUCT`, `FIXED`, `SENT`...). La capa `src/data` del front los
  pasa a minúsculas al conectarse.

## [RM-004] Reseñas y valoraciones
- **Objetivo:** reemplazar `reviewsFor`, `ratingFor` y el store `lib/reviews.ts` (`ReviewList`, `Rating`,
  `ReviewDialog`, `SupplierReviewDialog`). `targetId` puede ser un producto, un servicio o un proveedor.

  | Método | Ruta | Body / Respuesta |
  |---|---|---|
  | GET | `/api/v1/reviews?targetId={id}` | `{ reviews: Review[], average: number \| null }`, con el promedio redondeado a 1 decimal |
  | POST | `/api/v1/reviews` | `{ targetId, rating, comment }` → `201 Review`. El autor es el usuario actual |

  Regla: una reseña por usuario y por destino (`400` si repite). Publicar una reseña suma **50 puntos** (`REVIEW_POINTS`, ver RM-007).
- **Hecho cuando:** una reseña creada aparece en el GET de su destino, el promedio cambia y no se puede reseñar dos veces lo mismo.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

## [RM-005] Cotizaciones
- **Objetivo:** reemplazar `lib/quotes.ts` (`/quotes`, `/quotes/new`, `/supplier/quotes`, `QuoteForm`, `QuoteAnswerDialog`).

  | Método | Ruta | Body / Respuesta |
  |---|---|---|
  | POST | `/api/v1/quotes` | `{ itemId, supplierId, quantity, requirement }` → `201 Quote` con `status: "sent"` y `requestedOn` de hoy |
  | GET | `/api/v1/quotes` | `Quote[]` del cliente actual |
  | GET | `/api/v1/suppliers/{id}/quotes` | `Quote[]` recibidas por el proveedor |
  | POST | `/api/v1/quotes/{id}/answer` | `{ price, validUntil, conditions, notes }` → `Quote` con `status: "answered"` y `answer` |

  `status` ∈ `sent | answered | expired`. Una cotización respondida no se vuelve a responder (`400`).
- **Hecho cuando:** el flujo completo (el cliente pide, el proveedor la ve en su bandeja y responde, el cliente ve la respuesta) funciona solo con la API.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

## [RM-006] Pedidos: compra simulada
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

## [RM-010] Registro e inicio de sesión
- **Objetivo:** reemplazar `signIn` y `registerSupplier` (`/login`, `/register`) y resolver quién hace cada petición.

  | Método | Ruta | Body / Respuesta |
  |---|---|---|
  | POST | `/api/v1/auth/register` | `{ name, email, password, role }` y, si `role = "supplier"`, además `{ ruc, phone, district, description }` → `201 Session` |
  | POST | `/api/v1/auth/login` | `{ email, password }` → `Session` (`name, email, role, supplierId?`). `401` si no coincide |
  | GET | `/api/v1/auth/me` | `Session` del usuario actual |

  `role` ∈ `client | supplier | admin`. Registrar un proveedor crea también su `Supplier` (`since` = año actual,
  `verified = false`). Las contraseñas se guardan hasheadas. Los tokens y la protección por rol se hacen en RM-012.
- **Hecho cuando:** se puede registrar una cuenta de cada rol, iniciar sesión con ella, y `me` devuelve la sesión.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

## [RM-011] Persistencia en MySQL (APF3)
- **Objetivo:** pasar los repositorios en memoria a Spring Data JPA + MySQL con las 4 piezas del profesor
  (entity, JpaRepository, assembler, adapter) sin tocar el dominio ni los controllers, y dejar los scripts
  de creación y carga inicial en `/scripts`, como pide la rúbrica.
- **Hecho cuando:** con MySQL levantado y los scripts aplicados, todos los endpoints de RM-002 a RM-010 responden igual que en memoria y los datos sobreviven a un reinicio.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

## [RM-012] Seguridad con Spring Security y JWT (entrega final)
- **Objetivo:** proteger la API: el login devuelve un JWT, el usuario actual sale del token, y cada ruta exige
  su rol (`/admin/**` solo admin; publicar y responder cotizaciones solo el proveedor dueño; pedidos, puntos y
  cotizaciones propias solo el cliente). El catálogo, los proveedores y las reseñas siguen siendo públicos en lectura.
- **Hecho cuando:** sin token, las rutas protegidas dan 401; con el rol equivocado dan 403; con el rol correcto responden.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

