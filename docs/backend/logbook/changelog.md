# Changelog

Registro permanente de todo el trabajo terminado. Indexado por código de tarea
(`TD-`, `RM-`, `WL-`). Orden inverso: lo más nuevo arriba.

**Formato de cada entrada:**

```
## [CÓDIGO] Título (YYYY-MM-DD HH:MM)
Resumen en ≤2 líneas de lo que se hizo.
```

---

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
