"use client";

import { useSyncExternalStore } from "react";

import { productsBySupplier, servicesBySupplier } from "@/data/catalog";
import type { Product, Service, ServicePricing } from "@/data/types";
import { slugify } from "@/lib/utils";

export type ListingEdit = {
  name: string;
  price: number;
  description: string;
  brand?: string;
  coverage?: string;
  pricing?: ServicePricing;
};

type ListingState = {
  products: Product[];
  services: Service[];
  edits: Record<string, ListingEdit>;
};

const STORAGE_KEY = "tecnolink-listings";
const EMPTY: ListingState = { products: [], services: [], edits: {} };

let state: ListingState = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (parsed && typeof parsed === "object" && "edits" in parsed) {
        state = parsed as ListingState;
      }
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: ListingState) {
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

function getSnapshot(): ListingState {
  return state;
}

function getServerSnapshot(): ListingState {
  return EMPTY;
}

function uniqueId(name: string, taken: string[]): string {
  const base = slugify(name) || "publicacion";
  if (!taken.includes(base)) return base;
  let suffix = 2;
  while (taken.includes(`${base}-${suffix}`)) suffix += 1;
  return `${base}-${suffix}`;
}

export function createProduct(input: {
  name: string;
  brand: string;
  categoryId: string;
  supplierId: string;
  price: number;
  description: string;
}): Product {
  const taken = [
    ...state.products.map((product) => product.id),
    ...state.services.map((service) => service.id),
  ];
  const product: Product = { ...input, id: uniqueId(input.name, taken), specs: {} };
  update({ ...state, products: [product, ...state.products] });
  return product;
}

export function createService(input: {
  name: string;
  categoryId: string;
  supplierId: string;
  price: number;
  pricing: ServicePricing;
  description: string;
  coverage: string;
}): Service {
  const taken = [
    ...state.products.map((product) => product.id),
    ...state.services.map((service) => service.id),
  ];
  const service: Service = { ...input, id: uniqueId(input.name, taken) };
  update({ ...state, services: [service, ...state.services] });
  return service;
}

export function editListing(id: string, edit: ListingEdit) {
  update({ ...state, edits: { ...state.edits, [id]: edit } });
}

function applyEdit<T extends Product | Service>(
  item: T,
  edits: ListingState["edits"]
): T {
  const edit = edits[item.id];
  if (!edit) return item;
  return {
    ...item,
    name: edit.name,
    price: edit.price,
    description: edit.description,
    ...("brand" in item && edit.brand ? { brand: edit.brand } : {}),
    ...("coverage" in item && edit.coverage ? { coverage: edit.coverage } : {}),
    ...("pricing" in item && edit.pricing ? { pricing: edit.pricing } : {}),
  };
}

export function useSupplierListings(supplierId: string) {
  const current = useSyncExternalStore(
    subscribe,
    getSnapshot,
    getServerSnapshot
  );

  const products = [
    ...current.products.filter((product) => product.supplierId === supplierId),
    ...productsBySupplier(supplierId),
  ].map((product) => applyEdit(product, current.edits));

  const services = [
    ...current.services.filter((service) => service.supplierId === supplierId),
    ...servicesBySupplier(supplierId),
  ].map((service) => applyEdit(service, current.edits));

  return { products, services };
}

export function useListing(supplierId: string, id: string) {
  const { products, services } = useSupplierListings(supplierId);
  const product = products.find((item) => item.id === id);
  if (product) return { kind: "product" as const, product };

  const service = services.find((item) => item.id === id);
  if (service) return { kind: "service" as const, service };

  return null;
}
