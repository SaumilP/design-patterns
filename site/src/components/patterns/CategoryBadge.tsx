import { categoryMap } from "../../data/categories";
import type { PatternCategory } from "../../types/pattern";

export function CategoryBadge({ category }: { category: PatternCategory }) {
  const meta = categoryMap[category];
  return (
    <span className={`inline-flex rounded-full border px-3 py-1 text-[11px] font-semibold uppercase tracking-[0.22em] ${meta.badgeClass}`}>
      {meta.label}
    </span>
  );
}
