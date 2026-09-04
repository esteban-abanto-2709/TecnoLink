"use client";

import { useSyncExternalStore } from "react";

import { suppliers as mockSuppliers } from "@/data/catalog";
import type { Supplier } from "@/data/types";
import { slugify } from "@/lib/utils";

const STORAGE_KEY = "tecnolink-suppliers";
const EMPTY: Supplier[] = [];

let registered: Supplier[] = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (Array.isArray(parsed)) registered = parsed as Supplier[];
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: Supplier[]) {
  registered = next;
  window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  for (const listener of listeners) listener();
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

function getSnapshot(): Supplier[] {
  return registered;
}

function getServerSnapshot(): Supplier[] {
  return EMPTY;
}

function uniqueId(name: string): string {
  const base = slugify(name) || "proveedor";
  const taken = [
    ...mockSuppliers.map((supplier) => supplier.id),
    ...registered.map((supplier) => supplier.id),
  ];
  if (!taken.includes(base)) return base;
  let suffix = 2;
  while (taken.includes(`${base}-${suffix}`)) suffix += 1;
  return `${base}-${suffix}`;
}

export function registerSupplier(input: {
  name: string;
  ruc: string;
  phone: string;
  district: string;
  description: string;
}): Supplier {
  const supplier: Supplier = {
    ...input,
    id: uniqueId(input.name),
    since: new Date().getFullYear(),
    verified: false,
  };

  update([supplier, ...registered]);
  return supplier;
}

export function useRegisteredSuppliers(): Supplier[] {
  return useSyncExternalStore(subscribe, getSnapshot, getServerSnapshot);
}

export function useAllSuppliers(): Supplier[] {
  return [...useRegisteredSuppliers(), ...mockSuppliers];
}

export function useSupplierById(id: string): Supplier | undefined {
  return useAllSuppliers().find((supplier) => supplier.id === id);
}

export function isMockSupplier(id: string): boolean {
  return mockSuppliers.some((supplier) => supplier.id === id);
}
