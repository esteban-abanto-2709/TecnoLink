# Integración con el frontend

El front (`tecnolink-frontend/apps/web`) se diseñó pensando en una API hecha con NestJS. Este archivo
anota **todo lo que la API de Spring Boot hace distinto** y lo que hay que cambiar en el front para que
calce. Se aplica en el plan 11 de [`plan-backend.md`](plan-backend.md). Cada módulo nuevo agrega sus
diferencias aquí.

## Generales

| Tema | API | Front hoy | Cambio en el front |
|---|---|---|---|
| Origen de los datos | Endpoints REST en `/api/v1` | Funciones síncronas de `src/data/catalog.ts` (`getProduct(id)` devuelve el objeto directo) | Reemplazar la capa `src/data` por funciones `async` con `fetch`. Las pantallas servidor pasan a `await`. Es el cambio más grande: hoy las páginas llaman a esas funciones sin esperar |
| URL base | `http://localhost:8080` en local | — | Variable de entorno (por ejemplo `NEXT_PUBLIC_API_URL`) |
| Enums | En mayúsculas: `PRODUCT`, `SERVICE`, `FIXED`, `FROM`, `HOURLY` | En minúsculas: `"product"`, `"fixed"`... | Pasar a minúsculas al leer y a mayúsculas al enviar, en la capa `src/data`. Los tipos de `types.ts` no cambian |
| Errores | `404 {"error": "..."}` · `400 {"error": "..."}` · validación `400 {"errors": ["campo: mensaje"]}` | No maneja errores: los mocks no fallan | Leer `error` / `errors` y mostrarlos en español en la UI |
| Precios | `double`: `1899.0` | `number` | Ninguno |
| Ids | Los mismos slugs del mock | Igual | Ninguno |
| CORS | Se agrega en el plan 11 con `@CrossOrigin` para `localhost:3000` y `tecno-link.vercel.app` | — | Ninguno |

## Catálogo (RM-002)

| Endpoint | Reemplaza en el front | Diferencias |
|---|---|---|
| `GET /api/v1/categories` | `categories`, `getCategory(id)` | Solo las activas. No hay endpoint por id: el front busca en la lista. Cada categoría trae además `active`. Ojo: los productos de una categoría desactivada siguen existiendo, así que en su detalle `getCategory` no la encuentra; el front debe tolerarlo (por ejemplo, no mostrar la categoría) |
| `GET /api/v1/products/{id}` | `getProduct(id)` | Un id inexistente da 404. El front hoy recibe `undefined` |
| `GET /api/v1/services/{id}` | `getService(id)` | Igual que el anterior |
| `GET /api/v1/products?ids=a,b` | `getProduct` en bucle dentro de `/compare` | Devuelve solo los ids que son productos, en el orden pedido (igual que el `.filter` del front). Más de 4 da 400 |
| `GET /api/v1/catalog?query=&categoryId=&kind=&minPrice=&maxPrice=` | `searchCatalog(...)`, `catalogItems()` | Mismos nombres de parámetros. `kind` va en mayúsculas. En productos, `pricing` llega como `null` (no `undefined`): el `item.pricing ? ...` del front funciona igual. Hasta RM-003 **no busca por nombre del proveedor** |
| `GET /api/v1/admin/categories` | `useAdminCategories()` | Devuelve todas, con `active`. El front hoy fusiona el mock con su store local (`names`, `disabled`); con la API eso desaparece |
| `POST /api/v1/admin/categories` | `createCategory(name, kind)` | Body `{ name, kind }` con `kind` en mayúsculas. Duplicado da `400 {"error": "Category X already exists"}`. El front puede dejar de validar con `isDuplicateCategory` o mantenerlo como aviso previo |
| `PATCH /api/v1/admin/categories/{id}` | `renameCategory(id, name)` | Body `{ name }`. Misma regla de duplicado |
| `PATCH /api/v1/admin/categories/{id}/status` | `toggleCategory(id)` | El front alterna; la API recibe el estado final `{ "active": true\|false }` |
| — | `ratingFor`, `reviewsFor` | Salen del módulo de reseñas (RM-004) |
| — | `getSupplier`, `productsBySupplier`, `servicesBySupplier` | Salen del módulo de proveedores (RM-003) |
