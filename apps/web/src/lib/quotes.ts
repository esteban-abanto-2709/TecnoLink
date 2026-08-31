"use client";

import { useSyncExternalStore } from "react";

import { quotes as mockQuotes } from "@/data/catalog";
import type { Quote, QuoteStatus } from "@/data/types";

export const quoteStatusLabels: Record<QuoteStatus, string> = {
  sent: "Enviada",
  answered: "Respondida",
  expired: "Vencida",
};

const STORAGE_KEY = "tecnolink-quotes";
const EMPTY: Quote[] = [];

let own: Quote[] = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (Array.isArray(parsed)) own = parsed as Quote[];
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: Quote[]) {
  own = next;
  window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  for (const listener of listeners) listener();
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

function getSnapshot(): Quote[] {
  return own;
}

function getServerSnapshot(): Quote[] {
  return EMPTY;
}

function today(): string {
  const now = new Date();
  const month = String(now.getMonth() + 1).padStart(2, "0");
  const day = String(now.getDate()).padStart(2, "0");
  return `${now.getFullYear()}-${month}-${day}`;
}

function quoteCode(): string {
  const random = Math.floor(Math.random() * 1000)
    .toString()
    .padStart(3, "0");
  return `cot-${random}`;
}

export function requestQuote(input: {
  itemId: string;
  supplierId: string;
  quantity: number;
  requirement: string;
}): Quote {
  const quote: Quote = {
    id: quoteCode(),
    itemId: input.itemId,
    supplierId: input.supplierId,
    quantity: input.quantity,
    requirement: input.requirement,
    status: "sent",
    requestedOn: today(),
  };

  update([quote, ...own]);
  return quote;
}

export function useQuotes(): Quote[] {
  const mine = useSyncExternalStore(subscribe, getSnapshot, getServerSnapshot);
  return [...mine, ...mockQuotes];
}
