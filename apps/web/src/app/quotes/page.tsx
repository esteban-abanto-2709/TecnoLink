"use client";

import Link from "next/link";
import { FileText } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { Badge } from "@/components/ui/badge";
import { getProduct, getService, getSupplier } from "@/data/catalog";
import type { QuoteStatus } from "@/data/types";
import { formatDate, formatPrice } from "@/lib/format";
import { quoteStatusLabels, useQuotes } from "@/lib/quotes";

const statusVariants: Record<
  QuoteStatus,
  "default" | "secondary" | "outline"
> = {
  sent: "secondary",
  answered: "default",
  expired: "outline",
};

export default function Page() {
  const quotes = useQuotes();

  if (quotes.length === 0) {
    return (
      <main className="mx-auto flex w-full max-w-xl flex-1 flex-col items-center justify-center gap-3 px-6 py-24 text-center">
        <FileText className="size-6 text-muted-foreground" aria-hidden />
        <h1 className="text-2xl font-semibold">No has pedido cotizaciones</h1>
        <p className="text-muted-foreground">
          Desde la ficha de cualquier equipo o servicio puedes pedirle una
          propuesta formal al proveedor.
        </p>
        <ButtonLink href="/search">Ver el catálogo</ButtonLink>
      </main>
    );
  }

  return (
    <main className="mx-auto w-full max-w-4xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Mis cotizaciones</h1>
      <p className="mt-2 text-muted-foreground">
        Todas las respuestas usan el mismo formato, así que se pueden comparar
        entre proveedores.
      </p>

      <div className="mt-8 space-y-6">
        {quotes.map((quote) => {
          const product = getProduct(quote.itemId);
          const service = getService(quote.itemId);
          const item = product ?? service;
          const supplier = getSupplier(quote.supplierId);
          const href = product
            ? `/products/${quote.itemId}`
            : `/services/${quote.itemId}`;

          return (
            <article
              key={quote.id}
              className="rounded-xl border border-border bg-card"
            >
              <header className="flex flex-wrap items-start justify-between gap-3 border-b border-border p-4">
                <div className="min-w-0">
                  <Link href={href} className="font-medium hover:text-primary">
                    {item?.name ?? quote.itemId}
                  </Link>
                  <p className="text-sm text-muted-foreground">
                    {supplier?.name} · Cantidad: {quote.quantity} · Enviada el{" "}
                    {formatDate(quote.requestedOn)}
                  </p>
                </div>
                <Badge variant={statusVariants[quote.status]}>
                  {quoteStatusLabels[quote.status]}
                </Badge>
              </header>

              <div className="space-y-4 p-4">
                <div>
                  <h2 className="text-xs font-semibold uppercase tracking-wide text-muted-foreground">
                    Tu requerimiento
                  </h2>
                  <p className="mt-1 text-sm">{quote.requirement}</p>
                </div>

                {quote.answer ? (
                  <div className="rounded-lg border border-border bg-secondary/30 p-4">
                    <h2 className="text-xs font-semibold uppercase tracking-wide text-muted-foreground">
                      Respuesta del proveedor
                    </h2>
                    <dl className="mt-3 grid gap-3 sm:grid-cols-2">
                      <div>
                        <dt className="text-sm text-muted-foreground">Precio</dt>
                        <dd className="font-heading text-lg font-semibold">
                          {formatPrice(quote.answer.price)}
                        </dd>
                      </div>
                      <div>
                        <dt className="text-sm text-muted-foreground">
                          Válida hasta
                        </dt>
                        <dd className="text-sm">
                          {formatDate(quote.answer.validUntil)}
                        </dd>
                      </div>
                      <div className="sm:col-span-2">
                        <dt className="text-sm text-muted-foreground">
                          Condiciones
                        </dt>
                        <dd className="text-sm">{quote.answer.conditions}</dd>
                      </div>
                      <div className="sm:col-span-2">
                        <dt className="text-sm text-muted-foreground">
                          Observaciones
                        </dt>
                        <dd className="text-sm">{quote.answer.notes}</dd>
                      </div>
                    </dl>
                  </div>
                ) : (
                  <p className="text-sm text-muted-foreground">
                    El proveedor todavía no responde.
                  </p>
                )}
              </div>
            </article>
          );
        })}
      </div>
    </main>
  );
}
