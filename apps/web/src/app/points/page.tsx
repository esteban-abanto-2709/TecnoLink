"use client";

import { Check, Gift } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { benefits } from "@/data/catalog";
import { formatDate } from "@/lib/format";
import { redeemBenefit, usePoints } from "@/lib/points";

export default function Page() {
  const { movements, balance, usedBenefits } = usePoints();

  const available = benefits.filter(
    (benefit) => !usedBenefits.has(benefit.id)
  );
  const used = benefits.filter((benefit) => usedBenefits.has(benefit.id));

  return (
    <main className="mx-auto w-full max-w-4xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Mis puntos</h1>

      <div className="mt-6 rounded-xl border border-border bg-card p-6">
        <p className="text-sm text-muted-foreground">Saldo disponible</p>
        <p className="font-heading text-4xl font-semibold">{balance}</p>
        <p className="mt-2 text-sm text-muted-foreground">
          Ganas 1 punto por cada S/ 10 de compra. Los puntos no vencen mientras
          tu cuenta esté activa.
        </p>
      </div>

      <section className="mt-10 space-y-5">
        <h2 className="text-xl font-semibold">Beneficios disponibles</h2>

        <ul className="grid gap-4 sm:grid-cols-2">
          {available.map((benefit) => {
            const affordable = balance >= benefit.cost;

            return (
              <li
                key={benefit.id}
                className="flex flex-col gap-3 rounded-xl border border-border bg-card p-4"
              >
                <Gift className="size-5 text-muted-foreground" aria-hidden />
                <div className="space-y-1">
                  <h3 className="font-medium">{benefit.name}</h3>
                  <p className="text-sm text-muted-foreground">
                    {benefit.description}
                  </p>
                </div>

                <div className="mt-auto flex items-center justify-between gap-3 pt-2">
                  <span className="text-sm font-medium">
                    {benefit.cost} puntos
                  </span>
                  <Button
                    size="sm"
                    disabled={!affordable}
                    title={
                      affordable
                        ? undefined
                        : `Te faltan ${benefit.cost - balance} puntos`
                    }
                    onClick={() => redeemBenefit(benefit.id)}
                  >
                    Canjear
                  </Button>
                </div>
              </li>
            );
          })}
        </ul>
      </section>

      {used.length > 0 ? (
        <section className="mt-10 space-y-5">
          <h2 className="text-xl font-semibold">Beneficios ya usados</h2>
          <ul className="grid gap-4 sm:grid-cols-2">
            {used.map((benefit) => (
              <li
                key={benefit.id}
                className="rounded-xl border border-dashed border-border p-4"
              >
                <div className="flex items-start justify-between gap-3">
                  <div className="space-y-1">
                    <h3 className="font-medium text-muted-foreground">
                      {benefit.name}
                    </h3>
                    <p className="text-sm text-muted-foreground">
                      {benefit.cost} puntos
                    </p>
                  </div>
                  <Badge variant="outline">
                    <Check />
                    Usado
                  </Badge>
                </div>
              </li>
            ))}
          </ul>
        </section>
      ) : null}

      <section className="mt-10 space-y-5">
        <h2 className="text-xl font-semibold">Movimientos</h2>

        <ul className="divide-y divide-border border-y border-border">
          {movements.map((movement) => (
            <li
              key={movement.id}
              className="flex items-center justify-between gap-4 py-3"
            >
              <div className="min-w-0">
                <p className="text-sm">{movement.description}</p>
                <p className="text-sm text-muted-foreground">
                  {formatDate(movement.date)}
                </p>
              </div>
              <span
                className={
                  movement.points < 0
                    ? "font-medium text-muted-foreground"
                    : "font-medium text-primary"
                }
              >
                {movement.points > 0 ? "+" : ""}
                {movement.points}
              </span>
            </li>
          ))}
        </ul>
      </section>

      <div className="mt-8">
        <ButtonLink href="/search" variant="outline">
          Seguir sumando puntos
        </ButtonLink>
      </div>
    </main>
  );
}
