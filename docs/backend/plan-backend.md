# Plan del backend (APF2)

Hoja de ruta para cerrar la API del Avance 2 y las reglas con las que se está construyendo.
**Al retomar el trabajo (por ejemplo, después de limpiar el contexto), leer este archivo primero**,
y después [`logbook/roadmap.md`](logbook/roadmap.md) (detalle de cada RM),
[`responsabilidades.md`](responsabilidades.md) (dueño de cada módulo) e
[`integracion-frontend.md`](integracion-frontend.md) (lo que cambia en el front).

## Macro plan

Cada fila es un plan; cada paso de un plan es un commit. Se avanza en este orden porque
cada módulo usa lo del anterior.

| # | Módulo (dueño) | Plan | RM | Estado |
|---|---|---|---|---|
| 1 | `shared` (Esteban) | Base compartida: manejo de errores | RM-001 | Hecho |
| 2 | `catalog` (Esteban) | Lectura del catálogo: categorías, producto, servicio, búsqueda, comparador | RM-002 | Hecho |
| 3 | `catalog` (Esteban) | Escrituras del catálogo: (1) admin de categorías · (2) crear y editar productos y servicios | RM-009 (categorías) | Hecho |
| 4 | `suppliers` (Leyla) | Proveedores: perfil, su oferta y el nombre del proveedor en la búsqueda | RM-003 | En curso |
| 5 | `suppliers` (Leyla) | Publicaciones del proveedor y verificación por el admin | RM-008 + RM-009 (proveedores) | Pendiente |
| 6 | `loyalty` (Esperanza) | Puntos y beneficios. Antes que reseñas y compras, porque ambas suman puntos | RM-007 | Pendiente |
| 7 | `reviews` (Esperanza) | Reseñas (+50 puntos) | RM-004 | Pendiente |
| 8 | `quotes` (Carlos) | Cotizaciones | RM-005 | Pendiente |
| 9 | `orders` (Carlos) | Compra simulada (+ puntos) | RM-006 | Pendiente |
| 10 | `iam` (Benjamin) | Registro e inicio de sesión | RM-010 | Pendiente |
| 11 | Front (Esteban) | Conectar `tecnolink-frontend` a la API, aplicando [`integracion-frontend.md`](integracion-frontend.md), y CORS con `@CrossOrigin` | — | Pendiente |

Fuera de este plan: RM-011 (MySQL, APF3) y RM-012 (Spring Security, entrega final).

Decisión pendiente para el plan 6: cómo sabe la API qué usuario hace la petición antes de RM-010.
Lo más simple y fiel al curso es un parámetro `userId`.

## Reglas de trabajo

- **Solo lo que enseña el curso** (clases y labs de `marcos-de-desarrollo-web/raw`, resumidos en
  `marcos-de-desarrollo-web/procesado/`). Sin Swagger, sin librerías ni configuración que no se haya visto.
  Si algo no se vio en clase, no entra.
- **Estructura del profesor** (DDD + hexagonal), igual en todos los módulos:
  ```
  <modulo>/
  ├── domain/model/{aggregates, enums, valueobjects}   entidades con validación en el constructor y getters a mano
  ├── domain/repositories/                             interfaces (puertos)
  ├── infrastructure/persistence/memory/               InMemory...Repository con Map y datos del mock en el constructor
  ├── application/                                     un @Service; es lo que usan los otros módulos
  └── interfaces/rest/                                 @RestController (+ dto/ con records validados)
  ```
- Un módulo no usa repositorios ni dominio de otro: le pide las cosas a su `@Service`.
- **Errores** como en el lab de la semana 6: `NotFoundException` → `404 {"error"}`, validación →
  `400 {"errors": [...]}`, reglas de dominio con `IllegalArgumentException` → `400 {"error"}`. No hay 409.
- Ids `String` tipo slug, los mismos del mock del front. Enums en mayúsculas. Precios en `double`.
- Datos en memoria cargados con el mismo contenido de `tecnolink-frontend/apps/web/src/data/catalog.ts`.
- Si algo de la API no calza con lo que espera el front, se anota en `integracion-frontend.md`.

## Forma de trabajar

- Plan en pasos numerados; **cada paso es un commit** y deja el proyecto compilando con los tests en verde.
  Al terminar un paso, Claude se detiene y entrega el nombre del commit. Esteban revisa, comitea y dice "sigue".
- Commits: `tipo(módulo): descripción en inglés, imperativo y minúscula`, por ejemplo `feat(catalog): ...`.
- Verificación: Claude escribe un test temporal con MockMvc, corre `./mvnw test` y **borra el test** antes
  de entregar. En el repo solo queda `TecnolinkApplicationTests`.
- Claude no levanta el servidor. Esteban lo corre desde IntelliJ (`TecnolinkApplication`) y prueba en el navegador.
- Para correr Maven desde terminal hace falta Java 21 o superior:
  `JAVA_HOME=C:/Users/Esteban/.jdks/openjdk-24.0.1`. El Java por defecto del sistema es el 17.
- Logbook: al empezar un RM se marca `En progreso`; al cerrarlo pasa al `changelog.md` en el mismo commit.
- Al cerrar cada módulo, Claude deja una tabla de "qué hace cada clase" para quien lo expone.

## Contratos ya disponibles

| Módulo | Clase | Métodos públicos |
|---|---|---|
| `shared` | `NotFoundException` | `new NotFoundException(mensaje)` → 404 |
| `suppliers` | `SupplierService` | `getSuppliers()` · `getSupplier(id)` (404 si no existe) · `getProducts(supplierId)` · `getServices(supplierId)` |
| `catalog` | `CatalogService` | `getCategories()` (solo activas) · `getAllCategories()` · `getCategory(id)` · `createCategory(name, kind)` · `renameCategory(id, name)` · `setCategoryActive(id, active)` · `createProduct(supplierId, name, brand, categoryId, price, description)` · `updateProduct(id, name, brand, price, description)` · `createService(supplierId, name, categoryId, price, pricing, description, coverage)` · `updateService(id, name, price, pricing, description, coverage)` · `getProduct(id)` · `getProductsBySupplier(supplierId)` · `getServicesBySupplier(supplierId)` · `getProductsToCompare(ids)` · `getService(id)` · `search(query, categoryId, kind, minPrice, maxPrice)` |

`createProduct`/`createService` validan que la categoría exista, esté activa y sea del tipo correcto, y generan el id como slug único del nombre. **No validan que el proveedor exista**: eso lo hace el módulo `suppliers` antes de llamarlos. En `update...`, `brand`, `pricing` y `coverage` en `null` conservan el valor anterior.

La entidad de servicio se llama `TechService`, porque `Service` choca con la anotación `@Service` de Spring.
