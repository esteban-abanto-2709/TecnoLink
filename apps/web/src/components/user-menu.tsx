"use client";

import { useRouter } from "next/navigation";
import { ChevronDown, LogOut, User } from "lucide-react";

import { ButtonLink } from "@/components/button-link";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuRadioGroup,
  DropdownMenuRadioItem,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import {
  roleHome,
  roleLabels,
  signOut,
  switchRole,
  useSession,
  type Role,
} from "@/lib/session";

const roleLinks: Record<Role, { href: string; label: string }[]> = {
  client: [
    { href: "/orders", label: "Mis compras" },
    { href: "/quotes", label: "Mis cotizaciones" },
    { href: "/points", label: "Mis puntos" },
  ],
  supplier: [
    { href: "/supplier", label: "Panel del proveedor" },
    { href: "/supplier/listings", label: "Mis publicaciones" },
    { href: "/supplier/quotes", label: "Bandeja de cotizaciones" },
  ],
  admin: [
    { href: "/admin", label: "Panel de administración" },
    { href: "/admin/categories", label: "Categorías" },
    { href: "/admin/suppliers", label: "Proveedores" },
  ],
};

export function UserMenu() {
  const session = useSession();
  const router = useRouter();

  if (!session) {
    return (
      <>
        <ButtonLink href="/login" variant="ghost" size="sm">
          Ingresar
        </ButtonLink>
        <ButtonLink href="/register" size="sm">
          Crear cuenta
        </ButtonLink>
      </>
    );
  }

  function handleRoleChange(value: string) {
    const role = value as Role;
    switchRole(role);
    router.push(roleHome[role]);
  }

  function handleSignOut() {
    signOut();
    router.push("/");
  }

  return (
    <DropdownMenu>
      <DropdownMenuTrigger
        render={
          <Button variant="ghost" size="sm">
            <User />
            <span className="max-w-28 truncate">{session.name}</span>
            <ChevronDown />
          </Button>
        }
      />

      <DropdownMenuContent align="end" className="w-56">
        <DropdownMenuLabel>
          {session.email}
          <span className="mt-0.5 block text-xs font-normal text-muted-foreground">
            {roleLabels[session.role]}
          </span>
        </DropdownMenuLabel>

        <DropdownMenuSeparator />

        {roleLinks[session.role].map((link) => (
          <DropdownMenuItem
            key={link.href}
            onClick={() => router.push(link.href)}
          >
            {link.label}
          </DropdownMenuItem>
        ))}

        <DropdownMenuSeparator />

        <DropdownMenuLabel className="text-xs font-normal text-muted-foreground">
          Cambiar de rol
        </DropdownMenuLabel>
        <DropdownMenuRadioGroup
          value={session.role}
          onValueChange={handleRoleChange}
        >
          {(Object.keys(roleLabels) as Role[]).map((role) => (
            <DropdownMenuRadioItem key={role} value={role}>
              {roleLabels[role]}
            </DropdownMenuRadioItem>
          ))}
        </DropdownMenuRadioGroup>

        <DropdownMenuSeparator />

        <DropdownMenuItem onClick={handleSignOut}>
          <LogOut />
          Cerrar sesión
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
}
