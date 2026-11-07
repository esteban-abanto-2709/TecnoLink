# Docker

Por ahora solo levanta **MySQL** para la API (`apps/api`). La API y la web se corren
nativas (IntelliJ y `pnpm`).

## Inicio rápido

```bash
cd apps/docker
cp .env.example .env
docker compose up -d
```

MySQL queda en `localhost:3306` con la base `tecnolink`. Las tablas las crea
Hibernate al arrancar la API (`ddl-auto=update`).

## Cómo está organizado

Un archivo base y uno por entorno, que Compose fusiona:

```
apps/docker/
├── compose.yaml           # lo común: servicio, healthcheck, volumen
├── compose.override.yaml  # desarrollo: publica el puerto 3306 (se carga solo)
├── .env.example           # plantilla; funciona tal cual en desarrollo
└── .env                   # uno por máquina (ignorado por git)
```

Los nombres son fijos: proyecto `tecnolink`, contenedor `tecnolink-sql` y volumen
`tecnolink_mysql_data`.

## Variables

| Variable | Para qué |
|---|---|
| `MYSQL_DATABASE` | base que se crea al iniciar |
| `MYSQL_ROOT_PASSWORD` | contraseña de `root`; la misma de `spring.datasource.password` |

**No las cambies después del primer arranque.** MySQL solo las aplica al crear el
volumen; si las cambias luego, la API ya no se conecta. Para cambiarlas, borra el
volumen con `docker compose down -v`.

## Comandos

```bash
# desde apps/docker/
docker compose up -d     # levantar
docker compose ps        # estado y healthcheck
docker compose down      # detener (conserva los datos)
docker compose down -v   # detener y borrar la base
```
