import { BadgeCheck, MapPin, Store } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { Rating } from "@/components/rating";
import type { Supplier } from "@/data/types";

type SupplierCardProps = {
  supplier: Supplier;
};

export function SupplierCard({ supplier }: SupplierCardProps) {
  return (
    <div className="rounded-xl border border-border bg-card p-4">
      <div className="flex items-start gap-3">
        <span className="grid size-10 shrink-0 place-items-center rounded-lg bg-muted">
          <Store className="size-5 text-muted-foreground" aria-hidden />
        </span>

        <div className="min-w-0 space-y-1">
          <p className="font-medium leading-snug">{supplier.name}</p>
          <p className="flex items-center gap-1 text-sm text-muted-foreground">
            <MapPin className="size-3.5" aria-hidden />
            {supplier.district}
          </p>
          {supplier.verified ? (
            <p className="flex items-center gap-1 text-sm text-primary">
              <BadgeCheck className="size-3.5" aria-hidden />
              Proveedor verificado
            </p>
          ) : null}
        </div>
      </div>

      <div className="mt-4 flex items-center justify-between gap-3">
        <Rating targetId={supplier.id} />
        <ButtonLink
          href={`/suppliers/${supplier.id}`}
          variant="outline"
          size="sm"
        >
          Ver perfil
        </ButtonLink>
      </div>
    </div>
  );
}
