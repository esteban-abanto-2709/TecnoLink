"use client";

import Link from "next/link";
import { Package } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { ReviewDialog } from "@/components/review-dialog";
import { getProduct } from "@/data/catalog";
import { formatDate, formatPrice } from "@/lib/format";
import { useOrders } from "@/lib/orders";

export default function Page() {
  const orders = useOrders();

  if (orders.length === 0) {
    return (
      <main className="mx-auto flex w-full max-w-xl flex-1 flex-col items-center justify-center gap-3 px-6 py-24 text-center">
        <Package className="size-6 text-muted-foreground" aria-hidden />
        <h1 className="text-2xl font-semibold">Todavía no tienes compras</h1>
        <p className="text-muted-foreground">
          Cuando completes una compra la vas a encontrar acá, con la opción de
          reseñar lo que recibiste.
        </p>
        <ButtonLink href="/search">Ver el catálogo</ButtonLink>
      </main>
    );
  }

  return (
    <main className="mx-auto w-full max-w-4xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Mis compras</h1>
      <p className="mt-2 text-muted-foreground">
        Solo puedes reseñar equipos y servicios que aparezcan en esta lista.
      </p>

      <div className="mt-8 space-y-6">
        {orders.map((order) => (
          <article
            key={order.id}
            className="rounded-xl border border-border bg-card"
          >
            <header className="flex flex-wrap items-baseline justify-between gap-3 border-b border-border p-4">
              <div>
                <h2 className="font-medium">Pedido {order.id}</h2>
                <p className="text-sm text-muted-foreground">
                  {formatDate(order.date)} · {order.pointsEarned} puntos ganados
                </p>
              </div>
              <span className="font-heading text-lg font-semibold">
                {formatPrice(order.total)}
              </span>
            </header>

            <ul className="divide-y divide-border">
              {order.lines.map((line) => {
                const isProduct = getProduct(line.itemId) !== undefined;
                const href = isProduct
                  ? `/products/${line.itemId}`
                  : `/services/${line.itemId}`;

                return (
                  <li
                    key={line.itemId}
                    className="flex flex-wrap items-center justify-between gap-3 p-4"
                  >
                    <div className="min-w-0">
                      <Link href={href} className="font-medium hover:text-primary">
                        {line.name}
                      </Link>
                      <p className="text-sm text-muted-foreground">
                        {line.quantity} × {formatPrice(line.price)}
                      </p>
                    </div>

                    <ReviewDialog
                      targetId={line.itemId}
                      targetName={line.name}
                    />
                  </li>
                );
              })}
            </ul>
          </article>
        ))}
      </div>
    </main>
  );
}
