"use client";

import { useCart } from "@/lib/cart";

export function CartCount() {
  const { count } = useCart();

  if (count === 0) return null;

  return (
    <span
      aria-label={`${count} en el carrito`}
      className="absolute -right-1 -top-1 grid min-w-4 place-items-center rounded-full bg-primary px-1 text-[10px] font-semibold leading-4 text-primary-foreground"
    >
      {count}
    </span>
  );
}
