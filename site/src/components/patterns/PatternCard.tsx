import { Link } from "react-router-dom";
import { categoryMap } from "../../data/categories";
import type { PatternRecord } from "../../types/pattern";
import { HighlightedText } from "../search/HighlightedText";
import { CategoryBadge } from "./CategoryBadge";

interface PatternCardProps {
  pattern: PatternRecord;
  query: string;
}

export function PatternCard({ pattern, query }: PatternCardProps) {
  const category = categoryMap[pattern.category];
  const headingId = `pattern-card-${pattern.id}`;

  return (
    <article className="theme-panel group relative rounded-[1.75rem] border p-5 shadow-xl transition hover:-translate-y-1 hover:border-cyan-400/35">
      <Link
        to={`/patterns/${pattern.slug}`}
        aria-labelledby={headingId}
        className="absolute inset-0 z-0 rounded-[1.75rem] focus-visible:outline-none"
      >
        <span className="sr-only">Open {pattern.name} details</span>
      </Link>
      <div className={`mb-5 overflow-hidden rounded-[1.25rem] border border-black/5 bg-gradient-to-br ${category.cardGradient}`}>
        {pattern.previewThumbnailImage ? (
          <img
            src={`${import.meta.env.BASE_URL}${pattern.previewThumbnailImage.replace(/^\//, "")}`}
            alt={`${pattern.name} preview`}
            className="h-32 w-full object-cover"
            loading="lazy"
          />
        ) : null}
      </div>
      <div className="relative z-10 mb-4 flex items-center justify-between gap-4">
        <CategoryBadge category={pattern.category} />
        {pattern.subcategory ? (
          <span className="theme-text-muted font-mono text-[11px] uppercase tracking-[0.22em]">{pattern.subcategory}</span>
        ) : null}
      </div>
      <h3 id={headingId} className="theme-text-primary relative z-10 font-display text-2xl font-bold">
        <HighlightedText text={pattern.name} query={query} />
      </h3>
      <p className="theme-text-secondary relative z-10 mt-3 text-sm leading-7">
        <HighlightedText text={pattern.summary} query={query} />
      </p>
      <div className="relative z-10 mt-4 flex flex-wrap gap-2">
        {pattern.tags.slice(0, 4).map((tag) => (
          <span key={tag} className="theme-tag rounded-full border px-2.5 py-1 text-xs">
            {tag}
          </span>
        ))}
      </div>
      <div className="relative z-10 mt-5 flex flex-col gap-3 sm:flex-row sm:items-center">
        <span className="rounded-full border border-cyan-400/30 bg-cyan-400/14 px-4 py-2 text-center text-sm font-semibold text-cyan-100 transition group-hover:border-cyan-300 group-hover:bg-cyan-400/22">
          View details
        </span>
        <a
          href={pattern.githubUrl}
          target="_blank"
          rel="noreferrer"
          className="theme-text-primary rounded-full border border-[color:var(--border)] px-4 py-2 text-center text-sm font-semibold transition hover:border-[color:var(--border-strong)]"
        >
          View on GitHub
        </a>
      </div>
    </article>
  );
}
