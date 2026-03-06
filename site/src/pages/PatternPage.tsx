import { useEffect, useRef } from "react";
import type { PatternRecord } from "../types/pattern";
import { categoryMap } from "../data/categories";
import { contributionUrl } from "../lib/github";
import { summarize } from "../lib/markdown";
import { useSeo } from "../lib/seo";
import { Button } from "../components/common/Button";
import { CategoryBadge } from "../components/patterns/CategoryBadge";
import { RelatedPatterns } from "../components/patterns/RelatedPatterns";

export function PatternPage(props: { pattern: PatternRecord; related: PatternRecord[]; theme?: "dark" | "light" }) {
  const { pattern, related } = props;
  const category = categoryMap[pattern.category];
  const articleRef = useRef<HTMLDivElement>(null);

  useSeo({
    title: pattern.name,
    description: pattern.summary || summarize(pattern.contentHtml ?? "", 170),
    path: `/patterns/${pattern.slug}`,
  });

  useEffect(() => {
    let isActive = true;

    function scaleDiagram(node: HTMLElement) {
      const svg = node.querySelector("svg");
      if (!svg) {
        return;
      }

      const contentGroup =
        svg.querySelector("g.output") ||
        svg.querySelector("g") ||
        svg;

      let bounds: DOMRect | null = null;
      if ("getBBox" in contentGroup) {
        try {
          const box = (contentGroup as SVGGraphicsElement).getBBox();
          if (box.width > 0 && box.height > 0) {
            svg.setAttribute("viewBox", `${box.x} ${box.y} ${box.width} ${box.height}`);
            bounds = new DOMRect(box.x, box.y, box.width, box.height);
          }
        } catch {
          bounds = null;
        }
      }

      const viewBox = svg.getAttribute("viewBox");
      if (!viewBox && !bounds) {
        return;
      }

      const [, , rawWidth, rawHeight] = (viewBox ?? "").split(/\s+/).map(Number);
      if (!rawWidth || !rawHeight) {
        return;
      }

      const containerWidth = Math.max(node.clientWidth - 16, 260);
      const maxWidth = Math.min(containerWidth, 900);
      const maxHeight = Math.min(window.innerHeight * 0.52, 420);
      let scale = Math.min(maxWidth / rawWidth, maxHeight / rawHeight, 1);

      const aspectRatio = rawHeight / rawWidth;
      if (aspectRatio > 1.12) {
        scale = Math.min(scale, maxHeight / rawHeight, 0.82);
      }

      if (aspectRatio > 1.45) {
        scale = Math.min(scale, 0.68);
      }

      svg.setAttribute("width", `${rawWidth * scale}`);
      svg.setAttribute("height", `${rawHeight * scale}`);
      svg.setAttribute("preserveAspectRatio", "xMidYMid meet");
      svg.style.width = `${rawWidth * scale}px`;
      svg.style.height = `${rawHeight * scale}px`;
      svg.style.maxWidth = "100%";
      svg.style.display = "block";
      svg.style.margin = "0 auto";

      node.style.maxHeight = `${maxHeight + 28}px`;
    }

    async function renderMermaid() {
      const root = articleRef.current;
      if (!root) {
        return;
      }

      const diagrams = Array.from(root.querySelectorAll<HTMLElement>(".mermaid"));
      if (!diagrams.length) {
        return;
      }

      const mermaid = (await import("mermaid")).default;
      mermaid.initialize({
        startOnLoad: false,
        securityLevel: "loose",
        theme: document.documentElement.dataset.theme === "light" ? "default" : "dark",
      });

      for (const [index, node] of diagrams.entries()) {
        const source = node.textContent ?? "";
        try {
          const { svg } = await mermaid.render(`mermaid-${pattern.id}-${index}`, source);
          if (!isActive) {
            return;
          }
          node.innerHTML = svg;
          node.dataset.processed = "true";
          scaleDiagram(node);
        } catch (error) {
          node.innerHTML = `<pre class="code-block"><div class="code-block__label">mermaid</div><code>${source.replace(/[<>&]/g, (char) => ({ "<": "&lt;", ">": "&gt;", "&": "&amp;" }[char]))}</code></pre>`;
        }
      }
    }

    void renderMermaid();

    const onResize = () => {
      const root = articleRef.current;
      if (!root) {
        return;
      }
      root.querySelectorAll<HTMLElement>(".mermaid").forEach(scaleDiagram);
    };
    window.addEventListener("resize", onResize);

    return () => {
      isActive = false;
      window.removeEventListener("resize", onResize);
    };
  }, [pattern.contentHtml, pattern.id, props.theme]);

  return (
    <div className="mx-auto grid max-w-7xl gap-6 px-4 py-8 md:px-6 md:py-10 lg:grid-cols-[minmax(0,1fr)_330px] lg:gap-8">
      <article className="theme-panel order-2 rounded-[2rem] border p-5 shadow-2xl sm:p-6 md:p-8 lg:order-1">
        {pattern.previewImage ? (
          <div
            className={`relative -mx-5 -mt-5 mb-6 overflow-hidden rounded-t-[2rem] border-b border-[color:var(--border)] bg-gradient-to-br sm:-mx-6 sm:-mt-6 md:-mx-8 md:-mt-8 ${category.cardGradient}`}
          >
            <img
              src={`${import.meta.env.BASE_URL}${pattern.previewImage.replace(/^\//, "")}`}
              alt={`${pattern.name} preview banner`}
              className="h-48 w-full object-cover sm:h-64 md:h-80"
            />
            <div className="absolute inset-0 bg-gradient-to-t from-black/12 via-transparent to-transparent" />
            <div className="absolute bottom-4 right-4 sm:bottom-5 sm:right-5">
              <CategoryBadge category={pattern.category} />
            </div>
          </div>
        ) : null}
        <div className="flex flex-wrap items-center gap-3">
          {pattern.subcategory ? (
            <span className="theme-panel-soft theme-text-muted rounded-full border px-3 py-1 font-mono text-xs uppercase tracking-[0.18em]">
              {pattern.subcategory}
            </span>
          ) : null}
          {pattern.hasTests ? (
            <span className="rounded-full border border-emerald-400/20 bg-emerald-400/10 px-3 py-1 font-mono text-xs uppercase tracking-[0.18em] text-emerald-200">
              Has tests
            </span>
          ) : null}
        </div>

        <h1 className="theme-text-primary mt-5 font-display text-3xl font-bold tracking-tight sm:text-4xl md:text-5xl">
          {pattern.name}
        </h1>
        <p className="theme-text-secondary mt-4 max-w-3xl text-base leading-7 sm:text-lg sm:leading-8">{pattern.summary}</p>

        <div className="mt-6 flex flex-col gap-3 sm:flex-row sm:flex-wrap">
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

        <div
          ref={articleRef}
          className="prose-shell mt-10"
          dangerouslySetInnerHTML={{ __html: pattern.contentHtml ?? "" }}
        />
      </article>

      <aside className="order-1 space-y-6 lg:order-2">
        <section className="theme-panel rounded-[1.75rem] border p-5">
          <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">Metadata</div>
          <div className="mt-4 space-y-5 text-sm">
            <div>
              <div className="theme-text-primary font-semibold">Intent</div>
              <p className="theme-text-secondary mt-2 leading-7">{pattern.intent ?? pattern.summary}</p>
            </div>

            {pattern.problem ? (
              <div>
                <div className="theme-text-primary font-semibold">Problem</div>
                <p className="theme-text-secondary mt-2 leading-7">{pattern.problem}</p>
              </div>
            ) : null}

            {pattern.applicability?.length ? (
              <div>
                <div className="theme-text-primary font-semibold">Applicability</div>
                <ul className="theme-text-secondary mt-2 space-y-2">
                  {pattern.applicability.map((item) => (
                    <li key={item}>{item}</li>
                  ))}
                </ul>
              </div>
            ) : null}

            {pattern.tradeOffs?.length ? (
              <div>
                <div className="theme-text-primary font-semibold">Trade-offs</div>
                <ul className="theme-text-secondary mt-2 space-y-2">
                  {pattern.tradeOffs.map((item) => (
                    <li key={item}>{item}</li>
                  ))}
                </ul>
              </div>
            ) : null}

            {pattern.alternatives?.length ? (
              <div>
                <div className="theme-text-primary font-semibold">Alternatives</div>
                <ul className="theme-text-secondary mt-2 space-y-2">
                  {pattern.alternatives.map((item) => (
                    <li key={item}>{item}</li>
                  ))}
                </ul>
              </div>
            ) : null}

            <div>
              <div className="theme-text-primary font-semibold">Tags</div>
              <div className="mt-2 flex flex-wrap gap-2">
                {pattern.tags.map((tag) => (
                  <span key={tag} className="theme-tag rounded-full border px-2.5 py-1 text-xs">
                    {tag}
                  </span>
                ))}
              </div>
            </div>

            <div>
              <div className="theme-text-primary font-semibold">Reading time</div>
              <p className="theme-text-secondary mt-2">{pattern.readingTimeMinutes} min</p>
            </div>
          </div>
        </section>

        <RelatedPatterns items={related} />

        <section className="theme-panel rounded-[1.75rem] border p-5">
          <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">Contribute</div>
          <p className="theme-text-secondary mt-3 text-sm leading-7">
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
