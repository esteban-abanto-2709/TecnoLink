import {
  Cable,
  Code,
  Laptop,
  MemoryStick,
  Monitor,
  Package,
  Printer,
  Smartphone,
  Wifi,
  Wrench,
  type LucideIcon,
} from "lucide-react";

const icons: Record<string, LucideIcon> = {
  laptops: Laptop,
  smartphones: Smartphone,
  monitors: Monitor,
  printers: Printer,
  components: MemoryStick,
  networking: Wifi,
  support: Wrench,
  installation: Cable,
  development: Code,
};

type CategoryIconProps = {
  categoryId: string;
  className?: string;
};

export function CategoryIcon({ categoryId, className }: CategoryIconProps) {
  const Icon = icons[categoryId] ?? Package;
  return <Icon className={className} aria-hidden />;
}
