import type { ServicePricing } from "@/data/types";

const priceFormatter = new Intl.NumberFormat("es-PE", {
  style: "currency",
  currency: "PEN",
  maximumFractionDigits: 0,
});

const dateFormatter = new Intl.DateTimeFormat("es-PE", { dateStyle: "long" });

export function formatPrice(value: number): string {
  return priceFormatter.format(value);
}

export function formatServicePrice(
  value: number,
  pricing: ServicePricing
): string {
  switch (pricing) {
    case "hourly":
      return `${formatPrice(value)} por hora`;
    case "from":
      return `Desde ${formatPrice(value)}`;
    case "fixed":
      return formatPrice(value);
  }
}

export function formatDate(iso: string): string {
  const [year, month, day] = iso.split("-").map(Number);
  return dateFormatter.format(new Date(year, month - 1, day));
}
