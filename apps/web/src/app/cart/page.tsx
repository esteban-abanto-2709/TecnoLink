"use client";

import Link from "next/link";
import { Minus, Plus, ShoppingCart, Trash2 } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { CategoryIcon } from "@/components/category-icon";
import { Button } from "@/components/ui/button";
import { getCategory, getProduct, getService } from "@/data/catalog";
import { pointsFor, removeFromCart, setQuantity, useCart } from "@/lib/cart";
import { formatPrice } from "@/lib/format";

export default function Page() {
  const { lines, total } = useCart();

  if (lines.length === 0) {
    return (
      <main className="mx-auto flex w-full max-w-xl flex-1 flex-col items-center justify-center gap-3 px-6 py-24 text-center">
        <ShoppingCart className="size-6 text-muted-foreground" aria-hidden />
        <h1 className="text-2xl font-semibold">Tu carrito está vacío</h1>
        <p className="text-muted-foreground">
          Agrega equipos o servicios desde el catálogo y vuelve para completar la
          compra.
        </p>
        <ButtonLink href="/search">Ver el catálogo</ButtonLink>
      </main>
    );
  }

  return (
    <main className="mx-auto w-full max-w-5xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Carrito</h1>

      <div className="mt-8 grid gap-8 lg:grid-cols-[1fr_300px]">
        <ul className="divide-y divide-border border-y border-border">
          {lines.map((line) => {
            const source =
              line.kind === "product" ? getProduct(line.id) : getService(line.id);
            const category = source
              ? getCategory(source.categoryId)
              : undefined;
            const href =
              line.kind === "product"
                ? `/products/${line.id}`
                : `/services/${line.id}`;

            return (
              <li key={line.id} className="flex gap-4 py-4">
                <span className="grid size-16 shrink-0 place-items-center rounded-lg bg-muted">
                  <CategoryIcon
                    categoryId={source?.categoryId ?? ""}
                    className="size-6 text-muted-foreground"
                  />
                </span>

                <div className="min-w-0 flex-1 space-y-1">
                  <Link href={href} className="font-medium hover:text-primary">
                    {line.name}
                  </Link>
                  {category ? (
                    <p className="text-sm text-muted-foreground">
                      {category.name}
                    </p>
                  ) : null}
                  <p className="text-sm text-muted-foreground">
                    {formatPrice(line.price)} c/u
                  </p>
                </div>

                <div className="flex flex-col items-end gap-2">
                  <span className="font-medium">
                    {formatPrice(line.price * line.quantity)}
                  </span>

                  <div className="flex items-center gap-1">
                    <Button
                      variant="outline"
                      size="icon-xs"
                      aria-label="Quitar una unidad"
                      onClick={() => setQuantity(line.id, line.quantity - 1)}
                    >
                      <Minus />
                    </Button>
                    <span className="w-8 text-center text-sm">
                      {line.quantity}
                    </span>
                    <Button
                      variant="outline"
                      size="icon-xs"
                      aria-label="Agregar una unidad"
                      onClick={() => setQuantity(line.id, line.quantity + 1)}
                    >
                      <Plus />
                    </Button>
                    <Button
                      variant="ghost"
                      size="icon-xs"
                      aria-label={`Quitar ${line.name}`}
                      onClick={() => removeFromCart(line.id)}
                    >
                      <Trash2 />
                    </Button>
                  </div>
                </div>
              </li>
            );
          })}
        </ul>

        <aside className="space-y-3 self-start rounded-xl border border-border bg-card p-4">
          <h2 className="font-semibold">Resumen</h2>

          <div className="flex justify-between text-sm">
            <span className="text-muted-foreground">Subtotal</span>
            <span>{formatPrice(total)}</span>
          </div>
          <div className="flex justify-between text-sm">
            <span className="text-muted-foreground">Envío</span>
            <span>Coordinado con el proveedor</span>
          </div>

          <div className="flex justify-between border-t border-border pt-3 font-medium">
            <span>Total</span>
            <span className="font-heading text-lg">{formatPrice(total)}</span>
          </div>

          <p className="text-sm text-muted-foreground">
            Ganarás {pointsFor(total)} puntos de fidelización con esta compra.
          </p>

          <ButtonLink href="/checkout" className="w-full">
            Continuar la compra
          </ButtonLink>
        </aside>
      </div>
    </main>
  );
}
