import { Link } from "react-router-dom";
import type { PatternRecord } from "../../types/pattern";

export function RelatedPatterns({ items }: { items: PatternRecord[] }) {
  if (!items.length) {
    return null;
  }

  return (
    <section className="rounded-[1.75rem] border border-white/10 bg-white/[0.04] p-5">
      <div className="text-xs font-semibold uppercase tracking-[0.28em] text-cyan-300">You may also want</div>
      <div className="mt-4 space-y-3">
        {items.map((item) => (
          <Link
            key={item.id}
            to={`/patterns/${item.slug}`}
            className="block rounded-2xl border border-white/8 bg-slate-950/60 px-4 py-3 transition hover:border-white/20"
          >
            <div className="font-semibold text-slate-100">{item.name}</div>
            <div className="mt-1 text-sm text-slate-400">{item.summary}</div>
          </Link>
        ))}
      </div>
    </section>
  );
}
