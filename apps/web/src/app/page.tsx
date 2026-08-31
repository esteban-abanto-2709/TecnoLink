import Link from "next/link";
import { Search } from "lucide-react";

import { CategoryIcon } from "@/components/category-icon";
import { ItemCard } from "@/components/item-card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { catalogItems, categories } from "@/data/catalog";

export default function Page() {
  const items = catalogItems();
  const featuredProducts = items
    .filter((item) => item.kind === "product")
    .slice(0, 6);
  const featuredServices = items
    .filter((item) => item.kind === "service")
    .slice(0, 3);

  return (
    <main className="flex-1">
      <section className="border-b border-border bg-secondary/40">
        <div className="mx-auto w-full max-w-3xl px-6 py-16 text-center">
          <h1 className="text-3xl font-semibold sm:text-4xl">
            Encuentra, compara y cotiza tecnología en un solo lugar
          </h1>
          <p className="mt-4 text-muted-foreground">
            Equipos y servicios de proveedores de Lima Metropolitana, con
            precios y características que sí se pueden comparar.
          </p>

          <form action="/search" className="mt-8 flex gap-2">
            <Input
              type="search"
              name="q"
              aria-label="Buscar equipos y servicios"
              placeholder="Laptop, impresora, soporte técnico…"
            />
            <Button type="submit" size="lg">
              <Search />
              Buscar
            </Button>
          </form>
        </div>
      </section>

      <div className="mx-auto w-full max-w-6xl space-y-14 px-6 py-14">
        <section className="space-y-5">
          <h2 className="text-xl font-semibold">Explora por categoría</h2>
          <div className="grid grid-cols-2 gap-3 sm:grid-cols-3 lg:grid-cols-5">
            {categories.map((category) => (
              <Link
                key={category.id}
                href={`/search?category=${category.id}`}
                className="flex items-center gap-3 rounded-xl border border-border bg-card p-4 transition-colors hover:border-primary/40 hover:text-primary"
              >
                <CategoryIcon
                  categoryId={category.id}
                  className="size-5 shrink-0 text-muted-foreground"
                />
                <span className="text-sm font-medium">{category.name}</span>
              </Link>
            ))}
          </div>
        </section>

        <section className="space-y-5">
          <div className="flex items-baseline justify-between gap-4">
            <h2 className="text-xl font-semibold">Equipos destacados</h2>
            <Link
              href="/search"
              className="text-sm text-muted-foreground underline-offset-4 hover:text-foreground hover:underline"
            >
              Ver todo el catálogo
            </Link>
          </div>
          <div className="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
            {featuredProducts.map((item) => (
              <ItemCard key={item.id} item={item} />
            ))}
          </div>
        </section>

        <section className="space-y-5">
          <h2 className="text-xl font-semibold">Servicios tecnológicos</h2>
          <div className="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
            {featuredServices.map((item) => (
              <ItemCard key={item.id} item={item} />
            ))}
          </div>
        </section>
      </div>
    </main>
  );
}
