# Modelo de datos

Tablas de cada módulo según el diagrama de clases del equipo. En este avance **no hay base de datos**: los datos
viven en repositorios en memoria (`infrastructure/persistence/memory`). Este modelo es el que tomarán las tablas
cuando se conecte MySQL con Spring Data.

En cada diagrama, las tablas de otros módulos solo muestran las columnas que se referencian.

## Catálogo (`catalogo/`, Carlos)

`Publicacion` es abstracta y se guarda en una sola tabla con la columna `tipo` (`PRODUCTO` o `SERVICIO`): las
columnas propias de cada subclase quedan vacías en la otra.

```mermaid
erDiagram
    proveedores ||--o{ publicaciones : "publica"
    categorias ||--o{ publicaciones : "agrupa"
    publicaciones ||--o{ publicacion_imagenes : "tiene"

    proveedores {
        varchar id PK
    }
    categorias {
        varchar id_categoria PK
        varchar nombre
        varchar descripcion
    }
    publicaciones {
        varchar id_publicacion PK
        varchar tipo "PRODUCTO o SERVICIO"
        varchar proveedor_id FK
        varchar categoria_id FK
        varchar titulo
        varchar descripcion "20 caracteres o más"
        decimal precio "mayor que 0"
        date fecha_publicacion
        varchar estado "ACTIVA, PAUSADA"
        varchar marca "solo producto"
        varchar modelo "solo producto"
        int stock "solo producto, 0 o más"
        varchar modalidad "solo servicio"
        varchar duracion_estimada "solo servicio"
        varchar cobertura "solo servicio"
    }
    publicacion_imagenes {
        varchar publicacion_id PK, FK
        varchar url PK
    }
```

| Clase (dominio) | Tipo | Tabla | Repositorio en memoria |
|---|---|---|---|
| `Publicacion` | Aggregate abstracto | `publicaciones` | `InMemoryPublicacionRepository` |
| `Producto` | Hereda de `Publicacion` | `publicaciones` (`tipo = PRODUCTO`) | El mismo |
| `Servicio` | Hereda de `Publicacion` | `publicaciones` (`tipo = SERVICIO`) | El mismo |
| `Categoria` | Aggregate | `categorias` | `InMemoryCategoriaRepository` |

`listarPublicaciones()` de `Categoria` es `PublicacionRepository.findByCategoriaId(...)`.

## Proveedor, reseñas y fidelización (`proveedores/` y `fidelizacion/`, Esperanza)

La reputación del proveedor es el promedio de las calificaciones de las reseñas de sus publicaciones. El saldo de la
cuenta sale de sus movimientos: una acumulación suma y un canje resta. El nivel depende del saldo
(BRONCE menos de 500, PLATA desde 500, ORO desde 1500).

```mermaid
erDiagram
    proveedores ||--o{ publicaciones : "publica"
    clientes ||--o{ resenas : "escribe"
    publicaciones ||--o{ resenas : "califica"
    clientes ||--|| cuentas_fidelizacion : "posee"
    cuentas_fidelizacion ||--o{ movimientos_puntos : "registra"
    pedidos |o--o| movimientos_puntos : "genera"

    clientes {
        varchar id PK
    }
    publicaciones {
        varchar id_publicacion PK
        varchar proveedor_id FK
    }
    pedidos {
        bigint id_pedido PK
    }
    proveedores {
        varchar id_proveedor PK
        char ruc "11 dígitos, único"
        varchar razon_social
        varchar region
        varchar descripcion
        decimal reputacion "promedio de 1 a 5"
    }
    resenas {
        bigint id_resena PK
        varchar cliente_id FK
        varchar publicacion_id FK
        int calificacion "1 a 5"
        varchar comentario "10 caracteres o más"
        date fecha
    }
    cuentas_fidelizacion {
        varchar cliente_id PK, FK
        int puntos_acumulados
        varchar nivel "BRONCE, PLATA, ORO"
    }
    movimientos_puntos {
        bigint id_movimiento PK
        varchar cliente_id FK
        bigint pedido_id FK "solo si viene de una compra"
        date fecha
        varchar tipo "ACUMULACION, CANJE"
        int puntos "mayor que 0"
        varchar motivo
    }
```

| Clase (dominio) | Tipo | Tabla | Repositorio en memoria |
|---|---|---|---|
| `Proveedor` | Aggregate | `proveedores` | `InMemoryProveedorRepository` |
| `Resena` | Aggregate | `resenas` | `InMemoryResenaRepository` |
| `CuentaFidelizacion` | Aggregate | `cuentas_fidelizacion` | `InMemoryCuentaFidelizacionRepository` |
| `MovimientoPuntos` | Entity dentro de la cuenta | `movimientos_puntos` | Se guarda con su cuenta |

