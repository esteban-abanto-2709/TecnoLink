import { ListingForm } from "@/components/listing-form";

export default function Page() {
  return (
    <main className="mx-auto w-full max-w-3xl flex-1 px-6 py-10">
      <h1 className="text-2xl font-semibold">Publicar producto o servicio</h1>
      <p className="mt-2 text-muted-foreground">
        Los datos que cargues son los que el cliente usa para comparar. Sé
        específico con el precio y la descripción.
      </p>

      <ListingForm />
    </main>
  );
}
