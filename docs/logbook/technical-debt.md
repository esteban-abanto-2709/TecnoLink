# Deuda Técnica

Registro de atajos, decisiones pendientes y riesgos a futuro de este proyecto.
Código `TD-###` (nunca se reutiliza). Al resolverse, la entrada se mueve al
changelog y se borra de aquí.

**Formato de cada entrada:**
- **Ubicación:** `archivo:línea` afectado.
- **Riesgo:** del 1 al 10 (1-3 cosmético · 4-6 ralentiza/moderado · 7-9 bug latente o seguridad · 10 crítico).
- **Problema:** qué está mal, sintetizado.
- **Impacto futuro:** qué puede causar si no se atiende.
- **Fecha** y **Estado** (Abierto / En progreso).


---

## [TD-013] El handler global convierte cualquier excepción genérica de Java en respuesta de negocio
- **Ubicación:** `apps/api/src/main/java/com/tecnolink/tecnolink/shared/interfaces/rest/RestExceptionHandler.java`
- **Riesgo:** 3/10
- **Problema:** `IllegalArgumentException` → 400 e `IllegalStateException` → 409 también atrapan las que lanza el
  framework, y su mensaje interno llega al cliente. Todos los módulos usan esas dos excepciones como reglas de dominio,
  así que no se puede cambiar solo desde `quotes`.
- **Impacto futuro:** un fallo técnico puede verse como un 409 con nombres de clases. Se corrige con excepciones de
  dominio propias en todos los módulos.
- **Fecha:** 2026-11-08 · **Estado:** Abierto

## [TD-012] MySQL ignora mayúsculas al validar ids
- **Ubicación:** `apps/api/src/main/java/com/tecnolink/tecnolink/quotes/infrastructure/persistence/jpa/adapters/`
- **Riesgo:** 2/10
- **Problema:** la colación por defecto compara sin distinguir mayúsculas. `DEMO-CLIENT` pasa como cliente válido y
  se guarda con otra grafía, y una comparación acepta el mismo producto escrito en mayúsculas como si fuera otro.
- **Impacto futuro:** solo ocurre si alguien escribe el id a mano; el front siempre manda los slugs tal cual.
- **Fecha:** 2026-11-08 · **Estado:** Abierto

## [TD-010] El único test de la API necesita MySQL levantado
- **Ubicación:** `apps/api/src/test/java/com/tecnolink/tecnolink/TecnolinkApplicationTests.java`
- **Riesgo:** 4/10
- **Problema:** `contextLoads` arranca Spring completo y se conecta a `localhost:3306`. Sin el contenedor de
  MySQL, `./mvnw test` falla con `Communications link failure` aunque el código esté bien.
- **Impacto futuro:** "los tests pasan" depende de tener Docker corriendo; un error real se confunde con una base
  apagada. Antes de testear: `docker compose up -d` en `apps/docker`.
- **Fecha:** 2026-11-07 · **Estado:** Abierto

## [TD-009] Repositorios en memoria sin uso en `loyalty`
- **Ubicación:** `apps/api/src/main/java/com/tecnolink/tecnolink/loyalty/infrastructure/persistence/memory/`
- **Riesgo:** 2/10
- **Problema:** `InMemoryBenefitRepository` e `InMemoryPointsMovementRepository` quedaron del APF2. No son beans
  ni los usa nadie: la persistencia real es la de `jpa/`.
- **Impacto futuro:** confunden al leer el módulo o al exponerlo. Se borran al cerrar RM-029.
- **Fecha:** 2026-11-07 · **Estado:** Abierto

## [TD-008] Catálogo con rutas y especificaciones duplicadas
- **Ubicación:** `apps/api/src/main/java/com/tecnolink/tecnolink/catalog/interfaces/rest/`,
  `catalog/infrastructure/persistence/jpa/entities/ProductJpaEntity.java`, `quotes/domain/aggregates/Specification.java`
- **Riesgo:** 5/10
- **Problema:** hay dos caminos para lo mismo. Un servicio se lee en `GET /services/{id}` (`CatalogController`) y en
  `GET /tech-services/{id}` (`TechServiceController`); un producto se crea en `POST /products` y en
  `POST /suppliers/{id}/products`; y las especificaciones viven en `product_specifications` (catálogo) y en la
  tabla `specifications` (`quotes`).
- **Impacto futuro:** al conectar el front no queda claro qué ruta usar, y una especificación cargada por un camino
  no aparece en el otro (el cuadro comparativo solo lee `specifications`). Hay que elegir una de cada par.
- **Fecha:** 2026-11-07 · **Estado:** Abierto

