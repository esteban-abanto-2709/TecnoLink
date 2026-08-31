import { CategoryIcon } from "@/components/category-icon";
import { cn } from "@/lib/utils";

type ItemThumbProps = {
  categoryId: string;
  label?: string;
  className?: string;
  iconClassName?: string;
};

export function ItemThumb({
  categoryId,
  label,
  className,
  iconClassName,
}: ItemThumbProps) {
  return (
    <div
      className={cn(
        "relative isolate overflow-hidden bg-gradient-to-br from-secondary via-muted to-secondary",
        className
      )}
    >
      <CategoryIcon
        categoryId={categoryId}
        className="absolute -bottom-8 -right-8 -z-10 size-40 text-primary/10"
      />

      <div className="grid size-full place-items-center">
        <CategoryIcon
          categoryId={categoryId}
          className={cn("size-10 text-primary/60", iconClassName)}
        />
      </div>

      {label ? (
        <span className="absolute left-3 top-3 text-xs font-medium uppercase tracking-wide text-muted-foreground">
          {label}
        </span>
      ) : null}
    </div>
  );
}
