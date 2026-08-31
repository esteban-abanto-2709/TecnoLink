"use client";

import { useState, type FormEvent } from "react";
import { Plus } from "lucide-react";

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
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import type { CategoryKind } from "@/data/types";
import {
  createCategory,
  isDuplicateCategory,
  renameCategory,
} from "@/lib/admin";

type CategoryDialogProps = {
  category?: { id: string; name: string; kind: CategoryKind };
};

export function CategoryDialog({ category }: CategoryDialogProps) {
  const isEdit = category !== undefined;
  const [open, setOpen] = useState(false);
  const [kind, setKind] = useState<CategoryKind>(category?.kind ?? "product");
  const [error, setError] = useState("");

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const name = String(data.get("name") ?? "").trim();

    if (name.length < 3) {
      setError("El nombre necesita al menos 3 caracteres.");
      return;
    }

    if (isDuplicateCategory(name, category?.id)) {
      setError("Ya existe una categoría con ese nombre.");
      return;
    }

    setError("");
    if (isEdit) {
      renameCategory(category.id, name);
    } else {
      createCategory(name, kind);
    }
    setOpen(false);
  }

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger
        render={
          isEdit ? (
            <Button variant="outline" size="sm">
              Editar
            </Button>
          ) : (
            <Button>
              <Plus />
              Nueva categoría
            </Button>
          )
        }
      />

      <DialogContent>
        <form onSubmit={handleSubmit}>
          <DialogHeader>
            <DialogTitle>
              {isEdit ? "Editar categoría" : "Nueva categoría"}
            </DialogTitle>
            <DialogDescription>
              Las categorías organizan el catálogo y alimentan los filtros de
              búsqueda.
            </DialogDescription>
          </DialogHeader>

          <div className="space-y-4 py-4">
            <div className="space-y-2">
              <Label htmlFor={`name-${category?.id ?? "new"}`}>Nombre</Label>
              <Input
                id={`name-${category?.id ?? "new"}`}
                name="name"
                required
                defaultValue={category?.name}
                placeholder="Tablets"
              />
            </div>

            {isEdit ? null : (
              <fieldset className="space-y-2">
                <legend className="text-sm font-medium">Agrupa</legend>
                <RadioGroup
                  value={kind}
                  onValueChange={(value) => setKind(value as CategoryKind)}
                  className="gap-2"
                >
                  <Label className="flex items-center gap-3 rounded-lg border border-border p-3">
                    <RadioGroupItem value="product" />
                    Equipos
                  </Label>
                  <Label className="flex items-center gap-3 rounded-lg border border-border p-3">
                    <RadioGroupItem value="service" />
                    Servicios
                  </Label>
                </RadioGroup>
              </fieldset>
            )}

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
            <Button type="submit">{isEdit ? "Guardar" : "Crear"}</Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
