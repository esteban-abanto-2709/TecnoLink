"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState, type FormEvent } from "react";

import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { roleHome, signIn, type Role } from "@/lib/session";

const accountTypes: { id: Role; label: string; description: string }[] = [
  {
    id: "client",
    label: "Cliente",
    description: "Quiero buscar, comparar y comprar equipos o servicios.",
  },
  {
    id: "supplier",
    label: "Proveedor",
    description: "Quiero publicar mi oferta y responder cotizaciones.",
  },
];

export default function Page() {
  const router = useRouter();
  const [role, setRole] = useState<Role>("client");
  const [accepted, setAccepted] = useState(false);
  const [error, setError] = useState("");

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const name = String(data.get("name") ?? "");
    const email = String(data.get("email") ?? "");
    const password = String(data.get("password") ?? "");
    const confirmation = String(data.get("confirmation") ?? "");

    if (password !== confirmation) {
      setError("Las contraseñas no coinciden.");
      return;
    }

    if (!accepted) {
      setError("Debes aceptar los términos para crear la cuenta.");
      return;
    }

    setError("");
    signIn({ name, email, role });
    router.push(roleHome[role]);
  }

  return (
    <main className="mx-auto w-full max-w-md flex-1 px-6 py-16">
      <h1 className="text-2xl font-semibold">Crear cuenta</h1>
      <p className="mt-2 text-muted-foreground">
        Guarda tus datos para comprar y solicitar cotizaciones.
      </p>

      <form onSubmit={handleSubmit} className="mt-8 space-y-6">
        <div className="space-y-2">
          <Label htmlFor="name">Nombre completo</Label>
          <Input id="name" name="name" required autoComplete="name" />
        </div>

        <div className="space-y-2">
          <Label htmlFor="email">Correo electrónico</Label>
          <Input
            id="email"
            name="email"
            type="email"
            required
            autoComplete="email"
            placeholder="nombre@correo.com"
          />
        </div>

        <div className="space-y-2">
          <Label htmlFor="password">Contraseña</Label>
          <Input
            id="password"
            name="password"
            type="password"
            required
            minLength={8}
            autoComplete="new-password"
          />
          <p className="text-xs text-muted-foreground">Mínimo 8 caracteres.</p>
        </div>

        <div className="space-y-2">
          <Label htmlFor="confirmation">Repetir contraseña</Label>
          <Input
            id="confirmation"
            name="confirmation"
            type="password"
            required
            minLength={8}
            autoComplete="new-password"
          />
        </div>

        <fieldset className="space-y-2">
          <legend className="text-sm font-medium">Tipo de cuenta</legend>
          <RadioGroup
            value={role}
            onValueChange={(value) => setRole(value as Role)}
            className="gap-2"
          >
            {accountTypes.map((type) => (
              <Label
                key={type.id}
                className="flex items-start gap-3 rounded-lg border border-border p-3"
              >
                <RadioGroupItem value={type.id} className="mt-0.5" />
                <span>
                  {type.label}
                  <span className="block text-sm font-normal text-muted-foreground">
                    {type.description}
                  </span>
                </span>
              </Label>
            ))}
          </RadioGroup>
        </fieldset>

        <Label className="flex items-start gap-3">
          <Checkbox
            checked={accepted}
            onCheckedChange={(value) => setAccepted(value === true)}
            className="mt-0.5"
          />
          <span className="text-sm font-normal">
            Acepto los términos de uso y la política de privacidad de la
            plataforma.
          </span>
        </Label>

        {error ? (
          <p role="alert" className="text-sm text-destructive">
            {error}
          </p>
        ) : null}

        <Button type="submit" className="w-full">
          Crear cuenta
        </Button>
      </form>

      <p className="mt-6 text-sm text-muted-foreground">
        ¿Ya tienes cuenta?{" "}
        <Link
          href="/login"
          className="text-primary underline-offset-4 hover:underline"
        >
          Inicia sesión
        </Link>
        .
      </p>
    </main>
  );
}
