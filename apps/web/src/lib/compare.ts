"use client";

import { useSyncExternalStore } from "react";

export const MAX_COMPARE = 4;

const STORAGE_KEY = "tecnolink-compare";
const EMPTY: string[] = [];

let ids: string[] = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (Array.isArray(parsed)) ids = parsed as string[];
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: string[]) {
  ids = next;
  window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  for (const listener of listeners) listener();
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

function getSnapshot(): string[] {
  return ids;
}

function getServerSnapshot(): string[] {
  return EMPTY;
}

export function useCompare() {
  const selected = useSyncExternalStore(
    subscribe,
    getSnapshot,
    getServerSnapshot
  );
  return {
    ids: selected,
    count: selected.length,
    isFull: selected.length >= MAX_COMPARE,
  };
}

export function toggleCompare(id: string) {
  if (ids.includes(id)) {
    update(ids.filter((item) => item !== id));
    return;
  }
  if (ids.length >= MAX_COMPARE) return;
  update([...ids, id]);
}

export function removeFromCompare(id: string) {
  update(ids.filter((item) => item !== id));
}

export function clearCompare() {
  update([]);
}
