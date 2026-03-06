import { categories } from "../../data/categories";
import type { PatternCategory } from "../../types/pattern";

export function CategoryFilterBar(props: {
  activeCategory: PatternCategory | "all";
  onChange: (value: PatternCategory | "all") => void;
}) {
  const { activeCategory, onChange } = props;

  return (
    <div className="flex flex-wrap gap-3" aria-label="Category filters">
      <button
        type="button"
        onClick={() => onChange("all")}
        className={`rounded-full px-4 py-2 text-sm font-semibold transition ${
          activeCategory === "all"
            ? "bg-cyan-400/18 text-cyan-100"
            : "border border-white/10 bg-white/[0.04] text-slate-300 hover:border-white/20"
        }`}
      >
        All
      </button>
      {categories.map((category) => (
        <button
          key={category.id}
          type="button"
          onClick={() => onChange(category.id)}
          className={`rounded-full px-4 py-2 text-sm font-semibold transition ${
            activeCategory === category.id
              ? "bg-cyan-400/18 text-cyan-100"
              : "border border-white/10 bg-white/[0.04] text-slate-300 hover:border-white/20"
          }`}
        >
          {category.label}
        </button>
      ))}
    </div>
  );
}
