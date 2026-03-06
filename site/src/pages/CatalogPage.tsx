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
      <section className="rounded-[2rem] border border-white/10 bg-white/[0.04] p-6 shadow-2xl">
        <div className="grid gap-6 lg:grid-cols-[1.1fr_0.9fr] lg:items-end">
          <div>
            <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">Catalog</div>
            <h1 className="mt-2 font-display text-4xl font-bold text-white">Search the full pattern index</h1>
            <p className="mt-3 max-w-2xl text-sm leading-7 text-slate-300">
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
              className="rounded-full border border-white/10 bg-slate-900/70 px-3 py-2 text-sm text-slate-200 transition hover:border-white/20"
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
        <div className="mb-5 flex flex-wrap items-center justify-between gap-3 text-sm text-slate-400">
          <div>
            Showing <span className="font-semibold text-white">{patterns.length}</span> of{" "}
            <span className="font-semibold text-white">{total}</span> patterns
          </div>
          <div className="flex flex-wrap gap-2">
            {categories.map((item) => (
              <button
                key={item.id}
                type="button"
                onClick={() => onCategoryChange(item.id)}
                className={`rounded-full px-3 py-1 text-xs font-semibold uppercase tracking-[0.18em] ${
                  category === item.id ? "bg-cyan-400/18 text-cyan-100" : "bg-white/[0.04] text-slate-400"
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
