import assert from "node:assert/strict";

import {
  catalogItems,
  categories,
  getCategory,
  getSupplier,
  orders,
  pointsBalance,
  pointsMovements,
  products,
  quotes,
  ratingFor,
  reviews,
  searchCatalog,
  services,
  suppliers,
} from "../src/data/catalog.ts";
import { formatDate, formatServicePrice } from "../src/lib/format.ts";

function assertUniqueIds(label: string, items: { id: string }[]) {
  const ids = items.map((item) => item.id);
  assert.equal(
    new Set(ids).size,
    ids.length,
    `${label}: hay ids repetidos en ${ids.join(", ")}`
  );
}

assertUniqueIds("categories", categories);
assertUniqueIds("suppliers", suppliers);
assertUniqueIds("products", products);
assertUniqueIds("services", services);
assertUniqueIds("reviews", reviews);
assertUniqueIds("quotes", quotes);
assertUniqueIds("orders", orders);
assertUniqueIds("pointsMovements", pointsMovements);

const itemIds = new Set([
  ...products.map((product) => product.id),
  ...services.map((service) => service.id),
]);
assert.equal(
  itemIds.size,
  products.length + services.length,
  "un producto y un servicio comparten id"
);

for (const product of products) {
  const category = getCategory(product.categoryId);
  assert.ok(category, `${product.id}: categoría inexistente`);
  assert.equal(
    category.kind,
    "product",
    `${product.id}: apunta a una categoría de servicios`
  );
  assert.ok(getSupplier(product.supplierId), `${product.id}: proveedor inexistente`);
  assert.ok(product.price > 0, `${product.id}: precio inválido`);
  assert.ok(
    Object.keys(product.specs).length >= 4,
    `${product.id}: necesita al menos 4 especificaciones para el comparador`
  );
}

for (const service of services) {
  const category = getCategory(service.categoryId);
  assert.ok(category, `${service.id}: categoría inexistente`);
  assert.equal(
    category.kind,
    "service",
    `${service.id}: apunta a una categoría de productos`
  );
  assert.ok(getSupplier(service.supplierId), `${service.id}: proveedor inexistente`);
  assert.ok(service.price > 0, `${service.id}: precio inválido`);
}

const reviewTargets = new Set([
  ...itemIds,
  ...suppliers.map((supplier) => supplier.id),
]);
for (const review of reviews) {
  assert.ok(
    reviewTargets.has(review.targetId),
    `${review.id}: reseña sin destino (${review.targetId})`
  );
  assert.ok(
    review.rating >= 1 && review.rating <= 5,
    `${review.id}: calificación fuera de rango`
  );
}

for (const quote of quotes) {
  assert.ok(itemIds.has(quote.itemId), `${quote.id}: ítem inexistente`);
  assert.ok(getSupplier(quote.supplierId), `${quote.id}: proveedor inexistente`);
  assert.ok(quote.quantity > 0, `${quote.id}: cantidad inválida`);
  assert.equal(
    quote.status === "sent",
    quote.answer === undefined,
    `${quote.id}: el estado no coincide con tener respuesta`
  );
}

for (const order of orders) {
  const total = order.lines.reduce(
    (sum, line) => sum + line.price * line.quantity,
    0
  );
  assert.equal(order.total, total, `${order.id}: el total no cuadra con las líneas`);
  for (const line of order.lines) {
    assert.ok(itemIds.has(line.itemId), `${order.id}: ítem inexistente en una línea`);
  }
}

assert.equal(ratingFor("lenovo-ideapad-3"), 4.7, "promedio de reseñas incorrecto");
assert.equal(ratingFor("motorola-g84"), null, "un ítem sin reseñas debe dar null");
assert.equal(pointsBalance(), 191, "el saldo de puntos no cuadra");

assert.match(
  formatDate("2026-07-14"),
  /14 de julio/,
  "formatDate corre el día por zona horaria"
);
assert.match(formatServicePrice(60, "hourly"), /por hora/);
assert.match(formatServicePrice(450, "from"), /^Desde/);

assert.equal(
  catalogItems().length,
  products.length + services.length,
  "catalogItems no une productos y servicios"
);
assert.equal(
  searchCatalog({}).length,
  products.length + services.length,
  "una búsqueda vacía debe devolver todo el catálogo"
);
assert.equal(
  searchCatalog({ query: "camaras" }).length,
  1,
  "la búsqueda debe ignorar tildes: camaras vs cámaras"
);
assert.equal(
  searchCatalog({ query: "LAPTOP LENOVO" }).length,
  1,
  "la búsqueda debe ignorar mayúsculas y exigir todos los términos"
);
assert.equal(
  searchCatalog({ query: "16" }).length,
  1,
  "la búsqueda debe conservar los dígitos del término"
);
assert.equal(
  searchCatalog({ categoryId: "laptops" }).length,
  4,
  "el filtro por categoría no devuelve las laptops"
);
assert.equal(
  searchCatalog({ query: "hp", categoryId: "printers" }).length,
  1,
  "el término y la categoría deben combinarse"
);

console.log("Datos mock verificados.");
