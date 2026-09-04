"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState, type FormEvent } from "react";
import { Info } from "lucide-react";

import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { Textarea } from "@/components/ui/textarea";
import { roleHome, signIn, type Role } from "@/lib/session";
import { registerSupplier } from "@/lib/suppliers";
import { isValidRuc } from "@/lib/utils";

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

  const isSupplier = role === "supplier";

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const name = String(data.get("name") ?? "").trim();
    const email = String(data.get("email") ?? "").trim();
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

    if (!isSupplier) {
      setError("");
      signIn({ name, email, role });
      router.push(roleHome.client);
      return;
    }

    const ruc = String(data.get("ruc") ?? "").trim();
    const phone = String(data.get("phone") ?? "").trim();
    const district = String(data.get("district") ?? "").trim();
    const description = String(data.get("description") ?? "").trim();

    if (!isValidRuc(ruc)) {
      setError("El RUC debe tener 11 dígitos y empezar en 10, 15, 17 o 20.");
      return;
    }

    if (district === "") {
      setError("Indica el distrito donde opera tu negocio.");
      return;
    }

    if (description.length < 30) {
      setError(
        "Describe tu negocio en al menos 30 caracteres: es lo que el cliente lee en tu perfil."
      );
      return;
    }

    setError("");
    const supplier = registerSupplier({
      name,
      ruc,
      phone,
      district,
      description,
    });
    signIn({ name, email, role, supplierId: supplier.id });
    router.push(roleHome.supplier);
  }

  return (
    <main className="mx-auto w-full max-w-md flex-1 px-6 py-16">
      <h1 className="text-2xl font-semibold">Crear cuenta</h1>
      <p className="mt-2 text-muted-foreground">
        {isSupplier
          ? "Registra tu negocio para publicar equipos y servicios."
          : "Guarda tus datos para comprar y solicitar cotizaciones."}
      </p>

      <fieldset className="mt-8 space-y-2">
        <legend className="text-sm font-medium">Tipo de cuenta</legend>
        <RadioGroup
          value={role}
          onValueChange={(value) => {
            setRole(value as Role);
            setError("");
          }}
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

      {isSupplier ? (
        <div className="mt-6 flex items-start gap-2 rounded-lg border border-border bg-secondary/40 p-3 text-sm">
          <Info
            className="mt-0.5 size-4 shrink-0 text-muted-foreground"
            aria-hidden
          />
          <p>
            Tu cuenta entra como <strong>Por revisar</strong>. Un administrador
            valida los datos antes de que aparezcas como proveedor verificado.
          </p>
        </div>
      ) : null}

      <form onSubmit={handleSubmit} className="mt-6 space-y-6">
        <div className="space-y-2">
          <Label htmlFor="name">
            {isSupplier ? "Razón social o nombre comercial" : "Nombre completo"}
          </Label>
          <Input
            id="name"
            name="name"
            required
            autoComplete={isSupplier ? "organization" : "name"}
            placeholder={isSupplier ? "Importaciones TechPerú" : undefined}
          />
        </div>

        {isSupplier ? (
          <>
            <div className="space-y-2">
              <Label htmlFor="ruc">RUC</Label>
              <Input
                id="ruc"
                name="ruc"
                inputMode="numeric"
                maxLength={11}
                required
                placeholder="20553417802"
              />
              <p className="text-xs text-muted-foreground">
                11 dígitos, tal como figura en SUNAT.
              </p>
            </div>

            <div className="space-y-2">
              <Label htmlFor="district">Distrito</Label>
              <Input
                id="district"
                name="district"
                required
                placeholder="San Miguel"
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="phone">Teléfono de contacto</Label>
              <Input
                id="phone"
                name="phone"
                type="tel"
                required
                autoComplete="tel"
                placeholder="(01) 562 4471"
              />
            </div>
          </>
        ) : null}

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

        {isSupplier ? (
          <div className="space-y-2">
            <Label htmlFor="description">Descripción del negocio</Label>
            <Textarea
              id="description"
              name="description"
              rows={3}
              placeholder="Qué vendes o qué servicios ofreces, desde cuándo y qué te diferencia."
            />
            <p className="text-xs text-muted-foreground">
              Es el texto que los clientes leen en tu perfil público.
            </p>
          </div>
        ) : null}

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
          {isSupplier ? "Registrar mi negocio" : "Crear cuenta"}
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
