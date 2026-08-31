"use client";

import Link from "next/link";
import { Scale, X } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { CategoryIcon } from "@/components/category-icon";
import { Button } from "@/components/ui/button";
import { getProduct, getSupplier } from "@/data/catalog";
import type { Product, Review } from "@/data/types";
import {
  MAX_COMPARE,
  clearCompare,
  removeFromCompare,
  useCompare,
} from "@/lib/compare";
import { formatPrice } from "@/lib/format";
import { averageOf, reviewsWith, useOwnReviews } from "@/lib/reviews";

type Row = {
  label: string;
  values: string[];
};

function buildRows(products: Product[], ownReviews: Review[]): Row[] {
  const specKeys = Array.from(
    new Set(products.flatMap((product) => Object.keys(product.specs)))
  );

  return [
    {
      label: "Precio",
      values: products.map((product) => formatPrice(product.price)),
    },
    { label: "Marca", values: products.map((product) => product.brand) },
    {
      label: "Proveedor",
      values: products.map(
        (product) => getSupplier(product.supplierId)?.name ?? "—"
      ),
    },
    {
      label: "Calificación",
      values: products.map((product) => {
        const rating = averageOf(reviewsWith(ownReviews, product.id));
        return rating === null ? "Sin reseñas" : `${rating.toFixed(1)} de 5`;
      }),
    },
    ...specKeys.map((key) => ({
      label: key,
      values: products.map((product) => product.specs[key] ?? "—"),
    })),
  ];
}

export default function Page() {
  const { ids } = useCompare();
  const ownReviews = useOwnReviews();
  const products = ids
    .map((id) => getProduct(id))
    .filter((product): product is Product => product !== undefined);

  if (products.length < 2) {
    return (
      <main className="mx-auto flex w-full max-w-xl flex-1 flex-col items-center justify-center gap-3 px-6 py-24 text-center">
        <Scale className="size-6 text-muted-foreground" aria-hidden />
        <h1 className="text-2xl font-semibold">Comparador de equipos</h1>
        <p className="text-muted-foreground">
          {products.length === 0
            ? `Elige hasta ${MAX_COMPARE} equipos desde el catálogo para verlos enfrentados atributo por atributo.`
            : "Falta al menos un equipo más para poder comparar."}
        </p>
        <ButtonLink href="/search?type=product">Ver equipos</ButtonLink>
      </main>
    );
  }

  const rows = buildRows(products, ownReviews);

  return (
    <main className="mx-auto w-full max-w-6xl flex-1 px-6 py-10">
      <div className="flex flex-wrap items-center justify-between gap-3">
        <div>
          <h1 className="text-2xl font-semibold">Comparador de equipos</h1>
          <p className="mt-1 text-sm text-muted-foreground">
            Las filas resaltadas son las que tienen diferencias entre los
            equipos.
          </p>
        </div>
        <Button variant="ghost" size="sm" onClick={clearCompare}>
          Vaciar comparador
        </Button>
      </div>

      <div className="mt-8 overflow-x-auto">
        <table className="w-full min-w-3xl border-collapse text-sm">
          <thead>
            <tr>
              <th className="w-40 border-b border-border p-3 text-left align-bottom text-xs font-semibold uppercase tracking-wide text-muted-foreground">
                Atributo
              </th>
              {products.map((product) => (
                <th
                  key={product.id}
                  className="border-b border-border p-3 text-left align-bottom"
                >
                  <div className="flex items-start justify-between gap-2">
                    <span className="grid size-10 shrink-0 place-items-center rounded-lg bg-muted">
                      <CategoryIcon
                        categoryId={product.categoryId}
                        className="size-5 text-muted-foreground"
                      />
                    </span>
                    <Button
                      variant="ghost"
                      size="icon-xs"
                      aria-label={`Quitar ${product.name} del comparador`}
                      onClick={() => removeFromCompare(product.id)}
                    >
                      <X />
                    </Button>
                  </div>
                  <Link
                    href={`/products/${product.id}`}
                    className="mt-2 block font-medium hover:text-primary"
                  >
                    {product.name}
                  </Link>
                </th>
              ))}
            </tr>
          </thead>

          <tbody>
            {rows.map((row) => {
              const differs = new Set(row.values).size > 1;
              return (
                <tr
                  key={row.label}
                  className={differs ? "bg-secondary/40" : undefined}
                >
                  <th
                    scope="row"
                    className="border-b border-border p-3 text-left font-medium text-muted-foreground"
                  >
                    {row.label}
                  </th>
                  {row.values.map((value, index) => (
                    <td
                      key={products[index].id}
                      className="border-b border-border p-3 align-top"
                    >
                      {value}
                    </td>
                  ))}
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </main>
  );
}
