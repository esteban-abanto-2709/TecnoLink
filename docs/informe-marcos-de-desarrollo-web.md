# Informe — Plataforma Web para la Búsqueda, Comparación y Comercialización de Equipos y Servicios Tecnológicos

> **Documento derivado.** Generado a partir de
> [`originales/informe-marcos-de-desarrollo-web.docx`](originales/informe-marcos-de-desarrollo-web.docx).
> Si el Word se actualiza, este archivo se regenera. No editar uno sin el otro.

Curso: Marcos de Desarrollo Web · Docente: Robles Fernandez, Ivan · Sección 45315 · Lima, 2026

**Integrantes:** Abanto García, Wilder Esteban (U23310744) · Gallardo Sicha, Benjamin
Estuar (U24252186) · Condori Zegovia, Carlos Alberto · Naragio Chávez, Leyla Viviana
(U24219412)

---

## 1. Introducción

Plataforma web que centraliza la búsqueda de equipos y servicios tecnológicos ofrecidos
por proveedores de Lima Metropolitana.

Hoy, quien quiere comprar un equipo o contratar un servicio busca en redes sociales,
páginas web y perfiles de proveedores. La información está dispersa y a veces
desactualizada, lo que dificulta comparar alternativas y elegir proveedor. A la vez, los
proveedores pequeños y medianos no compiten en visibilidad contra las grandes
plataformas de comercio electrónico.

La propuesta concentra la oferta, facilita la búsqueda y comparación, y mejora la
interacción entre clientes y proveedores.

## 2. Situación problemática

En Lima Metropolitana la oferta tecnológica es amplia pero está repartida entre redes
sociales, páginas web, marketplaces y establecimientos físicos. Esa fragmentación golpea
a los dos lados:

- **Clientes:** deben consultar varios medios para encontrar equipos, servicios, precios,
  características y referencias. Muchos tienen conocimientos solo básicos de tecnología,
  así que comparar alternativas les resulta complicado.
- **Proveedores pequeños y medianos:** tienen menor capacidad de posicionamiento digital
  frente a las grandes empresas de e-commerce, lo que limita la exposición de su oferta.

El resultado es una desconexión entre una oferta creciente y clientes que necesitan una
forma sencilla y centralizada de encontrar, evaluar y comparar.

## 3. Problema por resolver

Los clientes no cuentan con un medio centralizado para buscar, comparar y acceder a
equipos y servicios tecnológicos de distintos proveedores de Lima Metropolitana; y los
proveedores pequeños y medianos tienen visibilidad digital limitada frente a las grandes
plataformas.

Se necesita una solución que centralice la oferta, facilite la comparación y mejore la
conexión entre ambos lados.

## 4. Misión

Facilitar la conexión entre clientes y proveedores de tecnología mediante una plataforma
digital que centralice la búsqueda, adquisición, comparación y cotización de equipos y
servicios tecnológicos.

## 5. Visión

Ser una plataforma digital de referencia en Lima Metropolitana para la búsqueda y
comparación de equipos y servicios tecnológicos, contribuyendo a mejorar la visibilidad y
competitividad de los proveedores locales.

## 6. Objetivos

### 6.1 Objetivo general

Desarrollar una plataforma web que centralice la búsqueda de equipos y servicios
tecnológicos de proveedores de Lima Metropolitana, facilitando al cliente la búsqueda,
comparación, cotización y adquisición simulada.

### 6.2 Objetivos específicos

1. Implementar un catálogo centralizado de equipos y servicios de distintos proveedores.
2. Permitir búsqueda y filtrado según diferentes criterios.
3. Implementar la comparación de características de equipos electrónicos.
4. Estandarizar la solicitud y presentación de cotizaciones.
5. Incorporar un sistema de reseñas.
6. Implementar un programa de fidelización con puntos o beneficios por compras.
7. Facilitar al proveedor la publicación y exposición de su oferta.
8. Implementar un flujo de compra simulado completo dentro de la plataforma.

## 7. Alcance y limitaciones

### 7.1 Alcance

Registro e inicio de sesión · búsqueda de equipos y servicios · categorías · filtros ·
detalle de producto y servicio · perfil del proveedor · comparación de equipos ·
solicitud de cotizaciones con formato estandarizado · reseñas y valoraciones ·
fidelización por puntos · publicación de productos y servicios por el proveedor ·
bandeja de solicitudes de cotización · simulación del proceso de compra completo, sin
transacciones monetarias reales.

### 7.2 Limitaciones

- La primera versión apunta a proveedores y clientes de Lima Metropolitana.
- No gestiona logística ni entrega física de productos.
- No sustituye la garantía que da cada proveedor.
- **No integra pasarela de pago real.**
- **En este primer avance las funcionalidades se representan con interfaces Front-End y
  navegación simulada.**
