import { ListingEditor } from "@/components/listing-editor";

export default async function Page({
  params,
}: PageProps<"/supplier/listings/[id]">) {
  const { id } = await params;

  return (
    <main className="mx-auto w-full max-w-3xl flex-1 px-6 py-10">
      <ListingEditor id={id} />
    </main>
  );
}