Una reseña por cliente y por publicación (`existsByClienteIdAndPublicacionId`).

## Compra simulada (`compras/`, Leyla)

Cada cliente tiene un solo carrito. Al comprar, cada ítem del carrito pasa a ser un detalle del pedido con el
precio de ese momento. El pago es simulado: no hay pasarela.

```mermaid
erDiagram
    clientes ||--|| carritos : "tiene"
    carritos ||--o{ items_carrito : "contiene"
    publicaciones ||--o{ items_carrito : ""
    clientes ||--o{ pedidos : "realiza"
    pedidos ||--|{ detalles_pedido : "contiene"
    publicaciones ||--o{ detalles_pedido : ""

    clientes {
        varchar id PK
    }
    publicaciones {
        varchar id_publicacion PK
    }
    carritos {
        bigint id_carrito PK
        varchar cliente_id FK "único"
    }
    items_carrito {
        bigint carrito_id PK, FK
        varchar publicacion_id PK, FK
        int cantidad "1 o más"
        decimal precio_unitario
    }
    pedidos {
        bigint id_pedido PK
        varchar cliente_id FK
        date fecha
        decimal total "suma de los subtotales"
        varchar estado "PENDIENTE, PAGADO, CONFIRMADO"
        varchar metodo_pago_simulado
    }
    detalles_pedido {
        bigint pedido_id PK, FK
        varchar publicacion_id PK, FK
        int cantidad
        decimal precio_unitario
        decimal subtotal
    }
```

| Clase (dominio) | Tipo | Tabla | Repositorio en memoria |
|---|---|---|---|
| `Carrito` | Aggregate | `carritos` | `InMemoryCarritoRepository` |
| `ItemCarrito` | Entity dentro del carrito | `items_carrito` | Se guarda con su carrito |
| `Pedido` | Aggregate | `pedidos` | `InMemoryPedidoRepository` |
| `DetallePedido` | Entity dentro del pedido | `detalles_pedido` | Se guarda con su pedido |

## Cotizaciones y comparador (`cotizaciones/`, Esteban)

```mermaid
erDiagram
    clientes ||--o{ solicitudes_cotizacion : "envía"
    publicaciones ||--o{ solicitudes_cotizacion : "sobre"
    solicitudes_cotizacion ||--o| cotizaciones : "genera"
    clientes ||--o{ comparaciones : "crea"
    comparaciones ||--|{ comparacion_productos : "compara (2 a 4)"
    productos ||--o{ comparacion_productos : ""
    productos ||--|{ especificaciones : "tiene"

    clientes {
        varchar id PK
    }
    publicaciones {
        varchar id PK
    }
    productos {
        varchar id PK
    }
    solicitudes_cotizacion {
        bigint id_solicitud PK
        varchar cliente_id FK
        varchar publicacion_id FK
        date fecha "nulo mientras es BORRADOR"
        int cantidad "1 o más"
        varchar requerimiento
        varchar estado "BORRADOR, ENVIADA, RESPONDIDA, VENCIDA"
    }
    cotizaciones {
        bigint id_cotizacion PK "mismo id que su solicitud"
        date fecha_emision
        int dias_vigencia "1 o más"
        decimal subtotal
        decimal igv "18% del subtotal"
        decimal total "subtotal + igv"
        varchar condiciones
    }
    comparaciones {
        bigint id_comparacion PK
        varchar cliente_id FK
        date fecha
    }
    comparacion_productos {
        bigint id_comparacion PK, FK
        varchar producto_id PK, FK
    }
    especificaciones {
        varchar producto_id PK, FK
        varchar nombre PK
        varchar valor
        varchar unidad "vacío si no aplica"
    }
```

| Clase (dominio) | Tipo | Tabla | Repositorio en memoria |
|---|---|---|---|
| `SolicitudCotizacion` | Aggregate | `solicitudes_cotizacion` | `InMemorySolicitudCotizacionRepository` |
| `Cotizacion` | Entity dentro de la solicitud | `cotizaciones` | Se guarda con su solicitud |
| `Comparacion` | Aggregate | `comparaciones` + `comparacion_productos` | `InMemoryComparacionRepository` |
| `Especificacion` | Entity del producto | `especificaciones` | `InMemoryEspecificacionRepository` |
