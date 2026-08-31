export type CategoryKind = "product" | "service";

export type Category = {
  id: string;
  name: string;
  kind: CategoryKind;
};

export type Supplier = {
  id: string;
  name: string;
  district: string;
  description: string;
  since: number;
  verified: boolean;
};

export type Product = {
  id: string;
  name: string;
  brand: string;
  categoryId: string;
  supplierId: string;
  price: number;
  description: string;
  specs: Record<string, string>;
};

export type ServicePricing = "fixed" | "from" | "hourly";

export type Service = {
  id: string;
  name: string;
  categoryId: string;
  supplierId: string;
  price: number;
  pricing: ServicePricing;
  description: string;
  coverage: string;
};

export type CatalogItem = {
  id: string;
  kind: CategoryKind;
  name: string;
  categoryId: string;
  supplierId: string;
  price: number;
  pricing?: ServicePricing;
  description: string;
};

export type Review = {
  id: string;
  targetId: string;
  author: string;
  rating: number;
  comment: string;
  date: string;
};

export type QuoteStatus = "sent" | "answered" | "expired";

export type Quote = {
  id: string;
  itemId: string;
  supplierId: string;
  quantity: number;
  requirement: string;
  status: QuoteStatus;
  requestedOn: string;
  answer?: {
    price: number;
    validUntil: string;
    conditions: string;
    notes: string;
  };
};

export type OrderLine = {
  itemId: string;
  name: string;
  quantity: number;
  price: number;
};

export type Order = {
  id: string;
  date: string;
  lines: OrderLine[];
  total: number;
  pointsEarned: number;
};

export type PointsMovement = {
  id: string;
  date: string;
  description: string;
  points: number;
  used: boolean;
};
