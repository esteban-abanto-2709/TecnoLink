"use client";

import { useState, type FormEvent } from "react";
import { CircleCheckBig } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { requestQuote } from "@/lib/quotes";

type QuoteFormProps = {
  itemId: string;
  itemName: string;
  supplierId: string;
  supplierName: string;
};

export function QuoteForm({
  itemId,
  itemName,
  supplierId,
  supplierName,
}: QuoteFormProps) {
  const [sent, setSent] = useState<string | null>(null);
  const [error, setError] = useState("");

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const quantity = Number(data.get("quantity") ?? 1);
    const requirement = String(data.get("requirement") ?? "").trim();

    if (requirement.length < 15) {
      setError(
        "Describe tu requerimiento con un poco más de detalle para que el proveedor pueda cotizar."
      );
      return;
    }

    setError("");
    const quote = requestQuote({ itemId, supplierId, quantity, requirement });
    setSent(quote.id);
  }

  if (sent) {
    return (
      <div className="mt-8 flex flex-col items-center gap-3 rounded-xl border border-border bg-card p-8 text-center">
        <CircleCheckBig className="size-8 text-primary" aria-hidden />
        <h2 className="text-xl font-semibold">Solicitud enviada</h2>
        <p className="max-w-md text-muted-foreground">
          {supplierName} recibió tu solicitud {sent}. Cuando responda vas a ver
          el precio, la vigencia y las condiciones en tus cotizaciones.
        </p>
        <div className="flex flex-wrap justify-center gap-2 pt-2">
          <ButtonLink href="/quotes">Ver mis cotizaciones</ButtonLink>
          <ButtonLink href="/search" variant="outline">
            Seguir explorando
          </ButtonLink>
        </div>
      </div>
    );
  }

  return (
    <form onSubmit={handleSubmit} className="mt-8 max-w-xl space-y-6">
      <div className="space-y-2">
        <Label htmlFor="quantity">Cantidad</Label>
        <Input
          id="quantity"
          name="quantity"
          type="number"
          min={1}
          max={999}
          defaultValue={1}
          required
          className="max-w-32"
        />
      </div>

      <div className="space-y-2">
        <Label htmlFor="requirement">Requerimiento</Label>
        <Textarea
          id="requirement"
          name="requirement"
          rows={5}
          placeholder={`Cuéntale a ${supplierName} para qué necesitas ${itemName}: plazo, lugar de entrega, si necesitas factura, y cualquier detalle que cambie el precio.`}
        />
        <p className="text-xs text-muted-foreground">
          Mientras más claro sea, más precisa será la respuesta.
        </p>
      </div>

      {error ? (
        <p role="alert" className="text-sm text-destructive">
          {error}
        </p>
      ) : null}

      <Button type="submit">Enviar solicitud</Button>
    </form>
  );
}
