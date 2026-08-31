import Link from "next/link";
import { Link2, Search, ShoppingCart } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export function SiteHeader() {
  return (
    <header className="border-b border-border bg-background">
      <div className="mx-auto flex w-full max-w-6xl flex-wrap items-center gap-x-6 gap-y-3 px-6 py-3">
        <Link
          href="/"
          className="flex items-center gap-2 text-primary transition-colors hover:text-foreground"
        >
          <Link2 className="size-5" />
          <span className="font-heading text-lg font-semibold">TecnoLink</span>
        </Link>

        <form action="/search" className="order-3 w-full sm:order-none sm:flex-1">
          <div className="flex gap-2">
            <Input
              type="search"
              name="q"
              aria-label="Buscar equipos y servicios"
              placeholder="Laptop, impresora, soporte técnico…"
            />
            <Button type="submit" size="icon" aria-label="Buscar">
              <Search />
            </Button>
          </div>
        </form>

        <nav className="ml-auto flex items-center gap-1">
          <ButtonLink href="/compare" variant="ghost" size="sm">
            Comparar
          </ButtonLink>
          <ButtonLink
            href="/cart"
            variant="ghost"
            size="icon-sm"
            aria-label="Carrito"
          >
            <ShoppingCart />
          </ButtonLink>
          <ButtonLink href="/login" variant="ghost" size="sm">
            Ingresar
          </ButtonLink>
          <ButtonLink href="/register" size="sm">
            Crear cuenta
          </ButtonLink>
        </nav>
      </div>
    </header>
  );
}
