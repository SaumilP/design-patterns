import { categories, categoryMap } from "../data/categories";
import { intentChips } from "../lib/search";
import { useSeo } from "../lib/seo";
import type { PatternCategory, PatternRecord } from "../types/pattern";
import { CategoryFilterBar } from "../components/filters/CategoryFilterBar";
import { SearchBar } from "../components/search/SearchBar";
import { SearchResults } from "../components/search/SearchResults";

export function CatalogPage(props: {
  patterns: PatternRecord[];
  total: number;
  query: string;
  category: PatternCategory | "all";
  onQueryChange: (value: string) => void;
  onCategoryChange: (value: PatternCategory | "all") => void;
  onIntentClick: (value: string) => void;
  onOpenPalette: () => void;
}) {
  const { patterns, total, query, category, onQueryChange, onCategoryChange, onIntentClick, onOpenPalette } = props;

  useSeo({
    title: category === "all" ? "Explore Patterns" : `${categoryMap[category].label} Patterns`,
    description:
      category === "all"
        ? "Browse the full Java design pattern catalog with fuzzy search and category filters."
        : `Browse ${categoryMap[category].label} patterns with summaries, source links, and trade-offs.`,
    path: category === "all" ? "/patterns" : `/categories/${category}`,
  });

  return (
    <div className="mx-auto max-w-7xl px-4 py-10 md:px-6 md:py-14">
      <section className="theme-panel rounded-[2rem] border p-5 shadow-2xl sm:p-6">
        <div className="grid gap-6 lg:grid-cols-[1.1fr_0.9fr] lg:items-end">
          <div>
            <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">Catalog</div>
            <h1 className="theme-text-primary mt-2 font-display text-3xl font-bold sm:text-4xl">Search the full pattern index</h1>
            <p className="theme-text-secondary mt-3 max-w-2xl text-sm leading-7">
              Exact names, prefixes, aliases, category terms, and intent-style queries all resolve against the generated repository index.
            </p>
          </div>
          <SearchBar query={query} onQueryChange={onQueryChange} onOpenPalette={onOpenPalette} />
        </div>

        <div className="mt-6 flex flex-wrap gap-2">
          {intentChips.map((chip) => (
            <button
              key={chip}
              type="button"
              onClick={() => onIntentClick(chip)}
              className="theme-tag rounded-full border px-3 py-2 text-sm transition hover:border-[color:var(--border-strong)]"
            >
              {chip}
            </button>
          ))}
        </div>

        <div className="mt-6">
          <CategoryFilterBar activeCategory={category} onChange={onCategoryChange} />
        </div>
      </section>

      <section className="mt-8">
        <div className="theme-text-muted mb-5 flex flex-col items-start justify-between gap-3 text-sm sm:flex-row sm:flex-wrap sm:items-center">
          <div>
            Showing <span className="theme-text-primary font-semibold">{patterns.length}</span> of{" "}
            <span className="theme-text-primary font-semibold">{total}</span> patterns
          </div>
          <div className="flex w-full flex-wrap gap-2 sm:w-auto">
            {categories.map((item) => (
              <button
                key={item.id}
                type="button"
                onClick={() => onCategoryChange(item.id)}
                className={`rounded-full px-3 py-1 text-xs font-semibold uppercase tracking-[0.18em] ${
                  category === item.id
                    ? "bg-cyan-400/18 text-cyan-100"
                    : "bg-[var(--panel-soft)] text-[var(--text-muted)]"
                }`}
              >
                {item.shortLabel}
              </button>
            ))}
          </div>
        </div>
        <SearchResults patterns={patterns} query={query} onSuggestionClick={onIntentClick} />
      </section>
    </div>
  );
}
