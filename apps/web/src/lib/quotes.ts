"use client";

import { useSyncExternalStore } from "react";

import { quotes as mockQuotes } from "@/data/catalog";
import type { Quote, QuoteStatus } from "@/data/types";

export type QuoteAnswer = NonNullable<Quote["answer"]>;

export const quoteStatusLabels: Record<QuoteStatus, string> = {
  sent: "Enviada",
  answered: "Respondida",
  expired: "Vencida",
};

type QuoteState = {
  own: Quote[];
  answers: Record<string, QuoteAnswer>;
};

const STORAGE_KEY = "tecnolink-quotes";
const EMPTY: QuoteState = { own: [], answers: {} };

let state: QuoteState = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (parsed && typeof parsed === "object" && "own" in parsed) {
        state = parsed as QuoteState;
      }
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: QuoteState) {
  state = next;
  window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  for (const listener of listeners) listener();
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

function getSnapshot(): QuoteState {
  return state;
}

function getServerSnapshot(): QuoteState {
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

  update({ ...state, own: [quote, ...state.own] });
  return quote;
}

export function answerQuote(id: string, answer: QuoteAnswer) {
  update({ ...state, answers: { ...state.answers, [id]: answer } });
}

function applyAnswers(quotes: Quote[], answers: QuoteState["answers"]): Quote[] {
  return quotes.map((quote) => {
    const answer = answers[quote.id];
    if (!answer) return quote;
    return { ...quote, status: "answered" as const, answer };
  });
}

export function useQuotes(): Quote[] {
  const current = useSyncExternalStore(
    subscribe,
    getSnapshot,
    getServerSnapshot
  );
  return applyAnswers([...current.own, ...mockQuotes], current.answers);
}