- La precisión de la información publicada será responsabilidad del proveedor en etapas
  funcionales futuras.

## 8. Actores del sistema

| Actor | Qué hace |
|---|---|
| **Cliente** | Busca equipos o servicios, aplica filtros, compara equipos, consulta proveedores, solicita cotizaciones, compra (simulado), consulta sus puntos y registra reseñas. |
| **Proveedor** | Publica y administra su oferta, gestiona sus publicaciones y atiende solicitudes de cotización. |
| **Administrador** | Supervisa la plataforma: categorías, proveedores, usuarios y contenido publicado. |

## 9. Caso de uso de alto nivel

> Transcrito del diagrama incluido en el Word.

| Actor | Objetivo | Casos de uso |
|---|---|---|
| **Cliente** | Encontrar, evaluar y adquirir equipos o servicios. | Registrarse / iniciar sesión · buscar equipos y servicios · aplicar filtros · consultar detalle · comparar equipos · consultar proveedores · solicitar cotización · realizar compra simulada · consultar compras · consultar puntos de fidelización · registrar reseñas. |
| **Proveedor** | Publicar su oferta y atender solicitudes. | Iniciar sesión · gestionar perfil · publicar productos y servicios · administrar publicaciones · consultar solicitudes de cotización · responder cotizaciones · consultar compras asociadas. |
| **Administrador** | Supervisar y mantener la plataforma. | Iniciar sesión · gestionar usuarios · gestionar proveedores · gestionar categorías · supervisar contenido y publicaciones. |

## 10. Funcionalidades del sistema

| Módulo | Funcionalidad principal |
|---|---|
| Catálogo y búsqueda | Consultar equipos y servicios mediante categorías, búsquedas y filtros. |
| Comparador | Comparar características de diferentes equipos electrónicos. |
| Cotizaciones | Solicitar y recibir cotizaciones con un formato estandarizado. |
| Compra | Agregar productos o servicios y completar un proceso de compra simulada. |
| Proveedores | Consultar información, oferta y reputación del proveedor. |
| Reseñas | Consultar y registrar opiniones y valoraciones. |
| Fidelización | Acumular y consultar puntos o beneficios por compras. |
| Gestión del proveedor | Publicar productos y servicios y gestionar solicitudes de cotización. |
| Administración | Gestionar categorías, proveedores, usuarios y contenido. |

Las interfaces se diseñan buscando una experiencia sencilla, consistente y adaptable a
distintos tamaños de pantalla.

## 11. Herramientas declaradas en el informe

| Herramienta | Uso |
|---|---|
| Figma | Diseño de los prototipos de interfaz. |
| Visual Studio Code | Entorno de desarrollo del Front-End. |
| HTML5 | Estructura y contenido de las interfaces. |
| CSS3 | Estilos y presentación visual. |
| Bootstrap | Componentes visuales y adaptación responsive. |
| JavaScript | Interacciones y comportamiento dinámico. |
| Git | Control de versiones. |
| GitHub | Repositorio, documentación y trabajo colaborativo. |

> **Discrepancia pendiente.** El repositorio se construye con **Next.js 16, React 19 y
> Tailwind CSS v4**, no con Bootstrap y JavaScript plano. Hay que reconciliar esta tabla
> con el docente o actualizar el informe.

## 12. Lean Canvas

> Transcrito de la imagen del Anexo 2.

**Problema**

- Búsqueda dispersa de equipos y servicios en redes sociales y páginas web.
- Información desactualizada y dificultad para comparar alternativas.
- Proveedores con poca visibilidad física y digital frente a grandes e-commerce.
- Clientes con conocimientos básicos de tecnología: les cuesta elegir el producto adecuado.

**Solución**

- Aplicación que centraliza equipos y servicios tecnológicos.
- Cotizaciones estandarizadas para facilitar la comparación.
- Comparación de equipos y características.
- Reseñas y valoraciones de clientes.
- Programa de fidelización por compras.

**Proposición de valor única**

> "Encuentra, compara y cotiza equipos y servicios tecnológicos en un solo lugar, de
> forma sencilla y confiable."

**Ventaja especial** — Red de proveedores tecnológicos locales integrada en una sola
plataforma con información estandarizada.

**Segmento de clientes**

- **Clientes:** personas de Lima Metropolitana que buscan equipos o servicios
  tecnológicos, especialmente usuarios con conocimientos básicos de tecnología.
- **Proveedores:** pequeñas y medianas empresas y vendedores que buscan aumentar su
  visibilidad y llegar a nuevos clientes.

**Métricas clave** — Usuarios registrados y activos · proveedores registrados ·
cotizaciones solicitadas · compras realizadas · reseñas y recomendaciones.

**Canales** — Aplicación móvil/web · redes sociales (TikTok, Instagram, Facebook) ·
publicidad digital · alianzas con proveedores · recomendaciones de usuarios.

