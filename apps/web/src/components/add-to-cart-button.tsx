"use client";

import { useState } from "react";
import { Check, ShoppingCart } from "lucide-react";

import { Button } from "@/components/ui/button";
import { addToCart, type CartLine } from "@/lib/cart";

type AddToCartButtonProps = {
  item: Omit<CartLine, "quantity">;
  label?: string;
  variant?: "default" | "secondary";
};

export function AddToCartButton({
  item,
  label = "Agregar al carrito",
  variant = "default",
}: AddToCartButtonProps) {
  const [added, setAdded] = useState(false);

  function handleClick() {
    addToCart(item);
    setAdded(true);
    window.setTimeout(() => setAdded(false), 2000);
  }

  return (
    <Button onClick={handleClick} variant={variant}>
      {added ? <Check /> : <ShoppingCart />}
      {added ? "Agregado al carrito" : label}
    </Button>
  );
}
