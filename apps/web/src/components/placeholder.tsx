import { Construction } from "lucide-react";

type PlaceholderProps = {
  title: string;
  task: string;
  description: string;
};

export function Placeholder({ title, task, description }: PlaceholderProps) {
  return (
    <div className="mx-auto flex w-full max-w-xl flex-1 flex-col items-center justify-center gap-3 px-6 py-24 text-center">
      <Construction className="size-6 text-muted-foreground" />
      <h1 className="text-2xl font-semibold">{title}</h1>
      <p className="text-muted-foreground">{description}</p>
      <p className="text-xs text-muted-foreground">
        Pantalla pendiente · se construye en {task}
      </p>
    </div>
  );
}
