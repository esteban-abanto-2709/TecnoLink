import { notFound } from "next/navigation";
import { BadgeCheck, CalendarDays, MapPin } from "lucide-react";

import { ItemCard } from "@/components/item-card";
import { Rating } from "@/components/rating";
import { ReviewList } from "@/components/review-list";
import {
  catalogItems,
  getSupplier,
  productsBySupplier,
  servicesBySupplier,
} from "@/data/catalog";

export default async function Page({ params }: PageProps<"/suppliers/[id]">) {
  const { id } = await params;
  const supplier = getSupplier(id);

  if (!supplier) notFound();

  const items = catalogItems();
  const productIds = new Set(
    productsBySupplier(supplier.id).map((product) => product.id)
  );
  const serviceIds = new Set(
    servicesBySupplier(supplier.id).map((service) => service.id)
  );

  const products = items.filter((item) => productIds.has(item.id));
  const services = items.filter((item) => serviceIds.has(item.id));

  return (
    <main className="mx-auto w-full max-w-6xl flex-1 px-6 py-10">
      <header className="space-y-4 border-b border-border pb-8">
        <h1 className="text-3xl font-semibold">{supplier.name}</h1>

        <div className="flex flex-wrap items-center gap-x-5 gap-y-2 text-sm text-muted-foreground">
          <span className="flex items-center gap-1.5">
            <MapPin className="size-4" aria-hidden />
            {supplier.district}
          </span>
          <span className="flex items-center gap-1.5">
            <CalendarDays className="size-4" aria-hidden />
            En la plataforma desde {supplier.since}
          </span>
          {supplier.verified ? (
            <span className="flex items-center gap-1.5 text-primary">
              <BadgeCheck className="size-4" aria-hidden />
              Proveedor verificado
            </span>
          ) : null}
          <Rating targetId={supplier.id} />
        </div>

        <p className="max-w-prose text-muted-foreground">
          {supplier.description}
        </p>
      </header>

      {products.length > 0 ? (
        <section className="mt-10 space-y-5">
          <h2 className="text-xl font-semibold">
            Equipos publicados
            <span className="ml-2 text-base font-normal text-muted-foreground">
              {products.length}
            </span>
          </h2>
          <div className="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
            {products.map((item) => (
              <ItemCard key={item.id} item={item} />
            ))}
          </div>
        </section>
      ) : null}

      {services.length > 0 ? (
        <section className="mt-10 space-y-5">
          <h2 className="text-xl font-semibold">
            Servicios publicados
            <span className="ml-2 text-base font-normal text-muted-foreground">
              {services.length}
            </span>
          </h2>
          <div className="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
            {services.map((item) => (
              <ItemCard key={item.id} item={item} />
            ))}
          </div>
        </section>
      ) : null}

      <section className="mt-10 max-w-2xl space-y-5">
        <h2 className="text-xl font-semibold">Reseñas del proveedor</h2>
        <ReviewList targetId={supplier.id} />
      </section>
    </main>
  );
}
