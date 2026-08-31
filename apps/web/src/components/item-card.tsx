import Link from "next/link";

import { CompareToggle } from "@/components/compare-toggle";
import { ItemThumb } from "@/components/item-thumb";
import { Rating } from "@/components/rating";
import { Badge } from "@/components/ui/badge";
import { getCategory, getProduct, getSupplier } from "@/data/catalog";
import type { CatalogItem } from "@/data/types";
import { formatPrice, formatServicePrice } from "@/lib/format";

type ItemCardProps = {
  item: CatalogItem;
};

export function ItemCard({ item }: ItemCardProps) {
  const href =
    item.kind === "product" ? `/products/${item.id}` : `/services/${item.id}`;
  const category = getCategory(item.categoryId);
  const supplier = getSupplier(item.supplierId);
  const brand = getProduct(item.id)?.brand;
  const price = item.pricing
    ? formatServicePrice(item.price, item.pricing)
    : formatPrice(item.price);

  return (
    <div className="relative flex">
      {item.kind === "product" ? (
        <div className="absolute right-2 top-2 z-10">
          <CompareToggle productId={item.id} />
        </div>
      ) : null}

      <Link
        href={href}
        className="group flex flex-1 flex-col overflow-hidden rounded-xl border border-border bg-card transition-colors hover:border-primary/40"
      >
      <ItemThumb
        categoryId={item.categoryId}
        label={brand ?? category?.name}
        className="aspect-video"
      />

      <div className="flex flex-1 flex-col gap-2 p-4">
        {category ? (
          <Badge variant="secondary" className="w-fit">
            {category.name}
          </Badge>
        ) : null}

        <h3 className="font-medium leading-snug group-hover:text-primary">
          {item.name}
        </h3>

        {supplier ? (
          <p className="text-sm text-muted-foreground">
            {supplier.name} · {supplier.district}
          </p>
        ) : null}

        <div className="mt-auto flex items-end justify-between gap-2 pt-2">
          <span className="font-heading text-lg font-semibold">{price}</span>
          <Rating targetId={item.id} />
        </div>
        </div>
      </Link>
    </div>
  );
}
