import Link from "next/link";

type Screen = {
  href: string;
  label: string;
  hu?: string;
};

const sections: { title: string; screens: Screen[] }[] = [
  {
    title: "Cliente",
    screens: [
      { href: "/", label: "Portada y búsqueda", hu: "HU-03" },
      { href: "/search", label: "Resultados y filtros", hu: "HU-04" },
      {
        href: "/products/lenovo-ideapad-3",
        label: "Detalle de equipo",
        hu: "HU-05",
      },
      {
        href: "/services/soporte-domicilio",
        label: "Detalle de servicio",
        hu: "HU-06",
      },
      { href: "/compare", label: "Comparador", hu: "HU-07" },
      {
        href: "/suppliers/techperu",
        label: "Perfil de proveedor",
        hu: "HU-10",
      },
      { href: "/cart", label: "Carrito" },
      { href: "/checkout", label: "Compra simulada" },
      { href: "/orders", label: "Mis compras y reseñas", hu: "HU-11/12" },
      { href: "/points", label: "Mis puntos", hu: "HU-13/14" },
      {
        href: "/quotes/new?item=lenovo-ideapad-3",
        label: "Solicitar cotización",
        hu: "HU-08",
      },
      { href: "/quotes", label: "Mis cotizaciones" },
    ],
  },
  {
    title: "Proveedor",
    screens: [
      { href: "/supplier", label: "Panel del proveedor" },
      { href: "/supplier/listings", label: "Mis publicaciones", hu: "HU-16" },
      {
        href: "/supplier/listings/new",
        label: "Publicar equipo o servicio",
        hu: "HU-15",
      },
      {
        href: "/supplier/listings/lenovo-ideapad-3",
        label: "Editar publicación",
        hu: "HU-16",
      },
      {
        href: "/supplier/quotes",
        label: "Bandeja de cotizaciones",
        hu: "HU-09/17",
      },
    ],
  },
  {
    title: "Administración",
    screens: [
      { href: "/admin", label: "Panel de administración" },
      { href: "/admin/categories", label: "Gestión de categorías", hu: "HU-18" },
      {
        href: "/admin/suppliers",
        label: "Gestión de proveedores",
        hu: "HU-19",
      },
    ],
  },
  {
    title: "Cuenta y sistema",
    screens: [
      { href: "/register", label: "Crear cuenta", hu: "HU-01" },
      { href: "/login", label: "Iniciar sesión", hu: "HU-02" },
      { href: "/style-guide", label: "Sistema visual" },
    ],
  },
];

export function SiteFooter() {
  return (
    <footer className="mt-auto border-t border-border bg-muted/40">
      <div className="mx-auto w-full max-w-6xl px-6 py-10">
        <h2 className="font-heading text-sm font-semibold">
          Mapa del prototipo
        </h2>
        <p className="mt-1 max-w-2xl text-sm text-muted-foreground">
          Las 23 pantallas del proyecto, con la historia de usuario que cubre
          cada una. Todas son alcanzables sin iniciar sesión: este pie existe
          mientras dure el prototipo y desaparece en la versión final.
        </p>

        <div className="mt-8 grid grid-cols-2 gap-8 sm:grid-cols-4">
          {sections.map((section) => (
            <div key={section.title}>
              <h3 className="text-xs font-semibold uppercase tracking-wide text-muted-foreground">
                {section.title}
              </h3>
              <ul className="mt-3 space-y-2">
                {section.screens.map((screen) => (
                  <li key={screen.href}>
                    <Link
                      href={screen.href}
                      className="text-sm text-muted-foreground underline-offset-4 hover:text-foreground hover:underline"
                    >
                      {screen.label}
                    </Link>
                    {screen.hu ? (
                      <span className="ml-1.5 text-xs text-muted-foreground/70">
                        {screen.hu}
                      </span>
                    ) : null}
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </div>

        <div className="mt-10 space-y-1 border-t border-border pt-6 text-xs text-muted-foreground">
          <p>
            Tres pantallas dependen de lo que hayas hecho antes: el carrito y la
            compra necesitan algo agregado, y las reseñas solo se habilitan sobre
            una compra registrada.
          </p>
          <p>
            El rol proveedor representa siempre a Importaciones TechPerú. El rol
            activo se cambia desde el menú del header.
          </p>
          <p className="pt-2">
            TecnoLink · Prototipo del curso Marcos de Desarrollo Web · Lima, 2026
          </p>
        </div>
      </div>
    </footer>
  );
}
