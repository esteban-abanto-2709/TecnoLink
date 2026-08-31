"use client";

import { Star } from "lucide-react";

import { useReviewsFor } from "@/lib/reviews";
import { cn } from "@/lib/utils";

type StarsProps = {
  value: number;
  className?: string;
};

export function Stars({ value, className }: StarsProps) {
  return (
    <span
      className={cn("flex items-center gap-0.5", className)}
      aria-label={`${value} de 5 estrellas`}
    >
      {[1, 2, 3, 4, 5].map((position) => (
        <Star
          key={position}
          aria-hidden
          className={cn(
            "size-4",
            position <= Math.round(value)
              ? "fill-accent text-accent"
              : "text-border"
          )}
        />
      ))}
    </span>
  );
}

type RatingProps = {
  targetId: string;
};

export function Rating({ targetId }: RatingProps) {
  const { reviews, average } = useReviewsFor(targetId);

  if (average === null) {
    return <span className="text-sm text-muted-foreground">Sin reseñas</span>;
  }

  return (
    <span className="flex items-center gap-1.5 text-sm">
      <Star className="size-4 fill-accent text-accent" aria-hidden />
      <span className="font-medium">{average.toFixed(1)}</span>
      <span className="text-muted-foreground">
        {reviews.length === 1 ? "1 reseña" : `${reviews.length} reseñas`}
      </span>
    </span>
  );
}
