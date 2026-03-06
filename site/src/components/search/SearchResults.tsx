import type { PatternRecord } from "../../types/pattern";
import { intentChips } from "../../lib/search";
import { PatternCard } from "../patterns/PatternCard";

export function SearchResults(props: {
  patterns: PatternRecord[];
  query: string;
  onSuggestionClick?: (value: string) => void;
}) {
  const { patterns, query, onSuggestionClick } = props;

  if (!patterns.length) {
    return (
      <div className="theme-panel-soft rounded-[1.75rem] border border-dashed p-10 text-center">
        <div className="theme-text-primary font-display text-2xl font-bold">No exact matches</div>
        <p className="theme-text-secondary mt-3 text-sm leading-7">
          Try a broader term, switch categories, or jump to one of these search prompts.
        </p>
        {onSuggestionClick ? (
          <div className="mt-5 flex flex-wrap justify-center gap-2">
            {intentChips.map((chip) => (
              <button
                key={chip}
                type="button"
                onClick={() => onSuggestionClick(chip)}
                className="theme-panel rounded-full border px-4 py-2 text-sm font-medium theme-text-primary"
              >
                {chip}
              </button>
            ))}
          </div>
        ) : null}
      </div>
    );
  }

  return (
    <div className="grid gap-5 md:grid-cols-2 xl:grid-cols-3">
      {patterns.map((pattern) => (
        <PatternCard key={pattern.id} pattern={pattern} query={query} />
      ))}
    </div>
  );
}
