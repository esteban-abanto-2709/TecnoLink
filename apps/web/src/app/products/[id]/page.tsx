import Link from "next/link";
import { notFound } from "next/navigation";
import { ChevronRight, FileText } from "lucide-react";

import { AddToCartButton } from "@/components/add-to-cart-button";
import { ButtonLink } from "@/components/button-link";
import { CompareToggle } from "@/components/compare-toggle";
import { ItemThumb } from "@/components/item-thumb";
import { Rating } from "@/components/rating";
import { ReviewList } from "@/components/review-list";
import { SupplierCard } from "@/components/supplier-card";
import { Badge } from "@/components/ui/badge";
import { getCategory, getProduct, getSupplier } from "@/data/catalog";
import { formatPrice } from "@/lib/format";

export default async function Page({ params }: PageProps<"/products/[id]">) {
  const { id } = await params;
  const product = getProduct(id);

  if (!product) notFound();

  const category = getCategory(product.categoryId);
  const supplier = getSupplier(product.supplierId);

  return (
    <main className="mx-auto w-full max-w-6xl flex-1 px-6 py-10">
      <nav
        aria-label="Ruta de navegación"
        className="flex flex-wrap items-center gap-1 text-sm text-muted-foreground"
      >
        <Link href="/search" className="hover:text-foreground">
          Catálogo
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
        <span className="text-foreground">{product.name}</span>
      </nav>

      <div className="mt-6 grid gap-10 lg:grid-cols-[1fr_320px]">
        <div className="space-y-10">
          <div className="grid gap-8 sm:grid-cols-2">
            <ItemThumb
              categoryId={product.categoryId}
              label={product.brand}
              className="aspect-square rounded-xl border border-border"
              iconClassName="size-16"
            />

            <div className="space-y-4">
              {category ? (
                <Badge variant="secondary">{category.name}</Badge>
              ) : null}
              <h1 className="text-2xl font-semibold">{product.name}</h1>
              <p className="text-sm text-muted-foreground">
                Marca: {product.brand}
              </p>
              <Rating targetId={product.id} />
              <p className="font-heading text-3xl font-semibold">
                {formatPrice(product.price)}
              </p>
              <p className="text-muted-foreground">{product.description}</p>
            </div>
          </div>

          <section className="space-y-4">
            <h2 className="text-lg font-semibold">Especificaciones</h2>
            <dl className="divide-y divide-border border-y border-border">
              {Object.entries(product.specs).map(([label, value]) => (
                <div
                  key={label}
                  className="grid grid-cols-2 gap-4 py-3 text-sm sm:grid-cols-3"
                >
                  <dt className="text-muted-foreground">{label}</dt>
                  <dd className="sm:col-span-2">{value}</dd>
                </div>
              ))}
            </dl>
          </section>

          <section className="space-y-4">
            <h2 className="text-lg font-semibold">Reseñas</h2>
            <ReviewList targetId={product.id} />
          </section>
        </div>

        <aside className="space-y-4 lg:sticky lg:top-6 lg:self-start">
          <div className="space-y-2 rounded-xl border border-border bg-card p-4">
            <p className="font-heading text-2xl font-semibold">
              {formatPrice(product.price)}
            </p>
            <p className="text-sm text-muted-foreground">
              Precio referencial. La compra es simulada: no se cobra nada.
            </p>
            <div className="grid gap-2 pt-2">
              <AddToCartButton
                item={{
                  id: product.id,
                  kind: "product",
                  name: product.name,
                  price: product.price,
                }}
              />
              <ButtonLink
                href={`/quotes/new?item=${product.id}`}
                variant="secondary"
              >
                <FileText />
                Solicitar cotización
              </ButtonLink>
              <CompareToggle productId={product.id} label="Comparar" />
            </div>
          </div>

          {supplier ? <SupplierCard supplier={supplier} /> : null}
        </aside>
      </div>
    </main>
  );
}
