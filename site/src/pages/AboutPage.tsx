import { contributionUrl, repositoryUrl } from "../lib/github";
import { useSeo } from "../lib/seo";
import { Button } from "../components/common/Button";

export function AboutPage() {
  useSeo({
    title: "About",
    description: "About the Design Patterns in Java catalog and repository.",
    path: "/about",
  });

  return (
    <div className="mx-auto max-w-5xl px-4 py-10 md:px-6 md:py-16">
      <div className="rounded-[2rem] border border-white/10 bg-white/[0.04] p-8 shadow-2xl">
        <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">About</div>
        <h1 className="mt-3 font-display text-4xl font-bold text-white">Repository-first documentation</h1>
        <p className="mt-5 max-w-3xl text-lg leading-8 text-slate-300">
          This site treats the repository folders as the content source. Every card and detail page is generated from the pattern README files, with no backend and no duplicated site-specific content to maintain.
        </p>
        <div className="mt-8 flex flex-wrap gap-3">
          <Button href={repositoryUrl} target="_blank" rel="noreferrer">
            Open repository
          </Button>
          <Button href={contributionUrl} target="_blank" rel="noreferrer" variant="secondary">
            Contributing
          </Button>
        </div>
      </div>
    </div>
  );
}
