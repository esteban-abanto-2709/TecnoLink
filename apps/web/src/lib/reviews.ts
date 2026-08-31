"use client";

import { useSyncExternalStore } from "react";

import { reviews as mockReviews } from "@/data/catalog";
import type { Review } from "@/data/types";

const STORAGE_KEY = "tecnolink-reviews";
const EMPTY: Review[] = [];

let own: Review[] = EMPTY;
const listeners = new Set<() => void>();

if (typeof window !== "undefined") {
  const stored = window.localStorage.getItem(STORAGE_KEY);
  if (stored) {
    try {
      const parsed: unknown = JSON.parse(stored);
      if (Array.isArray(parsed)) own = parsed as Review[];
    } catch {
      window.localStorage.removeItem(STORAGE_KEY);
    }
  }
}

function update(next: Review[]) {
  own = next;
  window.localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
  for (const listener of listeners) listener();
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => {
    listeners.delete(listener);
  };
}

function getSnapshot(): Review[] {
  return own;
}

function getServerSnapshot(): Review[] {
  return EMPTY;
}

function today(): string {
  const now = new Date();
  const month = String(now.getMonth() + 1).padStart(2, "0");
  const day = String(now.getDate()).padStart(2, "0");
  return `${now.getFullYear()}-${month}-${day}`;
}

export function addReview(input: {
  targetId: string;
  author: string;
  rating: number;
  comment: string;
}) {
  update([
    {
      id: `own-${Date.now()}`,
      targetId: input.targetId,
      author: input.author,
      rating: input.rating,
      comment: input.comment,
      date: today(),
    },
    ...own,
  ]);
}

export function useOwnReviews(): Review[] {
  return useSyncExternalStore(subscribe, getSnapshot, getServerSnapshot);
}

export function reviewsWith(mine: Review[], targetId: string): Review[] {
  return [
    ...mine.filter((review) => review.targetId === targetId),
    ...mockReviews.filter((review) => review.targetId === targetId),
  ];
}

export function averageOf(reviews: Review[]): number | null {
  if (reviews.length === 0) return null;
  const total = reviews.reduce((sum, review) => sum + review.rating, 0);
  return Math.round((total / reviews.length) * 10) / 10;
}

export function useReviewsFor(targetId: string) {
  const mine = useOwnReviews();
  const reviews = reviewsWith(mine, targetId);

  return {
    reviews,
    average: averageOf(reviews),
    reviewed: mine.some((review) => review.targetId === targetId),
  };
}
