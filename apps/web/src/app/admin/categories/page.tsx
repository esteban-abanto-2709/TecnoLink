"use client";

import { CategoryDialog } from "@/components/category-dialog";
import { CategoryIcon } from "@/components/category-icon";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { toggleCategory, useAdminCategories } from "@/lib/admin";

export default function Page() {
  const categories = useAdminCategories();
  const active = categories.filter((category) => category.active).length;

  return (
    <main className="mx-auto w-full max-w-4xl flex-1 px-6 py-10">
      <div className="flex flex-wrap items-center justify-between gap-3">
        <div>
          <h1 className="text-2xl font-semibold">Gestión de categorías</h1>
          <p className="mt-1 text-muted-foreground">
            {active} activas de {categories.length}. Una categoría desactivada
            deja de ofrecerse al publicar y de aparecer en los filtros.
          </p>
        </div>
        <CategoryDialog />
      </div>

      <ul className="mt-8 divide-y divide-border border-y border-border">
        {categories.map((category) => (
          <li key={category.id} className="flex flex-wrap items-center gap-4 py-4">
            <span className="grid size-10 shrink-0 place-items-center rounded-lg bg-muted">
              <CategoryIcon
                categoryId={category.id}
                className="size-5 text-muted-foreground"
              />
            </span>

            <div className="min-w-0 flex-1">
              <p className="font-medium">{category.name}</p>
              <p className="text-sm text-muted-foreground">
                {category.kind === "product" ? "Equipos" : "Servicios"}
              </p>
            </div>

            <Badge variant={category.active ? "secondary" : "outline"}>
              {category.active ? "Activa" : "Desactivada"}
            </Badge>

            <div className="flex gap-2">
              <CategoryDialog category={category} />
              <Button
                variant="ghost"
                size="sm"
                onClick={() => toggleCategory(category.id)}
              >
                {category.active ? "Desactivar" : "Reactivar"}
              </Button>
            </div>
          </li>
        ))}
      </ul>
    </main>
  );
}
