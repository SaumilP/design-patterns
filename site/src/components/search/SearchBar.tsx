interface SearchBarProps {
  query: string;
  onQueryChange: (value: string) => void;
  onOpenPalette?: () => void;
}

export function SearchBar({ query, onQueryChange, onOpenPalette }: SearchBarProps) {
  return (
    <div className="rounded-[1.6rem] border border-white/10 bg-white/[0.04] p-2 shadow-2xl">
      <label className="flex items-center gap-3 rounded-[1.3rem] bg-slate-950/70 px-4 py-3">
        <span className="font-mono text-xs uppercase tracking-[0.26em] text-cyan-300">Search</span>
        <input
          value={query}
          onChange={(event) => onQueryChange(event.target.value)}
          placeholder="Search patterns, use cases, aliases, or topics..."
          className="min-w-0 flex-1 bg-transparent text-base text-slate-50 outline-none placeholder:text-slate-500"
          aria-label="Search patterns"
        />
        {onOpenPalette ? (
          <button
            type="button"
            onClick={onOpenPalette}
            className="rounded-full border border-white/12 px-3 py-1 text-xs font-semibold uppercase tracking-[0.18em] text-slate-300 hover:border-white/24"
          >
            Ctrl+K
          </button>
        ) : null}
      </label>
    </div>
  );
}
