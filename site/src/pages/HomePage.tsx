import { Link } from "react-router-dom";
import { categories } from "../data/categories";
import { intentChips } from "../lib/search";
import { useSeo } from "../lib/seo";
import type { PatternRecord } from "../types/pattern";
import { Button } from "../components/common/Button";
import { SearchBar } from "../components/search/SearchBar";
import { SearchResults } from "../components/search/SearchResults";
import { repositoryUrl } from "../lib/github";

export function HomePage(props: {
  featuredPatterns: PatternRecord[];
  query: string;
  onQueryChange: (value: string) => void;
  onIntentClick: (value: string) => void;
  onOpenPalette: () => void;
  stats: {
    totalPatterns: number;
    totalCategories: number;
    examplesIncluded: number;
    testedPatterns: number;
  };
}) {
  const { featuredPatterns, query, onQueryChange, onIntentClick, onOpenPalette, stats } = props;

  useSeo({
    title: "Practical Java Design Patterns",
    description: "Runnable examples, trade-offs, and architecture notes in one searchable catalog.",
    path: "/",
  });

  return (
    <div className="mx-auto max-w-7xl px-4 py-10 md:px-6 md:py-16">
      <section className="grid gap-10 lg:grid-cols-[1.1fr_0.9fr] lg:items-end">
        <div>
          <div className="inline-flex rounded-full border border-cyan-400/22 bg-cyan-400/12 px-4 py-2 font-mono text-xs uppercase tracking-[0.3em] text-cyan-200">
            Modern GitHub Pages catalog
          </div>
          <h1 className="mt-6 max-w-4xl font-display text-5xl font-bold tracking-tight text-white md:text-7xl">
            Practical Java Design Patterns
          </h1>
          <p className="mt-5 max-w-2xl text-lg leading-8 text-slate-300">
            Runnable examples, trade-offs, and architecture notes in one searchable catalog.
          </p>
          <div className="mt-8 flex flex-wrap gap-3">
            <Link
              to="/patterns"
              className="rounded-full border border-cyan-400/30 bg-cyan-400/16 px-5 py-3 text-sm font-semibold text-cyan-100 transition hover:border-cyan-300 hover:bg-cyan-400/24"
            >
              Explore Patterns
            </Link>
            <Button href={repositoryUrl} target="_blank" rel="noreferrer" variant="secondary">
              View Repository
            </Button>
          </div>
        </div>

        <div className="rounded-[2rem] border border-white/10 bg-white/[0.04] p-6 shadow-2xl">
          <div className="text-xs font-semibold uppercase tracking-[0.28em] text-cyan-300">Command search</div>
          <div className="mt-4">
            <SearchBar query={query} onQueryChange={onQueryChange} onOpenPalette={onOpenPalette} />
          </div>
          <div className="mt-5 flex flex-wrap gap-2">
            {intentChips.map((chip) => (
              <button
                key={chip}
                type="button"
                onClick={() => onIntentClick(chip)}
                className="rounded-full border border-white/10 bg-slate-900/80 px-3 py-2 text-sm font-medium text-slate-200 transition hover:border-white/20"
              >
                {chip}
              </button>
            ))}
          </div>
        </div>
      </section>

      <section className="mt-10 grid gap-4 md:grid-cols-4">
        <div className="rounded-[1.5rem] border border-white/10 bg-white/[0.04] p-5">
          <div className="font-mono text-xs uppercase tracking-[0.26em] text-cyan-300">Patterns</div>
          <div className="mt-3 font-display text-3xl font-bold text-white">{stats.totalPatterns}</div>
        </div>
        <div className="rounded-[1.5rem] border border-white/10 bg-white/[0.04] p-5">
          <div className="font-mono text-xs uppercase tracking-[0.26em] text-cyan-300">Categories</div>
          <div className="mt-3 font-display text-3xl font-bold text-white">{stats.totalCategories}</div>
        </div>
        <div className="rounded-[1.5rem] border border-white/10 bg-white/[0.04] p-5">
          <div className="font-mono text-xs uppercase tracking-[0.26em] text-cyan-300">Examples</div>
          <div className="mt-3 font-display text-3xl font-bold text-white">{stats.examplesIncluded}</div>
        </div>
        <div className="rounded-[1.5rem] border border-white/10 bg-white/[0.04] p-5">
          <div className="font-mono text-xs uppercase tracking-[0.26em] text-cyan-300">Tested</div>
          <div className="mt-3 font-display text-3xl font-bold text-white">{stats.testedPatterns}</div>
        </div>
      </section>

      <section className="mt-16">
        <div className="mb-6 flex items-center justify-between gap-4">
          <div>
            <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">Categories</div>
            <h2 className="mt-2 font-display text-3xl font-bold text-white">Explore by pattern family</h2>
          </div>
          <Link to="/patterns" className="text-sm font-semibold text-slate-200 hover:text-white">
            See full catalog
          </Link>
        </div>

        <div className="grid gap-5 md:grid-cols-2 xl:grid-cols-5">
          {categories.map((category) => (
            <Link
              key={category.id}
              to={`/categories/${category.id}`}
              className={`rounded-[1.75rem] border border-white/10 bg-gradient-to-br ${category.accentClass} bg-white/[0.04] p-5 transition hover:-translate-y-1 hover:border-white/18`}
            >
              <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-200">{category.shortLabel}</div>
              <div className="mt-4 font-display text-2xl font-bold text-white">{category.label}</div>
              <p className="mt-3 text-sm leading-7 text-slate-300">{category.description}</p>
            </Link>
          ))}
        </div>
      </section>

      <section className="mt-16">
        <div className="mb-6">
          <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">Featured patterns</div>
          <h2 className="mt-2 font-display text-3xl font-bold text-white">Strong starting points</h2>
        </div>
        <SearchResults patterns={featuredPatterns} query={query} onSuggestionClick={onIntentClick} />
      </section>
    </div>
  );
}
