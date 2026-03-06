import { useDeferredValue, useEffect, useMemo, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import type { PatternRecord } from "../../types/pattern";
import { Button } from "../common/Button";

interface SearchCommandPaletteProps {
  isOpen: boolean;
  onClose: () => void;
  patterns: PatternRecord[];
  recentSearches: string[];
  onRememberSearch: (value: string) => void;
  search: {
    search: (query: string) => { pattern: PatternRecord; score: number }[];
    suggestions: (query: string) => string[];
  };
}

export function SearchCommandPalette(props: SearchCommandPaletteProps) {
  const { isOpen, onClose, patterns, recentSearches, onRememberSearch, search } = props;
  const [query, setQuery] = useState("");
  const [activeIndex, setActiveIndex] = useState(0);
  const inputRef = useRef<HTMLInputElement>(null);
  const navigate = useNavigate();
  const deferredQuery = useDeferredValue(query);

  const results = useMemo(() => {
    const entries = deferredQuery.trim()
      ? search.search(deferredQuery).slice(0, 8)
      : patterns
          .filter((pattern) => pattern.featured)
          .slice(0, 8)
          .map((pattern, index) => ({ pattern, score: 100 - index }));
    return entries;
  }, [deferredQuery, patterns, search]);

  const suggestions = useMemo(() => search.suggestions(deferredQuery), [deferredQuery, search]);

  useEffect(() => {
    if (!isOpen) {
      setQuery("");
      setActiveIndex(0);
      return;
    }
    inputRef.current?.focus();
  }, [isOpen]);

  useEffect(() => {
    setActiveIndex(0);
  }, [deferredQuery]);

  useEffect(() => {
    if (!isOpen) {
      return;
    }

    const handleKeyDown = (event: KeyboardEvent) => {
      if (event.key === "Escape") {
        onClose();
        return;
      }
      if (event.key === "ArrowDown") {
        event.preventDefault();
        setActiveIndex((value) => Math.min(value + 1, Math.max(results.length - 1, 0)));
      }
      if (event.key === "ArrowUp") {
        event.preventDefault();
        setActiveIndex((value) => Math.max(value - 1, 0));
      }
      if (event.key === "Enter" && results[activeIndex]) {
        event.preventDefault();
        onRememberSearch(query.trim());
        navigate(`/patterns/${results[activeIndex].pattern.slug}`);
        onClose();
      }
    };

    window.addEventListener("keydown", handleKeyDown);
    return () => window.removeEventListener("keydown", handleKeyDown);
  }, [activeIndex, navigate, onClose, onRememberSearch, query, results, isOpen]);

  if (!isOpen) {
    return null;
  }

  return (
    <div className="fixed inset-0 z-50 bg-slate-950/70 p-4 backdrop-blur-sm" role="dialog" aria-modal="true" aria-label="Search patterns">
      <div className="mx-auto max-w-3xl overflow-hidden rounded-[2rem] border border-white/10 bg-slate-950/95 shadow-2xl">
        <div className="border-b border-white/10 px-5 py-4">
          <input
            ref={inputRef}
            value={query}
            onChange={(event) => setQuery(event.target.value)}
            className="w-full bg-transparent text-lg text-slate-50 outline-none placeholder:text-slate-500"
            placeholder="Search patterns, use cases, aliases, or topics..."
            aria-label="Search patterns"
          />
        </div>

        <div className="grid gap-6 p-5 md:grid-cols-[1.7fr_0.9fr]">
          <div>
            <div className="mb-3 text-xs font-semibold uppercase tracking-[0.28em] text-cyan-300">Results</div>
            <div className="space-y-2">
              {results.map((entry, index) => (
                <button
                  key={entry.pattern.id}
                  type="button"
                  onMouseEnter={() => setActiveIndex(index)}
                  onClick={() => {
                    onRememberSearch(query.trim());
                    navigate(`/patterns/${entry.pattern.slug}`);
                    onClose();
                  }}
                  className={`flex w-full items-center justify-between rounded-2xl border px-4 py-3 text-left transition ${
                    index === activeIndex
                      ? "border-cyan-400/60 bg-cyan-400/10"
                      : "border-white/8 bg-white/[0.03] hover:border-white/20"
                  }`}
                >
                  <div>
                    <div className="font-display text-xl font-semibold text-slate-50">{entry.pattern.name}</div>
                    <div className="mt-1 text-sm text-slate-400">{entry.pattern.summary}</div>
                  </div>
                  <a
                    href={entry.pattern.githubUrl}
                    target="_blank"
                    rel="noreferrer"
                    className="rounded-full border border-white/12 px-3 py-1 text-xs font-semibold uppercase tracking-[0.16em] text-slate-300"
                    onClick={(event) => event.stopPropagation()}
                  >
                    GitHub
                  </a>
                </button>
              ))}
            </div>
          </div>

          <div className="space-y-5">
            <div>
              <div className="mb-3 text-xs font-semibold uppercase tracking-[0.28em] text-cyan-300">Suggestions</div>
              <div className="flex flex-wrap gap-2">
                {suggestions.map((suggestion) => (
                  <Button key={suggestion} variant="ghost" className="border border-white/10 text-slate-200 hover:bg-white/5" onClick={() => setQuery(suggestion)}>
                    {suggestion}
                  </Button>
                ))}
              </div>
            </div>

            <div>
              <div className="mb-3 text-xs font-semibold uppercase tracking-[0.28em] text-cyan-300">Recent searches</div>
              <div className="space-y-2">
                {recentSearches.length ? (
                  recentSearches.map((item) => (
                    <button
                      key={item}
                      type="button"
                      onClick={() => setQuery(item)}
                      className="block w-full rounded-xl border border-white/8 bg-white/[0.03] px-3 py-2 text-left text-sm text-slate-300 hover:border-white/20"
                    >
                      {item}
                    </button>
                  ))
                ) : (
                  <p className="text-sm text-slate-500">No recent searches yet.</p>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
