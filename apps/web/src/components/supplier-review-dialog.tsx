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
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { Textarea } from "@/components/ui/textarea";
import {
  reviewSupplier,
  supplierStatusLabels,
  type SupplierStatus,
} from "@/lib/admin";

type SupplierReviewDialogProps = {
  supplierId: string;
  supplierName: string;
  status: SupplierStatus;
  note: string;
};

export function SupplierReviewDialog({
  supplierId,
  supplierName,
  status,
  note,
}: SupplierReviewDialogProps) {
  const [open, setOpen] = useState(false);
  const [next, setNext] = useState<SupplierStatus>(status);
  const [error, setError] = useState("");

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const observation = String(data.get("note") ?? "").trim();

    if (observation.length < 10) {
      setError("Registra una observación que explique la decisión.");
      return;
    }

    setError("");
    reviewSupplier(supplierId, next, observation);
    setOpen(false);
  }

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger
        render={
          <Button variant="outline" size="sm">
            Revisar
          </Button>
        }
      />

      <DialogContent>
        <form onSubmit={handleSubmit}>
          <DialogHeader>
            <DialogTitle>Revisar a {supplierName}</DialogTitle>
            <DialogDescription>
              El estado decide si el proveedor aparece como verificado ante los
              clientes. La observación queda registrada junto al cambio.
            </DialogDescription>
          </DialogHeader>

          <div className="space-y-4 py-4">
            <fieldset className="space-y-2">
              <legend className="text-sm font-medium">Estado</legend>
              <RadioGroup
                value={next}
                onValueChange={(value) => setNext(value as SupplierStatus)}
                className="gap-2"
              >
                {(Object.keys(supplierStatusLabels) as SupplierStatus[]).map(
                  (option) => (
                    <Label
                      key={option}
                      className="flex items-center gap-3 rounded-lg border border-border p-3"
                    >
                      <RadioGroupItem value={option} />
                      {supplierStatusLabels[option]}
                    </Label>
                  )
                )}
              </RadioGroup>
            </fieldset>

            <div className="space-y-2">
              <Label htmlFor={`note-${supplierId}`}>Observación</Label>
              <Textarea
                id={`note-${supplierId}`}
                name="note"
                rows={3}
                defaultValue={note}
                placeholder="Qué se revisó y por qué se toma esta decisión."
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
            <Button type="submit">Guardar revisión</Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
