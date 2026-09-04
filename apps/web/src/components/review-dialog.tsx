"use client";

import { useState, type FormEvent } from "react";
import { Star } from "lucide-react";

import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogClose,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { REVIEW_POINTS } from "@/lib/points";
import { addReview, useReviewsFor } from "@/lib/reviews";
import { useSession } from "@/lib/session";
import { cn } from "@/lib/utils";

type ReviewDialogProps = {
  targetId: string;
  targetName: string;
};

export function ReviewDialog({ targetId, targetName }: ReviewDialogProps) {
  const session = useSession();
  const { reviewed } = useReviewsFor(targetId);
  const [open, setOpen] = useState(false);
  const [rating, setRating] = useState(5);
  const [comment, setComment] = useState("");
  const [error, setError] = useState("");

  if (reviewed) {
    return (
      <span className="text-sm text-muted-foreground">
        Reseña registrada · +{REVIEW_POINTS} puntos
      </span>
    );
  }

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    if (comment.trim().length < 10) {
      setError("Escribe al menos 10 caracteres para que la reseña sirva.");
      return;
    }

    addReview({
      targetId,
      author: session?.name ?? "Cliente",
      rating,
      comment: comment.trim(),
    });
    setError("");
    setComment("");
    setOpen(false);
  }

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger
        render={
          <Button variant="outline" size="sm">
            Dejar reseña
          </Button>
        }
      />

      <DialogContent>
        <form onSubmit={handleSubmit}>
          <DialogHeader>
            <DialogTitle>Reseñar {targetName}</DialogTitle>
            <DialogDescription>
              Cuenta cómo te fue con la compra. Tu reseña queda asociada a este
              pedido y te suma {REVIEW_POINTS} puntos.
            </DialogDescription>
          </DialogHeader>

          <div className="space-y-4 py-4">
            <fieldset className="space-y-2">
              <legend className="text-sm font-medium">Calificación</legend>
              <div className="flex gap-1">
                {[1, 2, 3, 4, 5].map((value) => (
                  <button
                    key={value}
                    type="button"
                    aria-label={`${value} de 5 estrellas`}
                    aria-pressed={rating === value}
                    onClick={() => setRating(value)}
                    className="rounded-md p-1 hover:bg-muted"
                  >
                    <Star
                      className={cn(
                        "size-6",
                        value <= rating
                          ? "fill-accent text-accent"
                          : "text-border"
                      )}
                    />
                  </button>
                ))}
              </div>
            </fieldset>

            <div className="space-y-2">
              <Label htmlFor={`comment-${targetId}`}>Comentario</Label>
              <Textarea
                id={`comment-${targetId}`}
                value={comment}
                onChange={(event) => setComment(event.target.value)}
                placeholder="Qué te pareció el equipo o el servicio, y cómo fue la atención del proveedor."
                rows={4}
              />
            </div>

            {error ? (
              <p role="alert" className="text-sm text-destructive">
                {error}
              </p>
            ) : null}
          </div>

          <DialogFooter>
            <DialogClose
              render={
                <Button type="button" variant="ghost">
                  Cancelar
                </Button>
              }
            />
            <Button type="submit">Publicar reseña</Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}
