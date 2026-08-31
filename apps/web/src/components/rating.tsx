import { Star } from "lucide-react";

import { ratingFor, reviewsFor } from "@/data/catalog";

type RatingProps = {
  targetId: string;
};

export function Rating({ targetId }: RatingProps) {
  const rating = ratingFor(targetId);

  if (rating === null) {
    return <span className="text-sm text-muted-foreground">Sin reseñas</span>;
  }

  const count = reviewsFor(targetId).length;

  return (
    <span className="flex items-center gap-1.5 text-sm">
      <Star className="size-4 fill-accent text-accent" aria-hidden />
      <span className="font-medium">{rating.toFixed(1)}</span>
      <span className="text-muted-foreground">
        {count === 1 ? "1 reseña" : `${count} reseñas`}
      </span>
    </span>
  );
}
