"use client";

import { BadgeCheck, Clock, FileText, Inbox, Package } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { useSupplierListings } from "@/lib/listings";
import { useQuotes } from "@/lib/quotes";
import { useActiveSupplierId } from "@/lib/session";
import { isMockSupplier, useSupplierById } from "@/lib/suppliers";

export default function Page() {
  const supplierId = useActiveSupplierId();
  const supplier = useSupplierById(supplierId);
  const { products, services } = useSupplierListings(supplierId);
  const quotes = useQuotes().filter(
    (quote) => quote.supplierId === supplierId
  );

  if (!supplier) return null;

  const listings = products.length + services.length;
  const pending = quotes.filter((quote) => quote.status === "sent").length;
  const answered = quotes.filter((quote) => quote.status === "answered").length;

  const metrics = [
    { label: "Publicaciones activas", value: listings, icon: Package },
    { label: "Cotizaciones por responder", value: pending, icon: Inbox },
    { label: "Cotizaciones respondidas", value: answered, icon: FileText },
  ];

  return (
    <main className="mx-auto w-full max-w-5xl flex-1 px-6 py-10">
      <header className="space-y-2">
        <h1 className="text-2xl font-semibold">Panel del proveedor</h1>
        <p className="flex flex-wrap items-center gap-2 text-muted-foreground">
          {supplier.name} · {supplier.district}
          {supplier.verified ? (
            <span className="flex items-center gap-1 text-sm text-primary">
              <BadgeCheck className="size-4" aria-hidden />
              Verificado
            </span>
          ) : (
            <span className="flex items-center gap-1 text-sm">
              <Clock className="size-4" aria-hidden />
              Por revisar
            </span>
          )}
        </p>
        <p className="text-sm text-muted-foreground">
          RUC {supplier.ruc} · {supplier.phone}
        </p>
      </header>

      <div className="mt-8 grid gap-4 sm:grid-cols-3">
        {metrics.map((metric) => (
          <div
            key={metric.label}
            className="rounded-xl border border-border bg-card p-4"
          >
            <metric.icon className="size-5 text-muted-foreground" aria-hidden />
            <p className="mt-3 font-heading text-3xl font-semibold">
              {metric.value}
            </p>
            <p className="text-sm text-muted-foreground">{metric.label}</p>
          </div>
        ))}
      </div>

      <div className="mt-8 flex flex-wrap gap-2">
        <ButtonLink href="/supplier/quotes">
          Ver bandeja de cotizaciones
        </ButtonLink>
        <ButtonLink href="/supplier/listings" variant="outline">
          Mis publicaciones
        </ButtonLink>
        {isMockSupplier(supplier.id) ? (
          <ButtonLink href={`/suppliers/${supplier.id}`} variant="ghost">
            Ver mi perfil público
          </ButtonLink>
        ) : null}
      </div>
    </main>
  );
}
