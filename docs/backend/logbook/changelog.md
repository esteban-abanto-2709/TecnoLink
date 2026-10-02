# Changelog

Registro permanente de todo el trabajo terminado. Indexado por código de tarea
(`TD-`, `RM-`, `WL-`). Orden inverso: lo más nuevo arriba.

**Formato de cada entrada:**

```
## [CÓDIGO] Título (YYYY-MM-DD HH:MM)
Resumen en ≤2 líneas de lo que se hizo.
```

---

## [TD-001] La app no arranca: JPA y MySQL sin datasource (2026-10-02 08:13)
Se quitaron `spring-boot-starter-data-jpa`, `mysql-connector-j` y `spring-boot-starter-data-jpa-test` del `pom.xml`:
el APF2 trabaja con datos en memoria. Vuelven en RM-011, junto con el adaptador JPA.
