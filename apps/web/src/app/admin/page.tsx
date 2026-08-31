"use client";

import { LayoutGrid, Package, Store } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { products, services } from "@/data/catalog";
import { useAdminCategories, useAdminSuppliers } from "@/lib/admin";

export default function Page() {
  const categories = useAdminCategories();
  const suppliers = useAdminSuppliers();

  const activeCategories = categories.filter((category) => category.active);
  const pending = suppliers.filter((row) => row.status === "pending");

  const metrics = [
    {
      label: "Categorías activas",
      value: `${activeCategories.length} de ${categories.length}`,
      icon: LayoutGrid,
    },
    {
      label: "Proveedores por revisar",
      value: `${pending.length} de ${suppliers.length}`,
      icon: Store,
    },
    {
      label: "Publicaciones en el catálogo",
      value: products.length + services.length,
      icon: Package,
    },
  ];

  return (
    <main className="mx-auto w-full max-w-5xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Panel de administración</h1>
      <p className="mt-2 text-muted-foreground">
        Supervisión del catálogo y de los proveedores de la plataforma.
      </p>

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
        <ButtonLink href="/admin/categories">Gestionar categorías</ButtonLink>
        <ButtonLink href="/admin/suppliers" variant="outline">
          Revisar proveedores
        </ButtonLink>
      </div>
    </main>
  );
}
