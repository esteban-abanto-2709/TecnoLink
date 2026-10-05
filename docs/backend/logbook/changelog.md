# Changelog

Registro permanente de todo el trabajo terminado. Indexado por código de tarea
(`TD-`, `RM-`, `WL-`). Orden inverso: lo más nuevo arriba.

**Formato de cada entrada:**

```
## [CÓDIGO] Título (YYYY-MM-DD HH:MM)
Resumen en ≤2 líneas de lo que se hizo.
```

---

## [RM-015] Compra simulada según el diagrama de clases (2026-10-05 16:11)
Módulo `compras/`: `Carrito` con `ItemCarrito` y `Pedido` con `DetallePedido` (pago simulado, 1 punto por cada S/ 10),
con carrito y pedidos de prueba de `demo-client`. Modelo de datos agregado en `docs/modelo-datos.md`.

## [RM-014] Catálogo según el diagrama de clases (2026-10-05 16:09)
Módulo `catalogo/`: `Publicacion` abstracta con `Producto` y `Servicio`, y `Categoria`, con los 20 ítems y 9 categorías
del catálogo en memoria. Modelo de datos agregado en `docs/modelo-datos.md`.

## [RM-013] Cotizaciones y comparador: dominio y datos de prueba (2026-10-05 16:01)
Módulo `cotizaciones/` con el modelo del profesor: `SolicitudCotizacion` y `Comparacion` (aggregates), `Cotizacion` y
`Especificacion` (entities), repositorios en memoria con datos de prueba y el modelo de datos en `docs/modelo-datos.md`.

## [RM-007] Fidelización: puntos y canje de beneficios (2026-10-03 19:07)
Módulo `loyalty`: `GET /benefits`, `GET /users/{userId}/points` (saldo calculado de los movimientos) y canje
`POST /users/{userId}/points/redemptions` (saldo suficiente, una vez por beneficio). `awardPoints` para reseñas y compras.

## [RM-009] Administración: categorías y verificación de proveedores (2026-10-03 18:00)
Admin de categorías (crear, renombrar, activar/desactivar) en `catalog` y revisión de proveedores en `suppliers`
(`VERIFIED`, `PENDING`, `SUSPENDED` con nota y fecha). `verified` se calcula del estado; un suspendido no publica.

## [RM-008] Publicaciones del proveedor (2026-10-03 17:57)
`POST /suppliers/{id}/products|services` y `PUT /suppliers/{id}/products|services/{itemId}`, con DTOs validados.
El proveedor solo edita lo suyo (404 si no); las reglas de categoría las aplica `CatalogService`.

## [RM-003] Proveedores: perfil público y su oferta (2026-10-03 17:53)
Módulo `suppliers` con los 5 proveedores del mock: `GET /suppliers`, `/suppliers/{id}`, `/suppliers/{id}/products` y `/services`.
La búsqueda `GET /catalog` también encuentra por nombre del proveedor (el controller le pasa los nombres al servicio).

## [RM-002] Catálogo: categorías, productos, servicios y búsqueda (2026-10-02 18:30)
Módulo `catalog` con datos en memoria iguales al mock del front: `GET /categories`, `/products/{id}`, `/services/{id}`,
búsqueda `GET /catalog` (sin tildes, por prefijo, con filtros) y lote para el comparador `GET /products?ids=` (máx. 4).

## [RM-001] Esqueleto hexagonal y piezas transversales (2026-10-02 17:59)
Módulo `shared` con el manejo de errores del lab de la semana 6: `NotFoundException` → 404 y `RestExceptionHandler`
(404, 400 de validación con `spring-boot-starter-validation`, 400 por `IllegalArgumentException`).

## [TD-001] La app no arranca: JPA y MySQL sin datasource (2026-10-02 08:13)
Se quitaron `spring-boot-starter-data-jpa`, `mysql-connector-j` y `spring-boot-starter-data-jpa-test` del `pom.xml`:
el APF2 trabaja con datos en memoria. Vuelven en RM-011, junto con el adaptador JPA.
