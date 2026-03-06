import { Link } from "react-router-dom";
import type { PatternRecord } from "../../types/pattern";

export function RelatedPatterns({ items }: { items: PatternRecord[] }) {
  if (!items.length) {
    return null;
  }

  return (
    <section className="theme-panel rounded-[1.75rem] border p-5">
      <div className="text-xs font-semibold uppercase tracking-[0.28em] text-cyan-300">You may also want</div>
      <div className="mt-4 space-y-3">
        {items.map((item) => (
          <Link
            key={item.id}
            to={`/patterns/${item.slug}`}
            className="theme-input block rounded-2xl border border-[color:var(--border)] px-4 py-3 transition hover:border-[color:var(--border-strong)]"
          >
            <div className="theme-text-primary font-semibold">{item.name}</div>
            <div className="theme-text-secondary mt-1 text-sm">{item.summary}</div>
          </Link>
        ))}
      </div>
    </section>
  );
}
