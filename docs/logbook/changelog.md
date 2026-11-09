# Changelog

Registro permanente de todo el trabajo terminado. Indexado por código de tarea
(`TD-`, `RM-`, `WL-`). Orden inverso: lo más nuevo arriba.

**Formato de cada entrada:**

```
## [CÓDIGO] Título (YYYY-MM-DD HH:MM)
Resumen en ≤2 líneas de lo que se hizo.
```


---

## [RM-037] Calidad técnica del módulo `quotes` (2026-11-09 00:46)
Errores consistentes, reglas entre módulos en el puerto `QuoteReferences`, consultas por agregado y dominio completo:
`QuoteAmounts`, vencimiento por fecha, `notes` (HU-09), hora de Lima, eventos con id y 25 tests que corren sin MySQL.

## [TD-011] Un id no numérico en `quotes` expone un mensaje interno (2026-11-08 23:00)
`RestExceptionHandler` responde `400 {"error": "Invalid value for parameter id"}` cuando un parámetro no se puede
convertir, en lugar del mensaje interno de Java.

## [RM-036] API en Docker y verificación de endpoints (2026-11-08 11:39)
API y MySQL en Docker Compose (`apps/docker`, `apps/api/Dockerfile`), con Actuator para el healthcheck, datos de prueba
en `scripts/seed.sql` y verificación con `curl` sin Java local ni IntelliJ.

## [RM-023] Cotizaciones y comparador (2026-11-02 13:00)
Módulo `quotes/` al estilo del profesor (DDD + CQRS + JPA + Swagger): solicitudes con cotización (IGV 18 %, vencimiento),
especificaciones de producto y comparador de 2 a 4 productos con cuadro comparativo. Rutas en `docs/integracion-frontend.md`.

## [RM-035] Usuarios según el diagrama de clases (2026-10-05 16:15)
Módulo `usuarios/`: `Usuario` abstracta con `Cliente` y `Administrador` (registro, inicio y cierre de sesión, perfil),
y `Proveedor` pasa a heredar de `Usuario`. Datos de prueba en memoria y modelo de datos en `docs/modelo-datos.md`.

## [RM-034] Proveedor, reseñas y fidelización según el diagrama de clases (2026-10-05 16:13)
Módulos `proveedores/` (`Proveedor` con reputación calculada, `Resena`) y `fidelizacion/` (`CuentaFidelizacion` con
`MovimientoPuntos` y nivel), con datos de prueba en memoria. Modelo de datos agregado en `docs/modelo-datos.md`.

## [RM-033] Compra simulada según el diagrama de clases (2026-10-05 16:11)
Módulo `compras/`: `Carrito` con `ItemCarrito` y `Pedido` con `DetallePedido` (pago simulado, 1 punto por cada S/ 10),
con carrito y pedidos de prueba de `demo-client`. Modelo de datos agregado en `docs/modelo-datos.md`.

## [RM-032] Catálogo según el diagrama de clases (2026-10-05 16:09)
Módulo `catalogo/`: `Publicacion` abstracta con `Producto` y `Servicio`, y `Categoria`, con los 20 ítems y 9 categorías
del catálogo en memoria. Modelo de datos agregado en `docs/modelo-datos.md`.

## [RM-031] Cotizaciones y comparador: dominio y datos de prueba (2026-10-05 16:01)
Módulo `cotizaciones/` con el modelo del profesor: `SolicitudCotizacion` y `Comparacion` (aggregates), `Cotizacion` y
`Especificacion` (entities), repositorios en memoria con datos de prueba y el modelo de datos en `docs/modelo-datos.md`.

## [RM-025] Fidelización: puntos y canje de beneficios (2026-10-03 19:07)
Módulo `loyalty`: `GET /benefits`, `GET /users/{userId}/points` (saldo calculado de los movimientos) y canje
`POST /users/{userId}/points/redemptions` (saldo suficiente, una vez por beneficio). `awardPoints` para reseñas y compras.

## [RM-027] Administración: categorías y verificación de proveedores (2026-10-03 18:00)
Admin de categorías (crear, renombrar, activar/desactivar) en `catalog` y revisión de proveedores en `suppliers`
(`VERIFIED`, `PENDING`, `SUSPENDED` con nota y fecha). `verified` se calcula del estado; un suspendido no publica.

## [RM-026] Publicaciones del proveedor (2026-10-03 17:57)
`POST /suppliers/{id}/products|services` y `PUT /suppliers/{id}/products|services/{itemId}`, con DTOs validados.
El proveedor solo edita lo suyo (404 si no); las reglas de categoría las aplica `CatalogService`.

## [RM-021] Proveedores: perfil público y su oferta (2026-10-03 17:53)
Módulo `suppliers` con los 5 proveedores del mock: `GET /suppliers`, `/suppliers/{id}`, `/suppliers/{id}/products` y `/services`.
La búsqueda `GET /catalog` también encuentra por nombre del proveedor (el controller le pasa los nombres al servicio).

## [RM-020] Catálogo: categorías, productos, servicios y búsqueda (2026-10-02 18:30)
Módulo `catalog` con datos en memoria iguales al mock del front: `GET /categories`, `/products/{id}`, `/services/{id}`,
búsqueda `GET /catalog` (sin tildes, por prefijo, con filtros) y lote para el comparador `GET /products?ids=` (máx. 4).

## [RM-019] Esqueleto hexagonal y piezas transversales (2026-10-02 17:59)
Módulo `shared` con el manejo de errores del lab de la semana 6: `NotFoundException` → 404 y `RestExceptionHandler`
(404, 400 de validación con `spring-boot-starter-validation`, 400 por `IllegalArgumentException`).

## [TD-005] La app no arranca: JPA y MySQL sin datasource (2026-10-02 08:13)
Se quitaron `spring-boot-starter-data-jpa`, `mysql-connector-j` y `spring-boot-starter-data-jpa-test` del `pom.xml`:
el APF2 trabaja con datos en memoria. Vuelven en RM-029, junto con el adaptador JPA.

> Las entradas de arriba vienen del repo `TecnoLink-backend` del grupo, importado en `apps/api` el 2026-11-07. Se renumeraron a continuación de las del front: `RM-n` pasó a `RM-(n+18)` y `TD-n` a `TD-(n+4)`.

## [RM-018] Registro diferenciado para proveedores (2026-09-04 12:31)
El registro cambia de campos según el tipo de cuenta: el proveedor carga razón social,
RUC, distrito, teléfono y descripción, y esa cuenta pasa a ser el proveedor activo de
todas las pantallas de `/supplier`, que antes apuntaban a uno fijo.

## [RM-017] Puntos por publicar una reseña (2026-09-04 11:46)
Publicar una reseña suma 50 puntos, derivados del store de reseñas en vez de guardarse
como movimiento aparte. La regla se anuncia antes de escribir y se confirma después.

## [RM-016] Pasada responsive (2026-08-31 14:56)
Barrido de las 23 pantallas entre 320 y 1280 px: sin desbordes horizontales. Se corrigió
el header en pantallas chicas y se rehizo la fila del carrito, y los controles de cantidad
pasaron a 36 px en móvil.

## [RM-015] Administración (2026-08-31 14:38)
Panel de administración con gestión de categorías —alta, edición, desactivación y control
de duplicados— y revisión de proveedores con tres estados y observación registrada.

## [RM-014] Fidelización (2026-08-31 14:35)
Pantalla de puntos con saldo, movimientos y catálogo de beneficios separando disponibles
de usados, con canje real. Los movimientos de compra se derivan de los pedidos, así que
no hay una segunda fuente de verdad para los puntos.

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
