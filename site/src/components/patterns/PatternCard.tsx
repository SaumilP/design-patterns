import { Link } from "react-router-dom";
import type { PatternRecord } from "../../types/pattern";
import { HighlightedText } from "../search/HighlightedText";
import { CategoryBadge } from "./CategoryBadge";

interface PatternCardProps {
  pattern: PatternRecord;
  query: string;
}

export function PatternCard({ pattern, query }: PatternCardProps) {
  return (
    <article className="group rounded-[1.75rem] border border-white/10 bg-white/[0.04] p-5 shadow-xl transition hover:-translate-y-1 hover:border-cyan-400/35 hover:bg-white/[0.06]">
      <div className="mb-4 flex items-center justify-between gap-4">
        <CategoryBadge category={pattern.category} />
        {pattern.subcategory ? (
          <span className="font-mono text-[11px] uppercase tracking-[0.22em] text-slate-500">{pattern.subcategory}</span>
        ) : null}
      </div>
      <h3 className="font-display text-2xl font-bold text-slate-50">
        <HighlightedText text={pattern.name} query={query} />
      </h3>
      <p className="mt-3 text-sm leading-7 text-slate-400">
        <HighlightedText text={pattern.summary} query={query} />
      </p>
      <div className="mt-4 flex flex-wrap gap-2">
        {pattern.tags.slice(0, 4).map((tag) => (
          <span key={tag} className="rounded-full border border-white/8 bg-slate-900/70 px-2.5 py-1 text-xs text-slate-400">
            {tag}
          </span>
        ))}
      </div>
      <div className="mt-5 flex items-center gap-3">
        <Link
          to={`/patterns/${pattern.slug}`}
          className="rounded-full border border-cyan-400/30 bg-cyan-400/14 px-4 py-2 text-sm font-semibold text-cyan-100 transition hover:border-cyan-300 hover:bg-cyan-400/22"
        >
          View details
        </Link>
        <a
          href={pattern.githubUrl}
          target="_blank"
          rel="noreferrer"
          className="rounded-full border border-white/10 px-4 py-2 text-sm font-semibold text-slate-200 transition hover:border-white/20"
        >
          View on GitHub
        </a>
      </div>
    </article>
  );
}
