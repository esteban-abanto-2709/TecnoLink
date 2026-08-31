import Link from "next/link";
import { SearchX } from "lucide-react";

import { ItemCard } from "@/components/item-card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { getCategory, searchCatalog } from "@/data/catalog";

function first(value: string | string[] | undefined): string | undefined {
  return Array.isArray(value) ? value[0] : value;
}

export default async function Page({ searchParams }: PageProps<"/search">) {
  const params = await searchParams;
  const query = first(params.q)?.trim() ?? "";
  const categoryId = first(params.category) ?? "";
  const category = categoryId ? getCategory(categoryId) : undefined;

  const results = searchCatalog({ query, categoryId });
  const filtered = query !== "" || categoryId !== "";

  return (
    <main className="mx-auto w-full max-w-6xl flex-1 px-6 py-10">
      <header className="space-y-3">
        <h1 className="text-2xl font-semibold">
          {query ? `Resultados para «${query}»` : "Catálogo"}
        </h1>

        <div className="flex flex-wrap items-center gap-3">
          <p className="text-sm text-muted-foreground">
            {results.length === 1
              ? "1 resultado"
              : `${results.length} resultados`}
          </p>

          {category ? (
            <Badge variant="secondary">Categoría: {category.name}</Badge>
          ) : null}

          {filtered ? (
            <Button variant="ghost" size="sm" render={<Link href="/search" />}>
              Limpiar búsqueda
            </Button>
          ) : null}
        </div>
      </header>

      {results.length > 0 ? (
        <div className="mt-8 grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
          {results.map((item) => (
            <ItemCard key={item.id} item={item} />
          ))}
        </div>
      ) : (
        <div className="mt-16 flex flex-col items-center gap-3 text-center">
          <SearchX className="size-6 text-muted-foreground" aria-hidden />
          <h2 className="text-lg font-semibold">Sin coincidencias</h2>
          <p className="max-w-md text-muted-foreground">
            No encontramos equipos ni servicios para esa búsqueda. Prueba con
            menos palabras o revisa el catálogo completo.
          </p>
          <Button variant="outline" render={<Link href="/search" />}>
            Ver todo el catálogo
          </Button>
        </div>
      )}
    </main>
  );
}
