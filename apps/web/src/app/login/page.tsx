"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState, type FormEvent } from "react";
import { Info } from "lucide-react";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import {
  nameFromEmail,
  roleHome,
  roleLabels,
  signIn,
  type Role,
} from "@/lib/session";

export default function Page() {
  const router = useRouter();
  const [role, setRole] = useState<Role>("client");

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const email = String(data.get("email") ?? "");

    signIn({ name: nameFromEmail(email), email, role });
    router.push(roleHome[role]);
  }

  return (
    <main className="mx-auto w-full max-w-md flex-1 px-6 py-16">
      <h1 className="text-2xl font-semibold">Iniciar sesión</h1>
      <p className="mt-2 text-muted-foreground">
        Accede a tus compras, cotizaciones y puntos de fidelización.
      </p>

      <div className="mt-6 flex items-start gap-2 rounded-lg border border-border bg-secondary/40 p-3 text-sm">
        <Info className="mt-0.5 size-4 shrink-0 text-muted-foreground" aria-hidden />
        <p>
          Prototipo: no se validan credenciales. Elige con qué rol quieres
          recorrer la plataforma.
        </p>
      </div>

      <form onSubmit={handleSubmit} className="mt-8 space-y-6">
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
            autoComplete="current-password"
          />
          <p className="text-xs text-muted-foreground">Mínimo 8 caracteres.</p>
        </div>

        <fieldset className="space-y-2">
          <legend className="text-sm font-medium">Entrar como</legend>
          <RadioGroup
            value={role}
            onValueChange={(value) => setRole(value as Role)}
            className="gap-2"
          >
            {(Object.keys(roleLabels) as Role[]).map((option) => (
              <Label
                key={option}
                className="flex items-center gap-3 rounded-lg border border-border p-3"
              >
                <RadioGroupItem value={option} />
                {roleLabels[option]}
              </Label>
            ))}
          </RadioGroup>
        </fieldset>

        <Button type="submit" className="w-full">
          Ingresar
        </Button>
      </form>

      <p className="mt-6 text-sm text-muted-foreground">
        ¿No tienes cuenta?{" "}
        <Link
          href="/register"
          className="text-primary underline-offset-4 hover:underline"
        >
          Crea una aquí
        </Link>
        .
      </p>
    </main>
  );
}
