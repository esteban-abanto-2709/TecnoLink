# Modelo de datos

Tablas de cada módulo de `apps/api`. Todos los módulos persisten en **MySQL con Spring Data JPA**, con las piezas
del profesor en `infrastructure/persistence/jpa/`: `entities/` (`...JpaEntity`), `repositories/`
(`...JpaRepository`), `mappers/` (`...PersistenceMapper`) y `adapters/` (`...RepositoryAdapter`, que implementa el
repositorio del dominio). Las tablas las crea Hibernate (`spring.jpa.hibernate.ddl-auto=update`).

Las entidades del diagrama de clases del equipo están en español; en el código y en las tablas van en inglés (ver
[`responsabilidades.md`](responsabilidades.md)). En cada diagrama, las tablas de otros módulos solo muestran las
columnas que se referencian.

## Usuarios y acceso (`iam`, Benjamin)

`User` es abstracta y usa herencia `JOINED`: cada subclase tiene su tabla, cuya clave es también la clave de
`users`. `Supplier` (módulo `suppliers`) también hereda de `User`.

```mermaid
erDiagram
    users ||--o| clients : "es"
    users ||--o| administrators : "es"
    users ||--o| suppliers : "es"

    users {
        varchar id PK
        varchar first_name
        varchar last_name
        varchar email "con @"
        varchar password "8 caracteres o más"
        varchar phone
        date registered_on
        boolean active
    }
    clients {
        varchar id PK, FK
        varchar dni "8 dígitos"
        varchar address
        varchar region
    }
    administrators {
        varchar id PK, FK
        varchar position
    }
    suppliers {
        varchar id PK, FK
    }
```

| Clase (dominio) | Tipo | Tabla | Persistencia |
|---|---|---|---|
| `User` | Aggregate abstracto | `users` | — |
| `Client` | Hereda de `User` | `users` + `clients` | `ClientRepositoryAdapter` |
| `Administrator` | Hereda de `User` | `users` + `administrators` | `AdministratorRepositoryAdapter` |

## Catálogo (`catalog`, Carlos)

`Listing` (publicación) es abstracta y usa herencia `JOINED`: `products` y `tech_services` comparten la clave de
`listings`. La entidad se llama `TechService` porque `Service` choca con la anotación `@Service` de Spring.

```mermaid
erDiagram
    suppliers ||--o{ listings : "publica"
    categories ||--o{ listings : "agrupa"
    listings ||--o{ listing_images : "tiene"
    listings ||--o| products : "es"
    listings ||--o| tech_services : "es"
    products ||--o{ product_specifications : "tiene"

    suppliers {
        varchar id PK
    }
    categories {
        varchar id PK
        varchar name
        varchar kind "PRODUCT, SERVICE"
        boolean active
        varchar description
    }
    listings {
        varchar id PK
        varchar name
        varchar category_id FK
        varchar supplier_id FK
        double price
        varchar description
        date published_on
        varchar status "ACTIVE, PAUSED, ARCHIVED"
    }
    listing_images {
        varchar listing_id FK
        varchar image_url
    }
    products {
        varchar id PK, FK
        varchar brand
        varchar model
        int stock
    }
    product_specifications {
        varchar product_id FK
        varchar spec_name
        varchar spec_value
        varchar spec_unit
    }
    tech_services {
        varchar id PK, FK
        varchar pricing "FIXED, FROM, HOURLY"
        varchar coverage
        varchar modality
        varchar estimated_duration
    }
```

| Clase (dominio) | Tipo | Tabla | Persistencia |
|---|---|---|---|
| `Listing` | Aggregate abstracto | `listings` + `listing_images` | — |
| `Product` | Hereda de `Listing` | `listings` + `products` + `product_specifications` | `ProductRepositoryAdapter` |
| `TechService` | Hereda de `Listing` | `listings` + `tech_services` | `TechServiceRepositoryAdapter` |
| `Category` | Aggregate | `categories` | `CategoryRepositoryAdapter` |

## Proveedor, reseñas y fidelización (`suppliers`, `reviews` y `loyalty`, Esperanza)

`verified` del proveedor no se guarda: es `true` solo si `status = VERIFIED`. El saldo de puntos sale de los
movimientos: `EARN` suma y `REDEEM` resta.

```mermaid
erDiagram
    users ||--o| suppliers : "es"
    clients ||--o{ reviews : "escribe"
    clients ||--|| loyalty_accounts : "posee"
    users ||--o{ points_movements : "registra"
    benefits |o--o{ points_movements : "se canjea en"

    users {
        varchar id PK
    }
    clients {
        varchar id PK
    }
    suppliers {
        varchar id PK, FK "hereda de users"
        varchar ruc
        varchar name
        varchar region
        varchar district
        varchar description
        int since_year
        double reputation
        varchar status "VERIFIED, PENDING, SUSPENDED"
        varchar review_note
        date reviewed_on
    }
    reviews {
        bigint id PK
        varchar client_id FK
        varchar target_id "producto, servicio o proveedor"
        int rating "1 a 5"
        varchar comment
        date review_date
    }
    loyalty_accounts {
        bigint id PK
        varchar user_id FK "único"
        int accumulated_points
        varchar level "BRONZE, SILVER, GOLD"
    }
    points_movements {
        varchar id PK
        varchar user_id FK
        date movement_date
        varchar description
        int points
        varchar type "EARN, REDEEM"
        varchar benefit_id FK "solo en canjes"
    }
    benefits {
        varchar id PK
        varchar name
        varchar description
        int cost
    }
```

