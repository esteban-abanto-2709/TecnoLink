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

## [TD-001] La app no arranca: JPA y MySQL sin datasource
- **Ubicación:** `tecnolink/src/main/resources/application.properties:1`, `tecnolink/pom.xml`
- **Riesgo:** 8/10
- **Problema:** el `pom.xml` trae `spring-boot-starter-data-jpa` y `mysql-connector-j`, pero `application.properties` solo tiene `spring.application.name`. Al arrancar falla con `Failed to configure a DataSource`, y el test `contextLoads` también.
- **Impacto futuro:** nadie del grupo puede levantar la API ni correr tests hasta configurar una BD, y eso bloquea el APF2, que se trabaja con datos en memoria.
- **Fecha:** 2026-10-02 · **Estado:** Abierto

## [TD-002] Estructura del repo distinta a la que pide la rúbrica
- **Ubicación:** `tecnolink/` (raíz del repo), `tecnolink/src/main/java/com/tecnolink/tecnolink/`
- **Riesgo:** 4/10
- **Problema:** la rúbrica pide `/docs`, `/scripts`, `/app-frontend`, `/app-backend`, y el proyecto Maven vive en `tecnolink/`. Además, el paquete raíz repite el nombre (`com.tecnolink.tecnolink`).
- **Impacto futuro:** riesgo de descuento por no seguir el formato de entrega. Cuanto más código haya, más caro será mover la carpeta o renombrar el paquete.
- **Fecha:** 2026-10-02 · **Estado:** Abierto
