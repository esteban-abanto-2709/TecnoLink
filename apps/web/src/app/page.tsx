import { Search, Star, Link2 } from "lucide-react";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  Dialog,
  DialogClose,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

const tokens = [
  { name: "primary", className: "bg-primary" },
  { name: "accent", className: "bg-accent" },
  { name: "secondary", className: "bg-secondary" },
  { name: "muted", className: "bg-muted" },
  { name: "destructive", className: "bg-destructive" },
  { name: "border", className: "bg-border" },
];

export default function Home() {
  return (
    <main className="mx-auto w-full max-w-3xl px-6 py-16 space-y-14">
      <header className="space-y-3">
        <div className="flex items-center gap-2 text-primary">
          <Link2 className="size-5" />
          <span className="font-heading text-lg font-semibold">TecnoLink</span>
        </div>
        <h1 className="text-3xl font-semibold">Sistema visual</h1>
        <p className="text-muted-foreground">
          Página de referencia del tema: colores, tipografía y componentes base.
          La reemplaza la portada real cuando se construya el catálogo.
        </p>
      </header>

      <section className="space-y-4">
        <h2 className="text-sm font-semibold uppercase tracking-wide text-muted-foreground">
          Color
        </h2>
        <div className="grid grid-cols-2 gap-3 sm:grid-cols-3">
          {tokens.map((token) => (
            <div key={token.name} className="space-y-2">
              <div
                className={`h-14 rounded-lg border border-border ${token.className}`}
              />
              <p className="text-xs text-muted-foreground">{token.name}</p>
            </div>
          ))}
        </div>
      </section>

      <section className="space-y-4">
        <h2 className="text-sm font-semibold uppercase tracking-wide text-muted-foreground">
          Tipografía
        </h2>
        <div className="space-y-2">
          <h3 className="text-2xl font-semibold">
            Plus Jakarta Sans para títulos
          </h3>
          <p className="max-w-prose">
            Inter para el cuerpo: especificaciones, precios y tablas de
            comparación se leen sin esfuerzo en pantallas pequeñas.
          </p>
        </div>
      </section>

      <section className="space-y-4">
        <h2 className="text-sm font-semibold uppercase tracking-wide text-muted-foreground">
          Componentes
        </h2>

        <div className="flex flex-wrap gap-2">
          <Button>Comprar ahora</Button>
          <Button variant="secondary">Solicitar cotización</Button>
          <Button variant="outline">Comparar</Button>
          <Button variant="ghost">Ver más</Button>
          <Button variant="destructive">Quitar</Button>
        </div>

        <Card>
          <CardHeader>
            <CardTitle>Laptop Lenovo IdeaPad 3</CardTitle>
            <CardDescription>
              Importaciones TechPeru · San Miguel
            </CardDescription>
          </CardHeader>
          <CardContent className="space-y-4">
            <div className="flex items-center gap-2 text-sm">
              <Star className="size-4 fill-accent text-accent" />
              <span className="font-medium">4.6</span>
              <span className="text-muted-foreground">· 128 reseñas</span>
            </div>
            <div className="space-y-2">
              <Label htmlFor="buscar">Buscar en el catálogo</Label>
              <div className="flex gap-2">
                <Input
                  id="buscar"
                  placeholder="Laptop, impresora, soporte técnico…"
                />
                <Button size="icon" aria-label="Buscar">
                  <Search />
                </Button>
              </div>
            </div>
          </CardContent>
          <CardFooter>
            <Dialog>
              <DialogTrigger
                render={<Button variant="outline">Abrir diálogo</Button>}
              />
              <DialogContent>
                <DialogHeader>
                  <DialogTitle>Solicitud enviada</DialogTitle>
                  <DialogDescription>
                    El proveedor responderá con precio, vigencia y condiciones.
                    Este prototipo no envía nada todavía.
                  </DialogDescription>
                </DialogHeader>
                <DialogFooter>
                  <DialogClose render={<Button>Entendido</Button>} />
                </DialogFooter>
              </DialogContent>
            </Dialog>
          </CardFooter>
        </Card>
      </section>
    </main>
  );
}