| Clase (dominio) | Tipo | Tabla | Persistencia |
|---|---|---|---|
| `Supplier` | Hereda de `User` | `users` + `suppliers` | `SupplierRepositoryAdapter` |
| `Review` | Aggregate | `reviews` | `ReviewRepositoryAdapter` |
| `LoyaltyAccount` | Aggregate | `loyalty_accounts` | `LoyaltyAccountRepositoryAdapter` |
| `PointsMovement` | Aggregate | `points_movements` | `PointsMovementRepositoryAdapter` |
| `Benefit` | Aggregate | `benefits` | `BenefitRepositoryAdapter` |

## Compra simulada (`orders`, Leyla)

Cada cliente tiene un solo carrito. Al comprar, cada ítem del carrito pasa a ser un detalle del pedido con el
precio de ese momento. El pago es simulado: no hay pasarela.

```mermaid
erDiagram
    clients ||--|| carts : "tiene"
    carts ||--o{ cart_items : "contiene"
    listings ||--o{ cart_items : ""
    clients ||--o{ orders : "realiza"
    orders ||--|{ order_details : "contiene"
    listings ||--o{ order_details : ""

    clients {
        varchar id PK
    }
    listings {
        varchar id PK
    }
    carts {
        bigint id PK
        varchar client_id FK "único"
    }
    cart_items {
        bigint id PK
        bigint cart_id FK
        varchar listing_id FK
        int quantity
        double unit_price
    }
    orders {
        bigint id PK
        varchar client_id FK
        date order_date
        double total "suma de los subtotales"
        varchar status "PENDING, PAID, CONFIRMED"
        varchar simulated_payment_method
    }
    order_details {
        bigint id PK
        bigint order_id FK
        varchar listing_id FK
        int quantity
        double unit_price
        double subtotal
    }
```

| Clase (dominio) | Tipo | Tabla | Persistencia |
|---|---|---|---|
| `Cart` | Aggregate | `carts` | `CartRepositoryAdapter` |
| `CartItem` | Entity dentro del carrito | `cart_items` | Se guarda con su carrito (`cascade`) |
| `Order` | Aggregate | `orders` | `OrderRepositoryAdapter` |
| `OrderDetail` | Entity dentro del pedido | `order_details` | Se guarda con su pedido (`cascade`) |

## Cotizaciones y comparador (`quotes`, Esteban)

```mermaid
erDiagram
    clients ||--o{ quote_requests : "envía"
    listings ||--o{ quote_requests : "sobre"
    quote_requests ||--o| quotes : "se responde con"
    products ||--o{ specifications : "tiene"
    clients ||--o{ comparisons : "crea"
    comparisons ||--|{ comparison_products : "compara (2 a 4)"

    quote_requests {
        bigint id PK
        varchar client_id FK
        varchar listing_id FK
        date request_date
        int quantity "1 o más"
        varchar requirement
        varchar status "SENT, ANSWERED, EXPIRED"
    }
    quotes {
        bigint id PK
        bigint request_id FK "único"
        date issue_date
        int validity_days "1 o más"
        double subtotal "hasta 2 decimales"
        double igv "18% del subtotal"
        double total "subtotal + igv"
        varchar conditions
        varchar notes "nulo si no hay observaciones"
    }
    specifications {
        bigint id PK
        varchar product_id FK
        varchar name "único por producto"
        varchar spec_value
        varchar unit "nulo si no aplica"
    }
    comparisons {
        bigint id PK
        varchar client_id FK
        date comparison_date
    }
    comparison_products {
        bigint comparison_id FK
        int position "orden de columnas"
        varchar product_id
    }
```

| Clase (dominio) | Tipo | Tabla | Persistencia |
|---|---|---|---|
| `QuoteRequest` | Aggregate | `quote_requests` | `QuoteRequestRepositoryAdapter` |
| `Quote` | Entity dentro de la solicitud | `quotes` | Se guarda con su solicitud (`cascade`) |
| `QuoteAmounts` | Value object de `Quote` | Columnas `subtotal`, `igv` y `total` de `quotes` | Lo arma `QuoteRequestPersistenceMapper` |
| `Specification` | Aggregate | `specifications` | `SpecificationRepositoryAdapter` |
| `Comparison` | Aggregate | `comparisons` + `comparison_products` | `ComparisonRepositoryAdapter` |
