import { Link } from "react-router-dom";
import { useSeo } from "../lib/seo";

export function NotFoundPage() {
  useSeo({
    title: "Not Found",
    description: "The requested route does not match a generated pattern page.",
    path: "/404",
  });

  return (
    <div className="mx-auto max-w-3xl px-4 py-24 text-center md:px-6">
      <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">404</div>
      <h1 className="mt-4 font-display text-5xl font-bold text-white">Pattern route not found</h1>
      <p className="mt-4 text-lg text-slate-400">Use the catalog or command palette to jump back into the generated index.</p>
      <Link
        to="/patterns"
        className="mt-8 inline-flex rounded-full border border-cyan-400/30 bg-cyan-400/16 px-5 py-3 text-sm font-semibold text-cyan-100"
      >
        Back to catalog
      </Link>
    </div>
  );
}
