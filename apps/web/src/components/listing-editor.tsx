"use client";

import { ButtonLink } from "@/components/button-link";
import { ListingForm } from "@/components/listing-form";
import { useListing } from "@/lib/listings";
import { useActiveSupplierId } from "@/lib/session";

type ListingEditorProps = {
  id: string;
};

export function ListingEditor({ id }: ListingEditorProps) {
  const listing = useListing(useActiveSupplierId(), id);

  if (!listing) {
    return (
      <div className="flex flex-col items-center gap-3 py-16 text-center">
        <h1 className="text-2xl font-semibold">Publicación no encontrada</h1>
        <p className="text-muted-foreground">
          Esta publicación no existe o no pertenece a tu cuenta.
        </p>
        <ButtonLink href="/supplier/listings">Ver mis publicaciones</ButtonLink>
      </div>
    );
  }

  const name =
    listing.kind === "product" ? listing.product.name : listing.service.name;

  return (
    <>
      <h1 className="text-2xl font-semibold">Editar publicación</h1>
      <p className="mt-2 text-muted-foreground">
        Estás editando {name}. Los cambios se ven de inmediato en tus
        publicaciones.
      </p>

      {listing.kind === "product" ? (
        <ListingForm product={listing.product} />
      ) : (
        <ListingForm service={listing.service} />
      )}
    </>
  );
}
