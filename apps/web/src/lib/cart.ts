"use client";

import { useSyncExternalStore } from "react";

import type { CategoryKind } from "@/data/types";

export type CartLine = {
  id: string;
  kind: CategoryKind;
  name: string;
  price: number;
  quantity: number;
};

const STORAGE_KEY = "tecnolink-cart";
const EMPTY: CartLine[] = [];

let cart: CartLine[] = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (Array.isArray(parsed)) cart = parsed as CartLine[];
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: CartLine[]) {
  cart = next;
  window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  for (const listener of listeners) listener();
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

function getSnapshot(): CartLine[] {
  return cart;
}

function getServerSnapshot(): CartLine[] {
  return EMPTY;
}

export function useCart() {
  const lines = useSyncExternalStore(subscribe, getSnapshot, getServerSnapshot);
  return {
    lines,
    count: lines.reduce((sum, line) => sum + line.quantity, 0),
    total: lines.reduce((sum, line) => sum + line.price * line.quantity, 0),
  };
}

export function addToCart(line: Omit<CartLine, "quantity">) {
  const existing = cart.find((item) => item.id === line.id);
  update(
    existing
      ? cart.map((item) =>
          item.id === line.id ? { ...item, quantity: item.quantity + 1 } : item
        )
      : [...cart, { ...line, quantity: 1 }]
  );
}

export function setQuantity(id: string, quantity: number) {
  update(
    quantity < 1
      ? cart.filter((item) => item.id !== id)
      : cart.map((item) => (item.id === id ? { ...item, quantity } : item))
  );
}

export function removeFromCart(id: string) {
  update(cart.filter((item) => item.id !== id));
}

export function clearCart() {
  update([]);
}

export function pointsFor(total: number): number {
  return Math.floor(total / 10);
}
