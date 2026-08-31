# Roadmap

Trabajo comprometido: lo que sí se va a hacer. Código `RM-###` (nunca se reutiliza).
Al terminar una tarea se mueve al changelog y se borra de aquí.

**Formato de cada entrada:**
- **Objetivo:** qué se quiere lograr.
- **Hecho cuando:** criterio claro de finalización.
- **Fecha** y **Estado** (Abierto / En progreso).

---

**Meta de esta etapa:** un prototipo web navegable de punta a punta, con datos mock,
que permita recorrer las experiencias de usuario completas. Todo en `apps/web`. Las
tareas están ordenadas para que el camino del cliente (buscar → ver → comprar) quede
recorrible lo antes posible; el resto se cuelga de ahí.

## [RM-003] Datos mock del catálogo
- **Objetivo:** una única fuente de datos falsos (productos, servicios, proveedores,
  categorías, reseñas, cotizaciones) que alimente todas las pantallas, para que no se
  contradigan entre sí.
- **Hecho cuando:** existe un módulo con los tipos y los datos; cualquier pantalla los
  consume desde ahí y ninguna define los suyos.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-004] Inicio y búsqueda — HU-03
- **Objetivo:** la portada con categorías destacadas y el buscador por texto.
- **Hecho cuando:** escribir un término lleva a resultados coincidentes y se puede
  limpiar la búsqueda.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-005] Resultados y filtros — HU-04
- **Objetivo:** la lista de resultados con filtros por categoría, precio y tipo.
- **Hecho cuando:** los filtros se combinan entre sí, actualizan la lista y el estado
  se refleja en la URL.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-006] Detalle de producto y de servicio — HU-05, HU-06
- **Objetivo:** las dos fichas de detalle, que comparten estructura pero no contenido:
  el producto muestra especificaciones, el servicio muestra alcance y cobertura.
- **Hecho cuando:** ambas muestran datos, proveedor, reseñas y las acciones de comprar
  y cotizar.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-007] Compra simulada — carrito, checkout y confirmación
- **Objetivo:** cerrar el recorrido principal del cliente de punta a punta.
- **Hecho cuando:** se agrega al carrito, se completa el checkout y se llega a una
  confirmación con resumen del pedido. Sin cobro, y la pantalla lo dice.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-008] Acceso simulado — HU-01, HU-02
- **Objetivo:** registro e inicio de sesión, y el cambio de rol (cliente / proveedor /
  administrador) que el prototipo necesita para demostrar las tres experiencias.
- **Hecho cuando:** ambos formularios validan campos y confirman; el rol activo cambia
  lo que muestra el header y a dónde redirige.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-009] Comparador de equipos — HU-07
- **Objetivo:** comparar dos o más equipos atributo por atributo.
- **Hecho cuando:** se seleccionan equipos desde el catálogo, se ven en columnas y las
  diferencias quedan resaltadas.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-010] Perfil de proveedor y reseñas — HU-10, HU-11, HU-12
- **Objetivo:** la vista pública del proveedor y el sistema de reseñas.
- **Hecho cuando:** el perfil muestra datos, oferta publicada y valoración promedio; se
  puede leer reseñas y registrar una desde una compra hecha.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-011] Solicitud de cotización — HU-08
- **Objetivo:** el lado cliente del flujo de cotizaciones.
- **Hecho cuando:** desde un producto o servicio se envía una solicitud con cantidad y
  requerimiento, y se confirma el envío.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-012] Bandeja y respuesta de cotizaciones — HU-09, HU-17
- **Objetivo:** el lado proveedor del mismo flujo, con el formato estandarizado que
  hace comparables las propuestas.
- **Hecho cuando:** la bandeja lista solicitudes con su estado y se responde con precio,
  vigencia, condiciones y observaciones.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-013] Publicaciones del proveedor — HU-15, HU-16
- **Objetivo:** publicar y editar productos y servicios.
- **Hecho cuando:** el formulario valida los campos obligatorios, la lista de
  publicaciones refleja los cambios y confirma la edición.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-014] Fidelización — HU-13, HU-14
- **Objetivo:** los puntos que deja cada compra y la pantalla donde se consultan.
- **Hecho cuando:** una compra simulada suma puntos, el saldo se actualiza, y la
  pantalla muestra movimientos y beneficios separando disponibles de usados.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-015] Administración — HU-18, HU-19
- **Objetivo:** el panel del administrador sobre categorías y proveedores.
- **Hecho cuando:** se listan, crean, editan y desactivan categorías validando
  duplicados; y se revisa proveedores pudiendo cambiar su estado con observación.
- **Fecha:** 2026-08-31 · **Estado:** Abierto

## [RM-016] Pasada responsive — HU-20
- **Objetivo:** revisar el prototipo completo en móvil y escritorio.
- **Hecho cuando:** ninguna pantalla desborda horizontalmente, menús y formularios son
  usables en móvil, y las tablas anchas (comparador, cotizaciones) tienen su propio
  scroll.
- **Fecha:** 2026-08-31 · **Estado:** Abierto
