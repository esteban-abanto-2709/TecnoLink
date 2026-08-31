import Link from "next/link";
import { FileText } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { CategoryIcon } from "@/components/category-icon";
import { QuoteForm } from "@/components/quote-form";
import { getProduct, getService, getSupplier } from "@/data/catalog";
import { formatPrice, formatServicePrice } from "@/lib/format";

function first(value: string | string[] | undefined): string | undefined {
  return Array.isArray(value) ? value[0] : value;
}

export default async function Page({ searchParams }: PageProps<"/quotes/new">) {
  const params = await searchParams;
  const id = first(params.item) ?? "";

  const product = getProduct(id);
  const service = getService(id);
  const item = product ?? service;
  const supplier = item ? getSupplier(item.supplierId) : undefined;

  if (!item || !supplier) {
    return (
      <main className="mx-auto flex w-full max-w-xl flex-1 flex-col items-center justify-center gap-3 px-6 py-24 text-center">
        <FileText className="size-6 text-muted-foreground" aria-hidden />
        <h1 className="text-2xl font-semibold">Solicitar cotización</h1>
        <p className="text-muted-foreground">
          Las cotizaciones se piden sobre un equipo o servicio. Elige uno del
          catálogo y usa el botón «Solicitar cotización» de su ficha.
        </p>
        <ButtonLink href="/search">Ver el catálogo</ButtonLink>
      </main>
    );
  }

  const href = product ? `/products/${item.id}` : `/services/${item.id}`;
  const price = service
    ? formatServicePrice(service.price, service.pricing)
    : formatPrice(item.price);

  return (
    <main className="mx-auto w-full max-w-3xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Solicitar cotización</h1>
      <p className="mt-2 text-muted-foreground">
        El proveedor responde con un formato estándar: precio, vigencia,
        condiciones y observaciones. Así puedes comparar propuestas de igual a
        igual.
      </p>

      <div className="mt-6 flex items-start gap-4 rounded-xl border border-border bg-card p-4">
        <span className="grid size-14 shrink-0 place-items-center rounded-lg bg-muted">
          <CategoryIcon
            categoryId={item.categoryId}
            className="size-6 text-muted-foreground"
          />
        </span>
        <div className="min-w-0">
          <Link href={href} className="font-medium hover:text-primary">
            {item.name}
          </Link>
          <p className="text-sm text-muted-foreground">
            {supplier.name} · {supplier.district}
          </p>
          <p className="mt-1 text-sm">
            Precio de lista: <span className="font-medium">{price}</span>
          </p>
        </div>
      </div>

      <QuoteForm
        itemId={item.id}
        itemName={item.name}
        supplierId={supplier.id}
        supplierName={supplier.name}
      />
    </main>
  );
}
