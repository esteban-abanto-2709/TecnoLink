"use client";

import Link from "next/link";
import { Inbox } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { QuoteAnswerDialog } from "@/components/quote-answer-dialog";
import { Badge } from "@/components/ui/badge";
import { getProduct, getService } from "@/data/catalog";
import type { QuoteStatus } from "@/data/types";
import { formatDate, formatPrice } from "@/lib/format";
import { quoteStatusLabels, useQuotes } from "@/lib/quotes";
import { useActiveSupplierId } from "@/lib/session";
import { useSupplierById } from "@/lib/suppliers";

const statusVariants: Record<
  QuoteStatus,
  "default" | "secondary" | "outline"
> = {
  sent: "secondary",
  answered: "default",
  expired: "outline",
};

export default function Page() {
  const supplierId = useActiveSupplierId();
  const supplier = useSupplierById(supplierId);
  const quotes = useQuotes().filter(
    (quote) => quote.supplierId === supplierId
  );
  const pending = quotes.filter((quote) => quote.status === "sent");

  return (
    <main className="mx-auto w-full max-w-4xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Bandeja de cotizaciones</h1>
      <p className="mt-2 text-muted-foreground">
        Solicitudes recibidas por {supplier?.name}.{" "}
        {pending.length === 0
          ? "No hay ninguna pendiente de respuesta."
          : pending.length === 1
            ? "Hay 1 pendiente de respuesta."
            : `Hay ${pending.length} pendientes de respuesta.`}
      </p>

      {quotes.length === 0 ? (
        <div className="mt-16 flex flex-col items-center gap-3 text-center">
          <Inbox className="size-6 text-muted-foreground" aria-hidden />
          <h2 className="text-lg font-semibold">Bandeja vacía</h2>
          <p className="max-w-md text-muted-foreground">
            Cuando un cliente pida una cotización de tus publicaciones, la vas a
            ver acá.
          </p>
          <ButtonLink href="/supplier/listings" variant="outline">
            Ver mis publicaciones
          </ButtonLink>
        </div>
      ) : (
        <div className="mt-8 space-y-6">
          {quotes.map((quote) => {
            const product = getProduct(quote.itemId);
            const service = getService(quote.itemId);
            const item = product ?? service;
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
                    <Link
                      href={href}
                      className="font-medium hover:text-primary"
                    >
                      {item?.name ?? quote.itemId}
                    </Link>
                    <p className="text-sm text-muted-foreground">
                      {quote.id} · Cantidad: {quote.quantity} · Recibida el{" "}
                      {formatDate(quote.requestedOn)}
                    </p>
                  </div>

                  <div className="flex items-center gap-3">
                    <Badge variant={statusVariants[quote.status]}>
                      {quoteStatusLabels[quote.status]}
                    </Badge>
                    {quote.status === "sent" && item ? (
                      <QuoteAnswerDialog
                        quoteId={quote.id}
                        itemName={item.name}
                        quantity={quote.quantity}
                        suggestedPrice={item.price * quote.quantity}
                      />
                    ) : null}
                  </div>
                </header>

                <div className="space-y-4 p-4">
                  <div>
                    <h2 className="text-xs font-semibold uppercase tracking-wide text-muted-foreground">
                      Requerimiento del cliente
                    </h2>
                    <p className="mt-1 text-sm">{quote.requirement}</p>
                  </div>

                  {quote.answer ? (
                    <div className="rounded-lg border border-border bg-secondary/30 p-4">
                      <h2 className="text-xs font-semibold uppercase tracking-wide text-muted-foreground">
                        Tu respuesta
                      </h2>
                      <dl className="mt-3 grid gap-3 sm:grid-cols-2">
                        <div>
                          <dt className="text-sm text-muted-foreground">
                            Precio
                          </dt>
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
                        {quote.answer.notes ? (
                          <div className="sm:col-span-2">
                            <dt className="text-sm text-muted-foreground">
                              Observaciones
                            </dt>
                            <dd className="text-sm">{quote.answer.notes}</dd>
                          </div>
                        ) : null}
                      </dl>
                    </div>
                  ) : null}
                </div>
              </article>
            );
          })}
        </div>
      )}
    </main>
  );
}
