import Link from "next/link";
import type { ComponentProps } from "react";

import { Button } from "@/components/ui/button";

type ButtonLinkProps = Omit<
  ComponentProps<typeof Button>,
  "render" | "nativeButton"
> & {
  href: string;
};

export function ButtonLink({ href, ...props }: ButtonLinkProps) {
  return <Button {...props} nativeButton={false} render={<Link href={href} />} />;
}
