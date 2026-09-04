"use client";

import Link from "next/link";
import { Pencil, Plus } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { CategoryIcon } from "@/components/category-icon";
import { Badge } from "@/components/ui/badge";
import { getCategory } from "@/data/catalog";
import { formatPrice, formatServicePrice } from "@/lib/format";
import { useSupplierListings } from "@/lib/listings";
import { useActiveSupplierId } from "@/lib/session";

export default function Page() {
  const supplierId = useActiveSupplierId();
  const { products, services } = useSupplierListings(supplierId);

  const rows = [
    ...products.map((product) => ({
      id: product.id,
      name: product.name,
      categoryId: product.categoryId,
      price: formatPrice(product.price),
      kind: "Equipo",
    })),
    ...services.map((service) => ({
      id: service.id,
      name: service.name,
      categoryId: service.categoryId,
      price: formatServicePrice(service.price, service.pricing),
      kind: "Servicio",
    })),
  ];

  return (
    <main className="mx-auto w-full max-w-4xl flex-1 px-6 py-10">
      <div className="flex flex-wrap items-center justify-between gap-3">
        <div>
          <h1 className="text-2xl font-semibold">Mis publicaciones</h1>
          <p className="mt-1 text-muted-foreground">
            {rows.length} {rows.length === 1 ? "publicación" : "publicaciones"}{" "}
            activas.
          </p>
        </div>
        <ButtonLink href="/supplier/listings/new">
          <Plus />
          Publicar
        </ButtonLink>
      </div>

      <ul className="mt-8 divide-y divide-border border-y border-border">
        {rows.map((row) => {
          const category = getCategory(row.categoryId);

          return (
            <li
              key={row.id}
              className="flex flex-wrap items-center gap-4 py-4"
            >
              <span className="grid size-12 shrink-0 place-items-center rounded-lg bg-muted">
                <CategoryIcon
                  categoryId={row.categoryId}
                  className="size-5 text-muted-foreground"
                />
              </span>

              <div className="min-w-0 flex-1">
                <Link
                  href={`/supplier/listings/${row.id}`}
                  className="font-medium hover:text-primary"
                >
                  {row.name}
                </Link>
                <p className="flex flex-wrap items-center gap-2 text-sm text-muted-foreground">
                  <Badge variant="outline">{row.kind}</Badge>
                  {category?.name}
                </p>
              </div>

              <span className="font-medium">{row.price}</span>

              <ButtonLink
                href={`/supplier/listings/${row.id}`}
                variant="outline"
                size="sm"
              >
                <Pencil />
                Editar
              </ButtonLink>
            </li>
          );
        })}
      </ul>
    </main>
  );
}
