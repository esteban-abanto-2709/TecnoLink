"use client";

import { useSyncExternalStore } from "react";

import { benefits, pointsMovements as mockMovements } from "@/data/catalog";
import type { PointsMovement } from "@/data/types";
import { useOwnOrders } from "@/lib/orders";

const STORAGE_KEY = "tecnolink-redemptions";
const EMPTY: PointsMovement[] = [];

let redemptions: PointsMovement[] = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (Array.isArray(parsed)) redemptions = parsed as PointsMovement[];
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: PointsMovement[]) {
  redemptions = next;
  window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  for (const listener of listeners) listener();
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

function getSnapshot(): PointsMovement[] {
  return redemptions;
}

function getServerSnapshot(): PointsMovement[] {
  return EMPTY;
}

function today(): string {
  const now = new Date();
  const month = String(now.getMonth() + 1).padStart(2, "0");
  const day = String(now.getDate()).padStart(2, "0");
  return `${now.getFullYear()}-${month}-${day}`;
}

export function redeemBenefit(benefitId: string) {
  const benefit = benefits.find((item) => item.id === benefitId);
  if (!benefit) return;

  update([
    {
      id: `pts-${Date.now()}`,
      date: today(),
      description: `Canje: ${benefit.name}`,
      points: -benefit.cost,
      benefitId: benefit.id,
    },
    ...redemptions,
  ]);
}

export function usePoints() {
  const own = useSyncExternalStore(subscribe, getSnapshot, getServerSnapshot);
  const orders = useOwnOrders();

  const fromOrders: PointsMovement[] = orders.map((order) => ({
    id: `pts-${order.id}`,
    date: order.date,
    description: `Compra ${order.id}`,
    points: order.pointsEarned,
  }));

  const movements = [...own, ...fromOrders, ...mockMovements].sort((a, b) =>
    b.date.localeCompare(a.date)
  );

  const balance = movements.reduce(
    (sum, movement) => sum + movement.points,
    0
  );
  const usedBenefits = new Set(
    movements
      .map((movement) => movement.benefitId)
      .filter((id): id is string => id !== undefined)
  );

  return { movements, balance, usedBenefits };
}
