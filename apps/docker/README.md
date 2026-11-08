# Docker

Levanta la **API** (`apps/api`) y su **MySQL** con Docker Compose. Solo hace falta
Docker: Java y Maven van dentro de la imagen.

## Inicio rápido

```bash
cd apps/docker
cp .env.example .env
docker compose up -d --build --wait
```

| Servicio | Contenedor | URL |
|---|---|---|
| API | `tecnolink-api` | `http://localhost:8080/api/v1/...` |
| Swagger | `tecnolink-api` | `http://localhost:8080/swagger-ui/index.html` |
| Salud | `tecnolink-api` | `http://localhost:8080/actuator/health` |
| MySQL | `tecnolink-sql` | `localhost:3306`, base `tecnolink` |

Las tablas las crea Hibernate al arrancar la API (`ddl-auto=update`), y la base
arranca vacía.

## Datos de prueba

`scripts/seed.sql` (en la raíz del repo) carga un cliente, un proveedor, una
categoría y dos productos. Con la API ya arriba, porque necesita las tablas:

```bash
docker compose exec -T mysql sh -c 'mysql -uroot -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < /scripts/seed.sql'
```

Se puede correr varias veces: `INSERT IGNORE` no duplica.

## Cómo está organizado

Un archivo base y uno por entorno, que Compose fusiona:

```
apps/docker/
├── compose.yaml           # lo común: servicios, healthchecks, red, volumen
├── compose.override.yaml  # desarrollo: construye la API, publica 8080 y 3306, monta scripts/ (se carga solo)
├── .env.example           # plantilla; funciona tal cual en desarrollo
└── .env                   # uno por máquina (ignorado por git)
```

Los nombres son fijos: proyecto `tecnolink`, contenedores `tecnolink-{sql,api}` y
volumen `tecnolink_mysql_data`.

## Variables

| Variable | Para qué |
|---|---|
| `MYSQL_DATABASE` | base que se crea al iniciar |
| `MYSQL_ROOT_PASSWORD` | contraseña de `root`; la misma de `spring.datasource.password` |

**No las cambies después del primer arranque.** MySQL solo las aplica al crear el
volumen; si las cambias luego, la API ya no se conecta. Para cambiarlas, borra el
volumen con `docker compose down -v`.

La API lee `application.properties`, pero el compose sobrescribe la conexión con
las variables `SPRING_DATASOURCE_*`: dentro de Docker, MySQL está en `mysql:3306`,
no en `localhost`.

## Solo la base (para correr la API desde IntelliJ)

```bash
docker compose up -d mysql
```

Luego arranca `TecnolinkApplication` desde IntelliJ. Si la API en Docker está
arriba ocupa el 8080: es una o la otra.

## Comandos

```bash
# desde apps/docker/
docker compose up -d --build --wait   # construir y levantar todo
docker compose ps                     # estado y healthcheck
docker compose logs -f api            # ver los logs de la API
docker compose down                   # detener (conserva los datos)
docker compose down -v                # detener y borrar la base
```

## Cómo se construye la imagen

El `Dockerfile` está en `apps/api` y tiene dos etapas:

1. **build:** Maven y Java 21 compilan el proyecto y generan el `.jar`. Los tests
   se compilan pero no se ejecutan, porque necesitan MySQL. Las dependencias
   quedan en una caché de BuildKit y no se descargan en cada build.
2. **runtime:** una imagen solo con la JRE recibe el `.jar` y lo ejecuta con un
   usuario sin privilegios.
