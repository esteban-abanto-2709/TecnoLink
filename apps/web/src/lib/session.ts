"use client";

import { useSyncExternalStore } from "react";

export type Role = "client" | "supplier" | "admin";

export type Session = {
  name: string;
  email: string;
  role: Role;
};

export const roleLabels: Record<Role, string> = {
  client: "Cliente",
  supplier: "Proveedor",
  admin: "Administrador",
};

export const roleHome: Record<Role, string> = {
  client: "/",
  supplier: "/supplier",
  admin: "/admin",
};

export const DEMO_SUPPLIER_ID = "techperu";

const STORAGE_KEY = "tecnolink-session";

let session: Session | null = null;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (parsed && typeof parsed === "object" && "role" in parsed) {
        session = parsed as Session;
      }
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: Session | null) {
  session = next;
  if (next) {
    window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  } else {
    window.localStorage.removeItem(STORAGE_KEY);
  }
  for (const listener of listeners) listener();
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

function getSnapshot(): Session | null {
  return session;
}

function getServerSnapshot(): Session | null {
  return null;
}

export function useSession(): Session | null {
  return useSyncExternalStore(subscribe, getSnapshot, getServerSnapshot);
}

export function signIn(next: Session) {
  update(next);
}

export function signOut() {
  update(null);
}

export function switchRole(role: Role) {
  if (session) update({ ...session, role });
}

export function nameFromEmail(email: string): string {
  const handle = email.split("@")[0] ?? "";
  const clean = handle.replace(/[._-]+/g, " ").trim();
  if (clean === "") return "Invitado";
  return clean
    .split(" ")
    .map((part) => part.charAt(0).toUpperCase() + part.slice(1))
    .join(" ");
}
