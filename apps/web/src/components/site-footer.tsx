import Link from "next/link";

const sections = [
  {
    title: "Cliente",
    links: [
      { href: "/", label: "Portada" },
      { href: "/search", label: "Resultados de búsqueda" },
      { href: "/products/1", label: "Detalle de producto" },
      { href: "/services/1", label: "Detalle de servicio" },
      { href: "/compare", label: "Comparador" },
      { href: "/suppliers/1", label: "Perfil de proveedor" },
      { href: "/cart", label: "Carrito" },
      { href: "/checkout", label: "Compra simulada" },
      { href: "/orders", label: "Mis compras" },
      { href: "/quotes", label: "Mis cotizaciones" },
      { href: "/points", label: "Mis puntos" },
    ],
  },
  {
    title: "Proveedor",
    links: [
      { href: "/supplier", label: "Panel" },
      { href: "/supplier/listings", label: "Mis publicaciones" },
      { href: "/supplier/listings/new", label: "Publicar" },
      { href: "/supplier/quotes", label: "Bandeja de cotizaciones" },
    ],
  },
  {
    title: "Administración",
    links: [
      { href: "/admin", label: "Panel" },
      { href: "/admin/categories", label: "Categorías" },
      { href: "/admin/suppliers", label: "Proveedores" },
    ],
  },
  {
    title: "Acceso",
    links: [
      { href: "/login", label: "Inicio de sesión" },
      { href: "/register", label: "Registro" },
      { href: "/style-guide", label: "Sistema visual" },
    ],
  },
];

export function SiteFooter() {
  return (
    <footer className="mt-auto border-t border-border bg-muted/40">
      <div className="mx-auto w-full max-w-6xl px-6 py-10">
        <div className="grid grid-cols-2 gap-8 sm:grid-cols-4">
          {sections.map((section) => (
            <div key={section.title}>
              <h2 className="text-xs font-semibold uppercase tracking-wide text-muted-foreground">
                {section.title}
              </h2>
              <ul className="mt-3 space-y-1.5">
                {section.links.map((link) => (
                  <li key={link.href}>
                    <Link
                      href={link.href}
                      className="text-sm text-muted-foreground underline-offset-4 hover:text-foreground hover:underline"
                    >
                      {link.label}
                    </Link>
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </div>

        <p className="mt-10 text-xs text-muted-foreground">
          Prototipo del curso Marcos de Desarrollo Web. Mientras dure el
          prototipo, este pie hace de mapa: lleva a cada pantalla sin necesidad
          de sesión.
        </p>
      </div>
    </footer>
  );
}
