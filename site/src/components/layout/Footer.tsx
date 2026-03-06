import { contributionUrl, repositoryUrl } from "../../lib/github";
import { Button } from "../common/Button";

export function Footer() {
  return (
    <footer className="theme-header border-t">
      <div className="mx-auto grid max-w-7xl gap-6 px-4 py-10 md:grid-cols-[1.2fr_0.8fr] md:px-6">
        <div>
          <div className="theme-text-primary font-display text-2xl font-bold">Found this useful?</div>
          <p className="theme-text-secondary mt-3 max-w-2xl text-sm leading-7">
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
