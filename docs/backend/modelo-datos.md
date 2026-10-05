# Modelo de datos: cotizaciones y comparador

Tablas del módulo `cotizaciones/` según el diagrama de clases del equipo. En este avance **no hay base de datos**:
los datos viven en repositorios en memoria (`infrastructure/persistence/memory`). Este modelo es el que tomarán las
tablas cuando se conecte MySQL con Spring Data.

`clientes`, `publicaciones` y `productos` son de otros módulos (usuarios y catálogo); aquí solo se muestran las
columnas que se referencian.

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

## De clase a tabla

| Clase (dominio) | Tipo | Tabla | Repositorio en memoria |
|---|---|---|---|
| `SolicitudCotizacion` | Aggregate | `solicitudes_cotizacion` | `InMemorySolicitudCotizacionRepository` |
| `Cotizacion` | Entity dentro de la solicitud | `cotizaciones` | Se guarda con su solicitud |
| `Comparacion` | Aggregate | `comparaciones` + `comparacion_productos` | `InMemoryComparacionRepository` |
| `Especificacion` | Entity del producto | `especificaciones` | `InMemoryEspecificacionRepository` |
