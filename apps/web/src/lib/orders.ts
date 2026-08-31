"use client";

import { useSyncExternalStore } from "react";

import { orders as mockOrders } from "@/data/catalog";
import type { Order } from "@/data/types";
import { pointsFor, type CartLine } from "@/lib/cart";

const STORAGE_KEY = "tecnolink-orders";
const EMPTY: Order[] = [];

let placed: Order[] = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (Array.isArray(parsed)) placed = parsed as Order[];
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: Order[]) {
  placed = next;
  window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  for (const listener of listeners) listener();
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

function getSnapshot(): Order[] {
  return placed;
}

function getServerSnapshot(): Order[] {
  return EMPTY;
}

function today(): string {
  const now = new Date();
  const month = String(now.getMonth() + 1).padStart(2, "0");
  const day = String(now.getDate()).padStart(2, "0");
  return `${now.getFullYear()}-${month}-${day}`;
}

function orderCode(): string {
  const random = Math.floor(Math.random() * 10000)
    .toString()
    .padStart(4, "0");
  return `TL-${new Date().getFullYear()}-${random}`;
}

export function placeOrder(lines: CartLine[], total: number): Order {
  const order: Order = {
    id: orderCode(),
    date: today(),
    lines: lines.map((line) => ({
      itemId: line.id,
      name: line.name,
      quantity: line.quantity,
      price: line.price,
    })),
    total,
    pointsEarned: pointsFor(total),
  };

  update([order, ...placed]);
  return order;
}

export function useOwnOrders(): Order[] {
  return useSyncExternalStore(subscribe, getSnapshot, getServerSnapshot);
}

export function useOrders(): Order[] {
  return [...useOwnOrders(), ...mockOrders];
}
