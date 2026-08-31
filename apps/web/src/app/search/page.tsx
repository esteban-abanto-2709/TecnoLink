import Link from "next/link";
import { SearchX } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { ItemCard } from "@/components/item-card";
import { Badge } from "@/components/ui/badge";
import { categories, getCategory, searchCatalog } from "@/data/catalog";
import type { CategoryKind } from "@/data/types";
import { cn } from "@/lib/utils";

type SearchState = {
  q?: string;
  type?: string;
  category?: string;
  price?: string;
};

const priceRanges = [
  { id: "0-500", label: "Hasta S/ 500", max: 500 },
  { id: "500-1500", label: "S/ 500 a S/ 1,500", min: 500, max: 1500 },
  { id: "1500-3000", label: "S/ 1,500 a S/ 3,000", min: 1500, max: 3000 },
  { id: "3000", label: "Más de S/ 3,000", min: 3000 },
];

const kinds: { id: CategoryKind; label: string }[] = [
  { id: "product", label: "Equipos" },
  { id: "service", label: "Servicios" },
];

function first(value: string | string[] | undefined): string | undefined {
  return Array.isArray(value) ? value[0] : value;
}

function searchHref(state: SearchState, change: SearchState): string {
  const params = new URLSearchParams();
  for (const [key, value] of Object.entries({ ...state, ...change })) {
    if (value) params.set(key, value);
  }
  const query = params.toString();
  return query ? `/search?${query}` : "/search";
}

type FilterGroupProps = {
  title: string;
  options: { id: string; label: string }[];
  active?: string;
  state: SearchState;
  param: keyof SearchState;
};

function FilterGroup({ title, options, active, state, param }: FilterGroupProps) {
  const entries = [{ id: "", label: "Todos" }, ...options];

  return (
    <div>
      <h2 className="text-xs font-semibold uppercase tracking-wide text-muted-foreground">
        {title}
      </h2>
      <ul className="mt-2 space-y-0.5">
        {entries.map((option) => {
          const selected = (active ?? "") === option.id;
          return (
            <li key={option.id || "all"}>
              <Link
                href={searchHref(state, { [param]: option.id })}
                aria-current={selected ? "true" : undefined}
                className={cn(
                  "block rounded-md px-2 py-1.5 text-sm transition-colors",
                  selected
                    ? "bg-secondary font-medium text-secondary-foreground"
                    : "text-muted-foreground hover:bg-muted hover:text-foreground"
                )}
              >
                {option.label}
              </Link>
            </li>
          );
        })}
      </ul>
    </div>
  );
}

export default async function Page({ searchParams }: PageProps<"/search">) {
  const params = await searchParams;
  const state: SearchState = {
    q: first(params.q)?.trim() || undefined,
    type: first(params.type) || undefined,
    category: first(params.category) || undefined,
    price: first(params.price) || undefined,
  };

  const category = state.category ? getCategory(state.category) : undefined;
  const range = priceRanges.find((option) => option.id === state.price);
  const kind = kinds.find((option) => option.id === state.type)?.id;

  const results = searchCatalog({
    query: state.q,
    categoryId: category?.id,
    kind,
    minPrice: range?.min,
    maxPrice: range?.max,
  });

  const filtered = Boolean(state.q || category || range || kind);

  return (
    <main className="mx-auto w-full max-w-6xl flex-1 px-6 py-10">
      <header className="space-y-3">
        <h1 className="text-2xl font-semibold">
          {state.q ? `Resultados para «${state.q}»` : "Catálogo"}
        </h1>

        <div className="flex flex-wrap items-center gap-3">
          <p className="text-sm text-muted-foreground">
            {results.length === 1
              ? "1 resultado"
              : `${results.length} resultados`}
          </p>

          {category ? <Badge variant="secondary">{category.name}</Badge> : null}
          {range ? <Badge variant="secondary">{range.label}</Badge> : null}

          {filtered ? (
            <ButtonLink href="/search" variant="ghost" size="sm">
              Limpiar filtros
            </ButtonLink>
          ) : null}
        </div>
      </header>

      <div className="mt-8 grid gap-8 lg:grid-cols-[200px_1fr]">
        <aside className="space-y-6">
          <FilterGroup
            title="Tipo"
            param="type"
            options={kinds}
            active={state.type}
            state={state}
          />
          <FilterGroup
            title="Categoría"
            param="category"
            options={categories.map((item) => ({
              id: item.id,
              label: item.name,
            }))}
            active={state.category}
            state={state}
          />
          <FilterGroup
            title="Precio"
            param="price"
            options={priceRanges}
            active={state.price}
            state={state}
          />
        </aside>

        {results.length > 0 ? (
          <div className="grid content-start gap-4 sm:grid-cols-2 xl:grid-cols-3">
            {results.map((item) => (
              <ItemCard key={item.id} item={item} />
            ))}
          </div>
        ) : (
          <div className="flex flex-col items-center gap-3 py-16 text-center">
            <SearchX className="size-6 text-muted-foreground" aria-hidden />
            <h2 className="text-lg font-semibold">Sin coincidencias</h2>
            <p className="max-w-md text-muted-foreground">
              Ningún equipo ni servicio cumple con todos los filtros. Prueba
              quitando alguno o revisa el catálogo completo.
            </p>
            <ButtonLink href="/search" variant="outline">
              Ver todo el catálogo
            </ButtonLink>
          </div>
        )}
      </div>
    </main>
  );
}
