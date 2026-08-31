import Link from "next/link";
import { notFound } from "next/navigation";
import { ChevronRight, FileText, MapPin } from "lucide-react";

import { AddToCartButton } from "@/components/add-to-cart-button";
import { ButtonLink } from "@/components/button-link";
import { CategoryIcon } from "@/components/category-icon";
import { Rating } from "@/components/rating";
import { ReviewList } from "@/components/review-list";
import { SupplierCard } from "@/components/supplier-card";
import { Badge } from "@/components/ui/badge";
import { getCategory, getService, getSupplier } from "@/data/catalog";
import { formatServicePrice } from "@/lib/format";

const pricingNotes = {
  fixed: "Precio cerrado por el servicio completo.",
  from: "Precio base. El monto final se define en la cotización.",
  hourly: "Se cobra por hora trabajada, con un mínimo de una hora.",
};

export default async function Page({ params }: PageProps<"/services/[id]">) {
  const { id } = await params;
  const service = getService(id);

  if (!service) notFound();

  const category = getCategory(service.categoryId);
  const supplier = getSupplier(service.supplierId);
  const price = formatServicePrice(service.price, service.pricing);

  return (
    <main className="mx-auto w-full max-w-6xl flex-1 px-6 py-10">
      <nav
        aria-label="Ruta de navegación"
        className="flex flex-wrap items-center gap-1 text-sm text-muted-foreground"
      >
        <Link href="/search?type=service" className="hover:text-foreground">
          Servicios
        </Link>
        {category ? (
          <>
            <ChevronRight className="size-3.5" aria-hidden />
            <Link
              href={`/search?category=${category.id}`}
              className="hover:text-foreground"
            >
              {category.name}
            </Link>
          </>
        ) : null}
        <ChevronRight className="size-3.5" aria-hidden />
        <span className="text-foreground">{service.name}</span>
      </nav>

      <div className="mt-6 grid gap-10 lg:grid-cols-[1fr_320px]">
        <div className="space-y-10">
          <div className="grid gap-8 sm:grid-cols-2">
            <div className="grid aspect-square place-items-center rounded-xl border border-border bg-muted">
              <CategoryIcon
                categoryId={service.categoryId}
                className="size-16 text-muted-foreground"
              />
            </div>

            <div className="space-y-4">
              {category ? (
                <Badge variant="secondary">{category.name}</Badge>
              ) : null}
              <h1 className="text-2xl font-semibold">{service.name}</h1>
              <Rating targetId={service.id} />
              <p className="font-heading text-3xl font-semibold">{price}</p>
              <p className="text-muted-foreground">{service.description}</p>
            </div>
          </div>

          <section className="space-y-4">
            <h2 className="text-lg font-semibold">Alcance del servicio</h2>
            <dl className="divide-y divide-border border-y border-border">
              <div className="grid grid-cols-2 gap-4 py-3 text-sm sm:grid-cols-3">
                <dt className="text-muted-foreground">Modalidad de cobro</dt>
                <dd className="sm:col-span-2">
                  {pricingNotes[service.pricing]}
                </dd>
              </div>
              <div className="grid grid-cols-2 gap-4 py-3 text-sm sm:grid-cols-3">
                <dt className="text-muted-foreground">Zona de cobertura</dt>
                <dd className="flex items-center gap-1.5 sm:col-span-2">
                  <MapPin className="size-3.5 text-muted-foreground" aria-hidden />
                  {service.coverage}
                </dd>
              </div>
              <div className="grid grid-cols-2 gap-4 py-3 text-sm sm:grid-cols-3">
                <dt className="text-muted-foreground">Qué incluye</dt>
                <dd className="sm:col-span-2">{service.description}</dd>
              </div>
            </dl>
          </section>

          <section className="space-y-4">
            <h2 className="text-lg font-semibold">Reseñas</h2>
            <ReviewList targetId={service.id} />
          </section>
        </div>

        <aside className="space-y-4 lg:sticky lg:top-6 lg:self-start">
          <div className="space-y-2 rounded-xl border border-border bg-card p-4">
            <p className="font-heading text-2xl font-semibold">{price}</p>
            <p className="text-sm text-muted-foreground">
              {pricingNotes[service.pricing]}
            </p>
            <div className="grid gap-2 pt-2">
              <ButtonLink href={`/quotes/new?item=${service.id}`}>
                <FileText />
                Solicitar cotización
              </ButtonLink>
              <AddToCartButton
                variant="secondary"
                label="Contratar ahora"
                item={{
                  id: service.id,
                  kind: "service",
                  name: service.name,
                  price: service.price,
                }}
              />
            </div>
          </div>

          {supplier ? <SupplierCard supplier={supplier} /> : null}
        </aside>
      </div>
    </main>
  );
}
