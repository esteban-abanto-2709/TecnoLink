"use client";

import { useState, type FormEvent } from "react";
import { CircleCheckBig, Info } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { clearCart, pointsFor, useCart, type CartLine } from "@/lib/cart";
import { formatPrice } from "@/lib/format";

type Confirmation = {
  code: string;
  lines: CartLine[];
  total: number;
  points: number;
};

const paymentMethods = [
  { id: "transfer", label: "Transferencia bancaria" },
  { id: "cash", label: "Pago contra entrega" },
  { id: "card", label: "Tarjeta de crédito o débito" },
];

function orderCode(): string {
  const random = Math.floor(Math.random() * 10000)
    .toString()
    .padStart(4, "0");
  return `TL-${new Date().getFullYear()}-${random}`;
}

export default function Page() {
  const { lines, total } = useCart();
  const [confirmation, setConfirmation] = useState<Confirmation | null>(null);

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setConfirmation({
      code: orderCode(),
      lines,
      total,
      points: pointsFor(total),
    });
    clearCart();
  }

  if (confirmation) {
    return (
      <main className="mx-auto w-full max-w-xl flex-1 px-6 py-16">
        <div className="flex flex-col items-center gap-3 text-center">
          <CircleCheckBig className="size-8 text-primary" aria-hidden />
          <h1 className="text-2xl font-semibold">Compra registrada</h1>
          <p className="text-muted-foreground">
            Tu pedido {confirmation.code} quedó registrado. El proveedor se
            comunicará para coordinar la entrega.
          </p>
        </div>

        <div className="mt-8 space-y-3 rounded-xl border border-border bg-card p-4">
          <h2 className="font-semibold">Resumen del pedido</h2>
          <ul className="divide-y divide-border border-y border-border">
            {confirmation.lines.map((line) => (
              <li key={line.id} className="flex justify-between gap-4 py-3">
                <span className="min-w-0">
                  {line.name}
                  <span className="text-muted-foreground"> × {line.quantity}</span>
                </span>
                <span className="shrink-0">
                  {formatPrice(line.price * line.quantity)}
                </span>
              </li>
            ))}
          </ul>
          <div className="flex justify-between font-medium">
            <span>Total</span>
            <span className="font-heading text-lg">
              {formatPrice(confirmation.total)}
            </span>
          </div>
          <p className="text-sm text-muted-foreground">
            Sumaste {confirmation.points} puntos de fidelización.
          </p>
        </div>

        <div className="mt-6 flex flex-wrap gap-2">
          <ButtonLink href="/orders">Ver mis compras</ButtonLink>
          <ButtonLink href="/search" variant="outline">
            Seguir explorando
          </ButtonLink>
        </div>
      </main>
    );
  }

  if (lines.length === 0) {
    return (
      <main className="mx-auto flex w-full max-w-xl flex-1 flex-col items-center justify-center gap-3 px-6 py-24 text-center">
        <h1 className="text-2xl font-semibold">No hay nada que pagar</h1>
        <p className="text-muted-foreground">
          Tu carrito está vacío, así que no hay un pedido para completar.
        </p>
        <ButtonLink href="/search">Ver el catálogo</ButtonLink>
      </main>
    );
  }

  return (
    <main className="mx-auto w-full max-w-5xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Completar la compra</h1>

      <div className="mt-4 flex items-start gap-2 rounded-lg border border-border bg-secondary/40 p-3 text-sm">
        <Info className="mt-0.5 size-4 shrink-0 text-muted-foreground" aria-hidden />
        <p>
          Esta compra es simulada: no se procesa ningún pago ni se comparten tus
          datos. La plataforma no tiene pasarela de pago.
        </p>
      </div>

      <form onSubmit={handleSubmit} className="mt-8 grid gap-8 lg:grid-cols-[1fr_300px]">
        <div className="space-y-8">
          <section className="space-y-4">
            <h2 className="text-lg font-semibold">Datos de contacto</h2>
            <div className="grid gap-4 sm:grid-cols-2">
              <div className="space-y-2">
                <Label htmlFor="name">Nombre completo</Label>
                <Input id="name" name="name" required autoComplete="name" />
              </div>
              <div className="space-y-2">
                <Label htmlFor="email">Correo electrónico</Label>
                <Input
                  id="email"
                  name="email"
                  type="email"
                  required
                  autoComplete="email"
                />
              </div>
              <div className="space-y-2">
                <Label htmlFor="phone">Teléfono</Label>
                <Input
                  id="phone"
                  name="phone"
                  type="tel"
                  required
                  autoComplete="tel"
                />
              </div>
              <div className="space-y-2">
                <Label htmlFor="district">Distrito</Label>
                <Input id="district" name="district" required />
              </div>
              <div className="space-y-2 sm:col-span-2">
                <Label htmlFor="address">Dirección</Label>
                <Input
                  id="address"
                  name="address"
                  required
                  autoComplete="street-address"
                />
              </div>
            </div>
          </section>

          <section className="space-y-4">
            <h2 className="text-lg font-semibold">Forma de pago</h2>
            <RadioGroup defaultValue="transfer" name="payment" className="gap-2">
              {paymentMethods.map((method) => (
                <Label
                  key={method.id}
                  className="flex items-center gap-3 rounded-lg border border-border p-3"
                >
                  <RadioGroupItem value={method.id} />
                  {method.label}
                </Label>
              ))}
            </RadioGroup>
          </section>
        </div>

        <aside className="space-y-3 self-start rounded-xl border border-border bg-card p-4">
          <h2 className="font-semibold">Tu pedido</h2>
          <ul className="divide-y divide-border border-y border-border">
            {lines.map((line) => (
              <li key={line.id} className="flex justify-between gap-3 py-3 text-sm">
                <span className="min-w-0">
                  {line.name}
                  <span className="text-muted-foreground"> × {line.quantity}</span>
                </span>
                <span className="shrink-0">
                  {formatPrice(line.price * line.quantity)}
                </span>
              </li>
            ))}
          </ul>

          <div className="flex justify-between font-medium">
            <span>Total</span>
            <span className="font-heading text-lg">{formatPrice(total)}</span>
          </div>

          <p className="text-sm text-muted-foreground">
            Ganarás {pointsFor(total)} puntos de fidelización.
          </p>

          <Button type="submit" className="w-full">
            Confirmar compra
          </Button>
        </aside>
      </form>
    </main>
  );
}
