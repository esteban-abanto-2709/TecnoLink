# Separación de responsabilidades — Backend

Quién hace qué en la API de TecnoLink. El detalle de cada tarea (endpoints, reglas,
criterio de terminado) está en [`logbook/roadmap.md`](logbook/roadmap.md). Este documento
solo asigna dueños y fija cómo se comunican los módulos.

## Regla principal

**Cada integrante es dueño de un módulo completo**: su paquete con sus capas
`domain/`, `application/`, `infrastructure/` e `interfaces/`. Nadie edita archivos de
otro módulo. Si necesitas algo de otro módulo, se lo pides a su **servicio de aplicación**:
nunca uses su repositorio ni sus clases de dominio directamente.

Así cada uno puede explicar su parte de punta a punta en la exposición, y no hay
conflictos de git por tocar los mismos archivos.

## División

| Integrante | Módulo (paquete) | Tareas | Endpoints |
|---|---|---|---|
| **Abanto García, Wilder Esteban** | `shared` + `catalog` | RM-001 base · RM-002 catálogo · RM-009 (categorías) · integración | `GET /categories` · `GET /products/{id}` · `GET /products?ids=` · `GET /services/{id}` · `GET /catalog?query=&categoryId=&kind=&minPrice=&maxPrice=` · `GET/POST /admin/categories` · `PATCH /admin/categories/{id}` · `PATCH /admin/categories/{id}/status` |
| **Naragio Chávez, Leyla Viviana** | `suppliers` | RM-003 proveedores · RM-008 publicaciones · RM-009 (verificación de proveedores) | `GET /suppliers` · `GET /suppliers/{id}` · `GET /suppliers/{id}/products` · `GET /suppliers/{id}/services` · `POST /suppliers/{id}/products` · `POST /suppliers/{id}/services` · `PUT /products/{id}` · `PUT /services/{id}` · `GET /admin/suppliers` · `PATCH /admin/suppliers/{id}/review` |
| **Condori Zegovia, Carlos Alberto** | `quotes` + `orders` | RM-005 cotizaciones · RM-006 compra simulada | `POST /quotes` · `GET /quotes` · `GET /suppliers/{id}/quotes` · `POST /quotes/{id}/answer` · `POST /orders` · `GET /orders` |
| **Machado Ninapaytan, Esperanza** | `reviews` + `loyalty` | RM-004 reseñas · RM-007 puntos y beneficios | `GET /reviews?targetId=` · `POST /reviews` · `GET /benefits` · `GET /points` · `POST /points/redemptions` |
| **Gallardo Sicha, Benjamin Estuar** | `iam` | RM-010 registro y login · luego encabeza RM-012 (Spring Security) | `POST /auth/register` · `POST /auth/login` · `GET /auth/me` |

Todas las rutas llevan el prefijo `/api/v1`.

RM-009 se comparte: la parte de categorías es de Esteban y la verificación de proveedores es de Leyla.
La tarea pasa al changelog cuando las dos partes están terminadas.

## Contratos entre módulos

Lo que cada dueño **expone** para que otros lo usen. El nombre exacto de cada clase lo fija su dueño al implementarla.

| Provee | Qué expone | Lo usan |
|---|---|---|
| `catalog` (Esteban) | Consultar producto o servicio por id: nombre, precio, proveedor, categoría | Carlos (precio real en órdenes, ítem válido en cotizaciones) · Esperanza (que exista el destino de una reseña) |
| `catalog` (Esteban) | Crear y editar productos y servicios | Leyla (sus endpoints de publicaciones) |
| `shared` (Esteban) | `NotFoundException` y `RestExceptionHandler`, como en el lab de la semana 6: `404 {"error"}`, validación `400 {"errors"}`, reglas de dominio (`IllegalArgumentException`) `400 {"error"}` | Todos |
| `suppliers` (Leyla) | Consultar proveedor por id · registrar proveedor | Carlos (cotizaciones) · Benjamin (registro con rol proveedor) |
| `loyalty` (Esperanza) | Sumar o restar puntos a un usuario con una descripción | Carlos (puntos por compra) · Esperanza (puntos por reseña) |

## Orden de trabajo

1. **Esteban:** RM-001 y la lectura del catálogo (RM-002). Así quedan listas las piezas comunes y los productos que los demás referencian.
2. **En paralelo:** cada integrante desarrolla su módulo con datos en memoria. Mientras tanto, Esteban completa la búsqueda, las categorías de admin y los comandos de catálogo que necesita Leyla.
3. **Integración (Esteban):** todo junto y una prueba del flujo completo:
   buscar → cotizar → comprar → reseñar → canjear puntos.

En el APF3, cada uno hace el adaptador JPA (MySQL) de su propio módulo.

## Exposición

Cada integrante presenta su módulo con la misma estructura: qué problema resuelve,
sus endpoints funcionando y cómo están organizadas sus capas.
Esteban abre con la arquitectura general y la base común (`shared`: manejo de errores).

## Regla para todos

Usar solo lo que el curso enseña (clases y labs de `marcos-de-desarrollo-web/raw`). Si algo no salió
en clase, no se agrega: el profesor puede pedir que lo expliquen.
