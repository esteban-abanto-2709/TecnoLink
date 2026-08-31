"use client";

import { useState, type FormEvent } from "react";

import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogClose,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { answerQuote } from "@/lib/quotes";

type QuoteAnswerDialogProps = {
  quoteId: string;
  itemName: string;
  quantity: number;
  suggestedPrice: number;
};

export function QuoteAnswerDialog({
  quoteId,
  itemName,
  quantity,
  suggestedPrice,
}: QuoteAnswerDialogProps) {
  const [open, setOpen] = useState(false);
  const [error, setError] = useState("");

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const price = Number(data.get("price") ?? 0);
    const validUntil = String(data.get("validUntil") ?? "");
    const conditions = String(data.get("conditions") ?? "").trim();
    const notes = String(data.get("notes") ?? "").trim();

    if (price <= 0) {
      setError("El precio tiene que ser mayor a cero.");
      return;
    }

    if (conditions.length < 10) {
      setError("Detalla las condiciones: plazo de entrega, garantía, envío.");
      return;
    }

    setError("");
    answerQuote(quoteId, { price, validUntil, conditions, notes });
    setOpen(false);
  }

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger render={<Button size="sm">Responder</Button>} />

      <DialogContent>
        <form onSubmit={handleSubmit}>
          <DialogHeader>
            <DialogTitle>Responder cotización</DialogTitle>
            <DialogDescription>
              {quantity} × {itemName}. Todas las respuestas usan este formato
              para que el cliente pueda compararlas.
            </DialogDescription>
          </DialogHeader>

          <div className="space-y-4 py-4">
            <div className="grid gap-4 sm:grid-cols-2">
              <div className="space-y-2">
                <Label htmlFor={`price-${quoteId}`}>Precio total (S/)</Label>
                <Input
                  id={`price-${quoteId}`}
                  name="price"
                  type="number"
                  min={1}
                  step={1}
                  defaultValue={suggestedPrice}
                  required
                />
              </div>

              <div className="space-y-2">
                <Label htmlFor={`valid-${quoteId}`}>Válida hasta</Label>
                <Input
                  id={`valid-${quoteId}`}
                  name="validUntil"
                  type="date"
                  required
                />
              </div>
            </div>

            <div className="space-y-2">
              <Label htmlFor={`conditions-${quoteId}`}>Condiciones</Label>
              <Textarea
                id={`conditions-${quoteId}`}
                name="conditions"
                rows={3}
                placeholder="Plazo de entrega, garantía, forma de envío, requisitos de facturación."
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor={`notes-${quoteId}`}>Observaciones</Label>
              <Textarea
                id={`notes-${quoteId}`}
                name="notes"
                rows={2}
                placeholder="Lo que el cliente debe saber y no entra en las condiciones."
              />
            </div>

            {error ? (
              <p role="alert" className="text-sm text-destructive">
                {error}
              </p>
            ) : null}
          </div>

          <DialogFooter>
            <DialogClose
              render={
                <Button type="button" variant="ghost">
                  Cancelar
                </Button>
              }
            />
            <Button type="submit">Enviar respuesta</Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
