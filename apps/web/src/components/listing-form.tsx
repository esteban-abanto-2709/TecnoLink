"use client";

import { useState, type FormEvent } from "react";
import { CircleCheckBig } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Textarea } from "@/components/ui/textarea";
import { categories } from "@/data/catalog";
import type { Product, Service, ServicePricing } from "@/data/types";
import { createProduct, createService, editListing } from "@/lib/listings";
import { useActiveSupplierId } from "@/lib/session";

const pricingOptions: { id: ServicePricing; label: string }[] = [
  { id: "fixed", label: "Precio cerrado" },
  { id: "from", label: "Precio base (desde)" },
  { id: "hourly", label: "Por hora" },
];

type ListingFormProps = {
  product?: Product;
  service?: Service;
};

export function ListingForm({ product, service }: ListingFormProps) {
  const existing = product ?? service;
  const isEdit = existing !== undefined;

  const [kind, setKind] = useState<"product" | "service">(
    service ? "service" : "product"
  );
  const [categoryId, setCategoryId] = useState(existing?.categoryId ?? "");
  const [pricing, setPricing] = useState<ServicePricing>(
    service?.pricing ?? "fixed"
  );
  const supplierId = useActiveSupplierId();
  const [saved, setSaved] = useState("");
  const [error, setError] = useState("");

  const available = categories.filter((category) =>
    kind === "product" ? category.kind === "product" : category.kind === "service"
  );

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const name = String(data.get("name") ?? "").trim();
    const price = Number(data.get("price") ?? 0);
    const description = String(data.get("description") ?? "").trim();
    const brand = String(data.get("brand") ?? "").trim();
    const coverage = String(data.get("coverage") ?? "").trim();

    if (categoryId === "") {
      setError("Elige una categoría para que aparezca en las búsquedas.");
      return;
    }

    if (description.length < 20) {
      setError("La descripción necesita al menos 20 caracteres.");
      return;
    }

    if (kind === "product" && brand === "") {
      setError("Indica la marca del equipo.");
      return;
    }

    if (kind === "service" && coverage === "") {
      setError("Indica la zona de cobertura del servicio.");
      return;
    }

    setError("");

    if (isEdit) {
      editListing(existing.id, {
        name,
        price,
        description,
        brand: kind === "product" ? brand : undefined,
        coverage: kind === "service" ? coverage : undefined,
        pricing: kind === "service" ? pricing : undefined,
      });
      setSaved(name);
      return;
    }

    if (kind === "product") {
      createProduct({
        name,
        brand,
        categoryId,
        supplierId,
        price,
        description,
      });
    } else {
      createService({
        name,
        categoryId,
        supplierId,
        price,
        pricing,
        description,
        coverage,
      });
    }

    setSaved(name);
  }

  if (saved) {
    return (
      <div className="mt-8 flex flex-col items-center gap-3 rounded-xl border border-border bg-card p-8 text-center">
        <CircleCheckBig className="size-8 text-primary" aria-hidden />
        <h2 className="text-xl font-semibold">
          {isEdit ? "Cambios guardados" : "Publicación creada"}
        </h2>
        <p className="max-w-md text-muted-foreground">
          {saved} ya {isEdit ? "muestra la información actualizada" : "aparece"}{" "}
          en tus publicaciones.
        </p>
        <ButtonLink href="/supplier/listings">Ver mis publicaciones</ButtonLink>
      </div>
    );
  }

  return (
    <form onSubmit={handleSubmit} className="mt-8 max-w-xl space-y-6">
      {isEdit ? null : (
        <fieldset className="space-y-2">
          <legend className="text-sm font-medium">Qué vas a publicar</legend>
          <RadioGroup
            value={kind}
            onValueChange={(value) => {
              setKind(value as "product" | "service");
              setCategoryId("");
            }}
            className="gap-2"
          >
            <Label className="flex items-center gap-3 rounded-lg border border-border p-3">
              <RadioGroupItem value="product" />
              Un equipo
            </Label>
            <Label className="flex items-center gap-3 rounded-lg border border-border p-3">
              <RadioGroupItem value="service" />
              Un servicio
            </Label>
          </RadioGroup>
        </fieldset>
      )}

      <div className="space-y-2">
        <Label htmlFor="name">Nombre</Label>
        <Input
          id="name"
          name="name"
          required
          defaultValue={existing?.name}
          placeholder={
            kind === "product"
              ? "Laptop HP Pavilion 14"
              : "Mantenimiento preventivo de laptop"
          }
        />
      </div>

      {kind === "product" ? (
        <div className="space-y-2">
          <Label htmlFor="brand">Marca</Label>
          <Input id="brand" name="brand" defaultValue={product?.brand} />
        </div>
      ) : null}

      <div className="space-y-2">
        <Label htmlFor="category">Categoría</Label>
        <Select
          value={categoryId}
          onValueChange={(value) => setCategoryId(value ?? "")}
        >
          <SelectTrigger id="category">
            <SelectValue placeholder="Elige una categoría" />
          </SelectTrigger>
          <SelectContent>
            {available.map((category) => (
              <SelectItem key={category.id} value={category.id}>
                {category.name}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>

      <div className="space-y-2">
        <Label htmlFor="price">Precio (S/)</Label>
        <Input
          id="price"
          name="price"
          type="number"
          min={1}
          step={1}
          required
          defaultValue={existing?.price}
          className="max-w-40"
        />
      </div>

      {kind === "service" ? (
        <>
          <fieldset className="space-y-2">
            <legend className="text-sm font-medium">Modalidad de cobro</legend>
            <RadioGroup
              value={pricing}
              onValueChange={(value) => setPricing(value as ServicePricing)}
              className="gap-2"
            >
              {pricingOptions.map((option) => (
                <Label
                  key={option.id}
                  className="flex items-center gap-3 rounded-lg border border-border p-3"
                >
                  <RadioGroupItem value={option.id} />
                  {option.label}
                </Label>
              ))}
            </RadioGroup>
          </fieldset>

          <div className="space-y-2">
            <Label htmlFor="coverage">Zona de cobertura</Label>
            <Input
              id="coverage"
              name="coverage"
              defaultValue={service?.coverage}
              placeholder="Lima Metropolitana"
            />
          </div>
        </>
      ) : null}

      <div className="space-y-2">
        <Label htmlFor="description">Descripción</Label>
        <Textarea
          id="description"
          name="description"
          rows={4}
          defaultValue={existing?.description}
          placeholder="Qué incluye, para quién sirve y qué lo diferencia."
        />
      </div>

      {error ? (
        <p role="alert" className="text-sm text-destructive">
          {error}
        </p>
      ) : null}

      <div className="flex gap-2">
        <Button type="submit">
          {isEdit ? "Guardar cambios" : "Publicar"}
        </Button>
        <ButtonLink href="/supplier/listings" variant="ghost">
          Cancelar
        </ButtonLink>
      </div>
    </form>
  );
}
