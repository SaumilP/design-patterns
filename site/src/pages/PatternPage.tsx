import type { PatternRecord } from "../types/pattern";
import { contributionUrl } from "../lib/github";
import { summarize } from "../lib/markdown";
import { useSeo } from "../lib/seo";
import { Button } from "../components/common/Button";
import { CategoryBadge } from "../components/patterns/CategoryBadge";
import { RelatedPatterns } from "../components/patterns/RelatedPatterns";

export function PatternPage(props: { pattern: PatternRecord; related: PatternRecord[] }) {
  const { pattern, related } = props;

  useSeo({
    title: pattern.name,
    description: pattern.summary || summarize(pattern.contentHtml ?? "", 170),
    path: `/patterns/${pattern.slug}`,
  });

  return (
    <div className="mx-auto grid max-w-7xl gap-8 px-4 py-10 md:px-6 lg:grid-cols-[minmax(0,1fr)_330px]">
      <article className="rounded-[2rem] border border-white/10 bg-white/[0.04] p-6 shadow-2xl md:p-8">
        <div className="flex flex-wrap items-center gap-3">
          <CategoryBadge category={pattern.category} />
          {pattern.subcategory ? (
            <span className="rounded-full border border-white/10 bg-white/[0.03] px-3 py-1 font-mono text-xs uppercase tracking-[0.18em] text-slate-400">
              {pattern.subcategory}
            </span>
          ) : null}
          {pattern.hasTests ? (
            <span className="rounded-full border border-emerald-400/20 bg-emerald-400/10 px-3 py-1 font-mono text-xs uppercase tracking-[0.18em] text-emerald-200">
              Has tests
            </span>
          ) : null}
        </div>

        <h1 className="mt-5 font-display text-4xl font-bold tracking-tight text-white md:text-5xl">
          {pattern.name}
        </h1>
        <p className="mt-4 max-w-3xl text-lg leading-8 text-slate-300">{pattern.summary}</p>

        <div className="mt-6 flex flex-wrap gap-3">
          <Button href={pattern.githubUrl} target="_blank" rel="noreferrer">
            Open on GitHub
          </Button>
          <Button
            href={`https://github.com/SaumilP/design-patterns/edit/main/${pattern.githubPath}/README.md`}
            target="_blank"
            rel="noreferrer"
            variant="secondary"
          >
            Edit this pattern
          </Button>
        </div>

        <div className="prose-shell mt-10" dangerouslySetInnerHTML={{ __html: pattern.contentHtml ?? "" }} />
      </article>

      <aside className="space-y-6">
        <section className="rounded-[1.75rem] border border-white/10 bg-white/[0.04] p-5">
          <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">Metadata</div>
          <div className="mt-4 space-y-5 text-sm text-slate-300">
            <div>
              <div className="font-semibold text-slate-100">Intent</div>
              <p className="mt-2 leading-7 text-slate-400">{pattern.intent ?? pattern.summary}</p>
            </div>

            {pattern.problem ? (
              <div>
                <div className="font-semibold text-slate-100">Problem</div>
                <p className="mt-2 leading-7 text-slate-400">{pattern.problem}</p>
              </div>
            ) : null}

            {pattern.applicability?.length ? (
              <div>
                <div className="font-semibold text-slate-100">Applicability</div>
                <ul className="mt-2 space-y-2 text-slate-400">
                  {pattern.applicability.map((item) => (
                    <li key={item}>{item}</li>
                  ))}
                </ul>
              </div>
            ) : null}

            {pattern.tradeOffs?.length ? (
              <div>
                <div className="font-semibold text-slate-100">Trade-offs</div>
                <ul className="mt-2 space-y-2 text-slate-400">
                  {pattern.tradeOffs.map((item) => (
                    <li key={item}>{item}</li>
                  ))}
                </ul>
              </div>
            ) : null}

            {pattern.alternatives?.length ? (
              <div>
                <div className="font-semibold text-slate-100">Alternatives</div>
                <ul className="mt-2 space-y-2 text-slate-400">
                  {pattern.alternatives.map((item) => (
                    <li key={item}>{item}</li>
                  ))}
                </ul>
              </div>
            ) : null}

            <div>
              <div className="font-semibold text-slate-100">Tags</div>
              <div className="mt-2 flex flex-wrap gap-2">
                {pattern.tags.map((tag) => (
                  <span key={tag} className="rounded-full border border-white/8 bg-slate-900/70 px-2.5 py-1 text-xs text-slate-400">
                    {tag}
                  </span>
                ))}
              </div>
            </div>

            <div>
              <div className="font-semibold text-slate-100">Reading time</div>
              <p className="mt-2 text-slate-400">{pattern.readingTimeMinutes} min</p>
            </div>
          </div>
        </section>

        <RelatedPatterns items={related} />

        <section className="rounded-[1.75rem] border border-white/10 bg-white/[0.04] p-5">
          <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">Contribute</div>
          <p className="mt-3 text-sm leading-7 text-slate-400">
            Improve examples, clarify trade-offs, or add frontmatter to sharpen search quality over time.
          </p>
          <div className="mt-4">
            <Button href={contributionUrl} target="_blank" rel="noreferrer" variant="secondary">
              Contribution guide
            </Button>
          </div>
        </section>
      </aside>
    </div>
  );
}
