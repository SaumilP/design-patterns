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
            : "border border-[color:var(--border)] bg-[var(--panel-soft)] text-[var(--text-secondary)] hover:border-[color:var(--border-strong)]"
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
              : "border border-[color:var(--border)] bg-[var(--panel-soft)] text-[var(--text-secondary)] hover:border-[color:var(--border-strong)]"
          }`}
        >
          {category.label}
        </button>
      ))}
    </div>
  );
}
