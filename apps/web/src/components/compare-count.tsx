"use client";

import { useCompare } from "@/lib/compare";

export function CompareCount() {
  const { count } = useCompare();

  if (count === 0) return null;

  return (
    <span
      aria-label={`${count} en el comparador`}
      className="ml-1 grid min-w-4 place-items-center rounded-full bg-primary px-1 text-[10px] font-semibold leading-4 text-primary-foreground"
    >
      {count}
    </span>
  );
}
