"use client";

import { Check, Scale } from "lucide-react";

import { Button } from "@/components/ui/button";
import { MAX_COMPARE, toggleCompare, useCompare } from "@/lib/compare";

type CompareToggleProps = {
  productId: string;
  label?: string;
};

export function CompareToggle({ productId, label }: CompareToggleProps) {
  const { ids, isFull } = useCompare();
  const selected = ids.includes(productId);
  const disabled = isFull && !selected;

  const title = disabled
    ? `Ya estás comparando ${MAX_COMPARE} equipos`
    : selected
      ? "Quitar del comparador"
      : "Agregar al comparador";

  if (!label) {
    return (
      <Button
        variant={selected ? "default" : "outline"}
        size="icon-sm"
        aria-label={title}
        title={title}
        disabled={disabled}
        onClick={() => toggleCompare(productId)}
      >
        {selected ? <Check /> : <Scale />}
      </Button>
    );
  }

  return (
    <Button
      variant={selected ? "secondary" : "outline"}
      disabled={disabled}
      title={disabled ? title : undefined}
      onClick={() => toggleCompare(productId)}
    >
      {selected ? <Check /> : <Scale />}
      {selected ? "En el comparador" : label}
    </Button>
  );
}
