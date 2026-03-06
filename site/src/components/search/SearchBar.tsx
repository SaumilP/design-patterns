interface SearchBarProps {
  query: string;
  onQueryChange: (value: string) => void;
  onOpenPalette?: () => void;
}

export function SearchBar({ query, onQueryChange, onOpenPalette }: SearchBarProps) {
  return (
    <div className="theme-panel rounded-[1.6rem] border p-2 shadow-2xl">
      <label className="theme-input flex items-center gap-3 rounded-[1.3rem] px-4 py-3">
        <span className="font-mono text-xs uppercase tracking-[0.26em] text-cyan-300">Search</span>
        <input
          value={query}
          onChange={(event) => onQueryChange(event.target.value)}
          placeholder="Search patterns, use cases, aliases, or topics..."
          className="theme-text-primary min-w-0 flex-1 bg-transparent text-base outline-none placeholder:text-[var(--text-muted)]"
          aria-label="Search patterns"
        />
        {onOpenPalette ? (
          <button
            type="button"
            onClick={onOpenPalette}
            className="theme-text-secondary rounded-full border border-[color:var(--border)] px-3 py-1 text-xs font-semibold uppercase tracking-[0.18em] hover:border-[color:var(--border-strong)]"
          >
            Ctrl+K
          </button>
        ) : null}
      </label>
    </div>
  );
}
