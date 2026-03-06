import { contributionUrl, repositoryUrl } from "../../lib/github";
import { Button } from "../common/Button";

export function Footer() {
  return (
    <footer className="border-t border-white/8 bg-slate-950">
      <div className="mx-auto grid max-w-7xl gap-6 px-4 py-10 md:grid-cols-[1.2fr_0.8fr] md:px-6">
        <div>
          <div className="font-display text-2xl font-bold text-slate-50">Found this useful?</div>
          <p className="mt-3 max-w-2xl text-sm leading-7 text-slate-400">
            The catalog is generated from the repository source folders. Star the repo, review the examples, or send a documentation improvement directly from the pattern page.
          </p>
        </div>
        <div className="flex flex-wrap items-start gap-3 md:justify-end">
          <Button href={repositoryUrl} target="_blank" rel="noreferrer">
            Star the repo
          </Button>
          <Button href={contributionUrl} target="_blank" rel="noreferrer" variant="secondary">
            Contribution guide
          </Button>
        </div>
      </div>
    </footer>
  );
}
