"use client";

import { useSyncExternalStore } from "react";

import { categories as mockCategories, suppliers } from "@/data/catalog";
import type { Category, CategoryKind } from "@/data/types";
import { normalizeText, slugify } from "@/lib/utils";

export type SupplierStatus = "verified" | "pending" | "suspended";

export const supplierStatusLabels: Record<SupplierStatus, string> = {
  verified: "Verificado",
  pending: "Por revisar",
  suspended: "Suspendido",
};

export type SupplierReview = {
  status: SupplierStatus;
  note: string;
  date: string;
};

type AdminState = {
  categories: Category[];
  names: Record<string, string>;
  disabled: string[];
  supplierReviews: Record<string, SupplierReview>;
};

const STORAGE_KEY = "tecnolink-admin";
const EMPTY: AdminState = {
  categories: [],
  names: {},
  disabled: [],
  supplierReviews: {},
};

let state: AdminState = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (parsed && typeof parsed === "object" && "disabled" in parsed) {
        state = parsed as AdminState;
      }
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: AdminState) {
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

function getSnapshot(): AdminState {
  return state;
}

function getServerSnapshot(): AdminState {
  return EMPTY;
}

function today(): string {
  const now = new Date();
  const month = String(now.getMonth() + 1).padStart(2, "0");
  const day = String(now.getDate()).padStart(2, "0");
  return `${now.getFullYear()}-${month}-${day}`;
}

function allCategories(current: AdminState): Category[] {
  return [...mockCategories, ...current.categories].map((category) => ({
    ...category,
    name: current.names[category.id] ?? category.name,
  }));
}

export function isDuplicateCategory(name: string, exceptId?: string): boolean {
  const target = normalizeText(name);
  return allCategories(state).some(
    (category) =>
      category.id !== exceptId && normalizeText(category.name) === target
  );
}

export function createCategory(name: string, kind: CategoryKind) {
  const id = slugify(name) || `categoria-${Date.now()}`;
  update({
    ...state,
    categories: [...state.categories, { id, name: name.trim(), kind }],
  });
}

export function renameCategory(id: string, name: string) {
  update({ ...state, names: { ...state.names, [id]: name.trim() } });
}

export function toggleCategory(id: string) {
  update({
    ...state,
    disabled: state.disabled.includes(id)
      ? state.disabled.filter((item) => item !== id)
      : [...state.disabled, id],
  });
}

export function reviewSupplier(
  id: string,
  status: SupplierStatus,
  note: string
) {
  update({
    ...state,
    supplierReviews: {
      ...state.supplierReviews,
      [id]: { status, note: note.trim(), date: today() },
    },
  });
}

export function useAdminCategories() {
  const current = useSyncExternalStore(
    subscribe,
    getSnapshot,
    getServerSnapshot
  );

  return allCategories(current).map((category) => ({
    ...category,
    active: !current.disabled.includes(category.id),
  }));
}

export function useAdminSuppliers() {
  const current = useSyncExternalStore(
    subscribe,
    getSnapshot,
    getServerSnapshot
  );

  return suppliers.map((supplier) => {
    const review = current.supplierReviews[supplier.id];
    return {
      supplier,
      status: review?.status ?? (supplier.verified ? "verified" : "pending"),
      note: review?.note ?? "",
      reviewedOn: review?.date ?? "",
    };
  });
}
