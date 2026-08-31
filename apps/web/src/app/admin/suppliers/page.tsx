"use client";

import Link from "next/link";
import { MapPin } from "lucide-react";

import { SupplierReviewDialog } from "@/components/supplier-review-dialog";
import { Badge } from "@/components/ui/badge";
import {
  supplierStatusLabels,
  useAdminSuppliers,
  type SupplierStatus,
} from "@/lib/admin";
import { formatDate } from "@/lib/format";

const statusVariants: Record<
  SupplierStatus,
  "default" | "secondary" | "destructive"
> = {
  verified: "default",
  pending: "secondary",
  suspended: "destructive",
};

export default function Page() {
  const rows = useAdminSuppliers();
  const pending = rows.filter((row) => row.status === "pending").length;

  return (
    <main className="mx-auto w-full max-w-4xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Gestión de proveedores</h1>
      <p className="mt-1 text-muted-foreground">
        {rows.length} registrados
        {pending > 0 ? `, ${pending} por revisar` : ""}.
      </p>

      <ul className="mt-8 space-y-4">
        {rows.map((row) => (
          <li
            key={row.supplier.id}
            className="rounded-xl border border-border bg-card p-4"
          >
            <div className="flex flex-wrap items-start justify-between gap-3">
              <div className="min-w-0">
                <Link
                  href={`/suppliers/${row.supplier.id}`}
                  className="font-medium hover:text-primary"
                >
                  {row.supplier.name}
                </Link>
                <p className="flex items-center gap-1.5 text-sm text-muted-foreground">
                  <MapPin className="size-3.5" aria-hidden />
                  {row.supplier.district} · Desde {row.supplier.since}
                </p>
              </div>

              <div className="flex items-center gap-3">
                <Badge variant={statusVariants[row.status]}>
                  {supplierStatusLabels[row.status]}
                </Badge>
                <SupplierReviewDialog
                  supplierId={row.supplier.id}
                  supplierName={row.supplier.name}
                  status={row.status}
                  note={row.note}
                />
              </div>
            </div>

            <p className="mt-3 text-sm text-muted-foreground">
              {row.supplier.description}
            </p>

            {row.note ? (
              <div className="mt-3 rounded-lg border border-border bg-secondary/30 p-3">
                <h2 className="text-xs font-semibold uppercase tracking-wide text-muted-foreground">
                  Última observación · {formatDate(row.reviewedOn)}
                </h2>
                <p className="mt-1 text-sm">{row.note}</p>
              </div>
            ) : null}
          </li>
        ))}
      </ul>
    </main>
  );
}
