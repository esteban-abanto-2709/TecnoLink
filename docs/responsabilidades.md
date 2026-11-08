# Separación de responsabilidades — API

Quién es dueño de cada módulo de `apps/api`. El detalle de cada tarea (endpoints, reglas, criterio de
terminado) está en [`logbook/roadmap.md`](logbook/roadmap.md), y los contratos entre módulos en
[`api.md`](api.md).

## Regla principal

**Cada integrante es dueño de un módulo completo**: su paquete con sus capas `domain/`, `application/`,
`infrastructure/` e `interfaces/`. Si un módulo necesita algo de otro, se lo pide a su **servicio de
aplicación**: nunca usa su repositorio ni sus clases de dominio directamente.

Así cada uno puede explicar su parte de punta a punta en la exposición.

## División

Según el diagrama de clases del equipo. Las entidades del diagrama están en español; en el código van en inglés.

| Integrante | Módulo | Paquete | Entidades del diagrama → clase en el código |
|---|---|---|---|
| **Naragio Chávez, Leyla Viviana** (U24219412) | Compra simulada | `orders` | `Pedido` → `Order` · `DetallePedido` → `OrderDetail` · `Carrito` → `Cart` · `ItemCarrito` → `CartItem` |
| **Abanto García, Wilder Esteban** (U23310744) | Cotizaciones y comparador | `quotes` | `SolicitudCotizacion` → `QuoteRequest` · `Cotizacion` → `Quote` · `Comparacion` → `Comparison` · `Especificacion` → `Specification` |
| **Condori Zegovia, Carlos Alberto** (U23328458) | Catálogo | `catalog` | `Publicacion` → `Listing` · `Producto` → `Product` · `Servicio` → `TechService` · `Categoria` → `Category` |
| **Machado Ninapaytan, Esperanza** (U23269478) | Proveedor, reseñas y fidelización | `suppliers`, `reviews`, `loyalty` | `Proveedor` → `Supplier` · `Resena` → `Review` · `CuentaFidelizacion` → `LoyaltyAccount` · `MovimientoPuntos` → `PointsMovement` (además `Benefit`) |
| **Gallardo Sicha, Benjamin Estuar** (U24252186) | Usuarios y acceso | `iam` | `Usuario` → `User` · `Cliente` → `Client` · `Administrador` → `Administrator` |

La base común (`shared`: manejo de errores, eventos de dominio y Swagger) la expone Esteban.

Todas las rutas llevan el prefijo `/api/v1`. El estado de cada módulo está en el macro plan del roadmap.

## Dependencias entre módulos

| Provee | Qué expone | Lo usan |
|---|---|---|
| `catalog` (Carlos) | `CatalogService`: consultar producto o servicio por id; crear y editar publicaciones | `suppliers` (publicaciones del proveedor). Previsto: `orders` y `reviews` (ítem válido) |
| `suppliers` (Esperanza) | `SupplierService`: consultar proveedor por id | `catalog` (búsqueda por nombre del proveedor) |
| `loyalty` (Esperanza) | `LoyaltyService.awardPoints`: sumar puntos a un usuario | Previsto: `orders` (puntos por compra) y `reviews` (puntos por reseña) |
| `iam` (Benjamin) | Tablas `users`, `clients`, `administrators` | Las tablas de `quotes`, `orders`, `reviews` y `loyalty` tienen FK a `clients` |
| `shared` (Esteban) | `NotFoundException`, `RestExceptionHandler`, eventos de dominio | Todos |

## Exposición

Cada integrante presenta su módulo con la misma estructura: qué problema resuelve, sus endpoints funcionando y
cómo están organizadas sus capas. Esteban abre con la arquitectura general y la base común (`shared`).

## Regla para todos

Lo del curso es la base, no el límite. Se puede agregar lo que no salió en clase, siempre que quien lo agrega lo
entienda: el profesor puede pedir que lo expliquen.