**Estructura de costos** — Desarrollo y mantenimiento · servidores y almacenamiento en
la nube · marketing y publicidad digital · soporte y atención al cliente · seguridad y
actualización.

**Flujo de ingresos** — Comisión por venta · planes de suscripción para proveedores ·
publicaciones o productos destacados · publicidad dentro de la plataforma.

## 13. Historias de usuario

### Acceso

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-01 | Cliente | Registrarme para guardar mis datos y realizar compras o solicitudes. | Registra nombre, correo y contraseña; valida campos obligatorios; confirma registro. | Alta | Registro |
| HU-02 | Cliente | Iniciar sesión para acceder a mis funciones personalizadas. | Valida credenciales; muestra errores; redirige al inicio del cliente. | Alta | Inicio de sesión |

### Catálogo

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-03 | Cliente | Buscar equipos y servicios desde un solo lugar. | Búsqueda por texto; muestra coincidencias; permite limpiar búsqueda. | Alta | Inicio / Búsqueda |
| HU-04 | Cliente | Filtrar por categoría, precio y tipo. | Filtros visibles y combinables; actualiza resultados. | Alta | Resultados de búsqueda |
| HU-05 | Cliente | Ver el detalle de un equipo. | Muestra nombre, imágenes, precio, especificaciones, proveedor y reseñas. | Alta | Detalle de producto |

### Servicios

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-06 | Cliente | Ver el detalle de un servicio tecnológico. | Muestra descripción, precio referencial o modalidad, proveedor, cobertura y reseñas. | Alta | Detalle de servicio |

### Comparador

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-07 | Cliente | Comparar equipos electrónicos. | Selecciona al menos 2 equipos; atributos en columnas; resalta diferencias. | Alta | Comparador |

### Cotizaciones

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-08 | Cliente | Solicitar una cotización a un proveedor. | Selecciona producto/servicio; ingresa cantidad o requerimiento; envía y confirma. | Alta | Solicitud de cotización |
| HU-09 | Proveedor | Responder solicitudes con un formato estándar. | Precio, vigencia, condiciones y observaciones; vista uniforme; permite enviar. | Alta | Gestión de cotizaciones |
| HU-17 | Proveedor | Revisar solicitudes recibidas. | Lista solicitudes; permite ver detalle; muestra estado de cada una. | Alta | Bandeja de cotizaciones |

### Proveedores y reseñas

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-10 | Cliente | Consultar el perfil de un proveedor. | Datos básicos, oferta publicada, valoración y reseñas. | Media | Perfil de proveedor |
| HU-11 | Cliente | Ver reseñas de otros usuarios. | Calificación, comentario y fecha; valoración promedio visible. | Alta | Reseñas / Detalle |
| HU-12 | Cliente | Registrar una reseña luego de una compra. | Calificación y comentario; valida contenido; asocia la reseña a una compra válida. | Media | Registrar reseña |

### Fidelización

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-13 | Cliente | Acumular puntos por mis compras. | Muestra puntos ganados; actualiza saldo; informa la regla de acumulación. | Media | Mis puntos |
| HU-14 | Cliente | Consultar mis puntos y beneficios disponibles. | Saldo, movimientos y beneficios; distingue disponibles y usados. | Media | Mis puntos |

### Gestión del proveedor

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-15 | Proveedor | Registrar equipos o servicios. | Datos mínimos, categoría, precio e imágenes; valida campos obligatorios. | Alta | Publicar producto/servicio |
| HU-16 | Proveedor | Editar mis publicaciones. | Permite modificar; conserva cambios; muestra confirmación. | Media | Mis publicaciones |

### Administración

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-18 | Administrador | Gestionar categorías. | Listar, crear, editar y desactivar; valida duplicados. | Media | Gestión de categorías |
| HU-19 | Administrador | Revisar proveedores registrados. | Lista proveedores; permite revisar datos y cambiar estado; registra observación. | Media | Gestión de proveedores |

### Navegación

| ID | Rol | Historia | Criterios de aceptación | Prio | Pantalla |
|---|---|---|---|---|---|
| HU-20 | Usuario | Usar la plataforma desde celular o computadora. | El diseño se adapta a móvil y escritorio; menús y formularios utilizables; sin desbordes. | Alta | Todas las pantallas |

## 14. Anexos del documento original

| Anexo | Contenido | Estado |
|---|---|---|
| 1 | Prototipo de interfaces | **Vacío en el Word.** No hay pantallas todavía. |
| 2 | Lean Canvas | Transcrito en la sección 12. |
| 3 | Historias de usuario | Transcrito en la sección 13. |

El `.docx` incluye además el diagrama de caso de uso de alto nivel (transcrito en la
sección 9) y la carátula. Las imágenes no se copiaron al repositorio: viven en el
original.
