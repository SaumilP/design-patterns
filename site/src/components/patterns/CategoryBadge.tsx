import { categoryMap } from "../../data/categories";
import type { PatternCategory } from "../../types/pattern";

export function CategoryBadge({ category }: { category: PatternCategory }) {
  return (
    <span className="inline-flex rounded-full border border-white/12 bg-white/[0.04] px-3 py-1 text-[11px] font-semibold uppercase tracking-[0.22em] text-slate-300">
      {categoryMap[category].label}
    </span>
  );
}
