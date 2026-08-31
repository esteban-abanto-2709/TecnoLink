import { Stars } from "@/components/rating";
import { ratingFor, reviewsFor } from "@/data/catalog";
import { formatDate } from "@/lib/format";

type ReviewListProps = {
  targetId: string;
};

export function ReviewList({ targetId }: ReviewListProps) {
  const reviews = reviewsFor(targetId);
  const average = ratingFor(targetId);

  if (average === null) {
    return (
      <div className="rounded-xl border border-dashed border-border p-6 text-center">
        <p className="text-muted-foreground">
          Todavía no hay reseñas. Las primeras llegan cuando alguien complete una
          compra.
        </p>
      </div>
    );
  }

  return (
    <div className="space-y-5">
      <div className="flex items-center gap-3">
        <span className="font-heading text-3xl font-semibold">
          {average.toFixed(1)}
        </span>
        <div>
          <Stars value={average} />
          <p className="mt-1 text-sm text-muted-foreground">
            {reviews.length === 1
              ? "1 reseña"
              : `${reviews.length} reseñas`}
          </p>
        </div>
      </div>

      <ul className="divide-y divide-border border-t border-border">
        {reviews.map((review) => (
          <li key={review.id} className="py-4">
            <div className="flex flex-wrap items-center gap-x-3 gap-y-1">
              <span className="font-medium">{review.author}</span>
              <Stars value={review.rating} />
              <span className="text-sm text-muted-foreground">
                {formatDate(review.date)}
              </span>
            </div>
            <p className="mt-2 text-muted-foreground">{review.comment}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