## [TD-007] Contraseñas en texto plano en el dominio de usuarios
- **Ubicación:** `apps/api/src/main/java/com/tecnolink/tecnolink/iam/domain/model/aggregates/User.java`
- **Riesgo:** 7/10
- **Problema:** `User` guarda la contraseña tal cual y así llega a la columna `users.password` de MySQL.
- **Impacto futuro:** cualquiera con acceso a la base ve las contraseñas. Se corrige en RM-030 con el
  `PasswordEncoder` de Spring Security.
- **Fecha:** 2026-10-05 · **Estado:** Abierto

## [TD-006] Estructura del repo distinta a la que pide la rúbrica
- **Ubicación:** `apps/api/`, `apps/web/`, `apps/api/src/main/java/com/tecnolink/tecnolink/`
- **Riesgo:** 4/10
- **Problema:** la rúbrica pide `/docs`, `/scripts`, `/app-frontend`, `/app-backend`, y el repo usa `apps/web` y `apps/api`. Además, el paquete raíz repite el nombre (`com.tecnolink.tecnolink`).
- **Impacto futuro:** riesgo de descuento por no seguir el formato de entrega. Cuanto más código haya, más caro será mover la carpeta o renombrar el paquete.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

> Las entradas de arriba vienen del repo `TecnoLink-backend` del grupo, importado en `apps/api` el 2026-11-07. Se renumeraron a continuación de las del front: `RM-n` pasó a `RM-(n+18)` y `TD-n` a `TD-(n+4)`.

## [TD-004] El informe dice que los puntos salen solo de las compras
- **Ubicación:** `docs/originales/informe-marcos-de-desarrollo-web.docx`, objetivo
  específico 6 y sección 10
- **Riesgo:** 4/10
- **Problema:** RM-017 hace que publicar una reseña sume 50 puntos, pero el informe
  declara un programa de fidelización que otorga puntos "a partir de las compras
  realizadas". La aplicación entrega más de lo que el documento promete.
- **Impacto futuro:** es un entregable calificado; la incoherencia entre el documento y
  la demo salta apenas alguien abre las dos cosas a la vez. Lo corrige el grupo en el
  `.docx`, y el `.md` derivado se regenera en el mismo commit.
- **Fecha:** 2026-09-04 · **Estado:** Abierto

## [TD-003] Los controles táctiles quedan por debajo de 44 px
- **Ubicación:** `apps/web/src/components/ui/button.tsx`
- **Riesgo:** 3/10
- **Problema:** la escala de `base-nova` da botones de 28 a 32 px de alto. Las pautas de
  accesibilidad piden 44 px de área táctil. En el carrito y el comparador se subió a 36 px
  en móvil con una clase puntual, pero el resto de la interfaz sigue en la escala chica.
- **Impacto futuro:** en celular cuesta acertarle a los botones secundarios. Resolverlo
  bien es tocar la escala del sistema de diseño, no cada pantalla, así que conviene
  decidirlo de una vez antes de que haya más pantallas.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [TD-002] Lo que publica el proveedor no llega al catálogo público
- **Ubicación:** `apps/web/src/app/search/page.tsx`, `apps/web/src/app/products/[id]/page.tsx`
- **Riesgo:** 5/10
- **Problema:** las publicaciones nuevas y las ediciones viven en un store de cliente,
  pero el catálogo, las fichas de detalle y el perfil del proveedor son componentes
  servidor que solo leen `src/data/`. Un equipo recién publicado no aparece en la
  búsqueda ni tiene ficha propia, así que la lista de publicaciones no enlaza a la vista
  pública para no dejar enlaces rotos.
- **Impacto futuro:** en una demo, publicar algo y no encontrarlo en el catálogo se lee
  como un bug. Cerrarlo pide que la búsqueda y el detalle pasen a cliente y fusionen el
  store, o que exista una API de verdad detrás de `src/data/`.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [TD-001] El buscador del header no conserva el término
- **Ubicación:** `apps/web/src/components/site-header.tsx`
- **Riesgo:** 3/10
- **Problema:** el input del header siempre arranca vacío. Al llegar a `/search?q=laptop`
  el término se ve en el título de la pantalla pero no en la caja, así que refinar una
  búsqueda obliga a reescribirla completa.
- **Impacto futuro:** se nota apenas se encadenan dos búsquedas, que es lo que hace
  cualquiera comparando equipos. Resolverlo pide leer `useSearchParams` en un componente
  cliente con su frontera de Suspense, o mover el buscador dentro de cada página.
- **Fecha:** 2026-08-31 · **Estado:** Abierto
