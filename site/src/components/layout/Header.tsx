import { useEffect, useState } from "react";
import { Link, NavLink } from "react-router-dom";
import { repositoryUrl } from "../../lib/github";
import { Button } from "../common/Button";

interface HeaderProps {
  onOpenSearch: () => void;
  theme: "dark" | "light";
  themeMode: "auto" | "dark" | "light";
  onToggleTheme: () => void;
}

export function Header({ onOpenSearch, theme, themeMode, onToggleTheme }: HeaderProps) {
  const [isCompact, setIsCompact] = useState(false);
  const [isMobileOpen, setIsMobileOpen] = useState(false);

  useEffect(() => {
    const onScroll = () => setIsCompact(window.scrollY > 18);
    onScroll();
    window.addEventListener("scroll", onScroll);
    return () => window.removeEventListener("scroll", onScroll);
  }, []);

  const links = [
    { to: "/", label: "Home" },
    { to: "/patterns", label: "Explore" },
    { to: "/categories/gof", label: "Categories" },
    { to: "/categories/reliability", label: "Reliability" },
    { to: "/about", label: "About" },
  ];

  return (
    <header className={`theme-header sticky top-0 z-40 border-b backdrop-blur-xl transition ${isCompact ? "shadow-2xl" : ""}`}>
      <div className={`mx-auto flex max-w-7xl items-center justify-between gap-4 px-4 transition-all md:px-6 ${isCompact ? "py-3" : "py-4"}`}>
        <Link to="/" className="flex items-center gap-3">
          <img src={`${import.meta.env.BASE_URL}logo-icon.svg`} alt="Pattern Hex logo" className="h-11 w-11" />
          <div>
            <div className="font-display text-sm font-semibold uppercase tracking-[0.3em] text-cyan-300">Pattern Hex</div>
            <div className="theme-text-muted text-xs">Practical Java Design Patterns</div>
          </div>
        </Link>

        <nav className="hidden items-center gap-6 md:flex">
          {links.map((item) => (
            <NavLink
              key={item.label}
              to={item.to}
              className={({ isActive }) =>
                isActive ? "theme-text-primary text-sm font-semibold" : "theme-text-muted text-sm font-medium hover:text-[var(--text-primary)]"
              }
            >
              {item.label}
            </NavLink>
          ))}
          <button
            type="button"
            onClick={onOpenSearch}
            className="theme-panel-soft theme-text-secondary rounded-full border px-4 py-2 text-sm font-medium transition hover:bg-[var(--panel)]"
            aria-label="Open search palette"
          >
            Search
            <span className="ml-2 rounded-md border border-[color:var(--border)] px-2 py-0.5 font-mono text-xs theme-text-muted">Ctrl K</span>
          </button>
          <button
            type="button"
            onClick={onToggleTheme}
            className="theme-panel-soft theme-text-secondary rounded-full border px-4 py-2 text-sm font-medium transition hover:bg-[var(--panel)]"
            aria-label={`Theme: ${themeMode}`}
          >
            Theme: {themeMode === "auto" ? `Auto (${theme})` : themeMode}
          </button>
          <a href={repositoryUrl} target="_blank" rel="noreferrer" className="theme-text-muted text-sm font-medium hover:text-[var(--text-primary)]">
            GitHub
          </a>
          <Button href={repositoryUrl} target="_blank" rel="noreferrer">
            Star on GitHub
          </Button>
        </nav>

        <button
          type="button"
          onClick={() => setIsMobileOpen((value) => !value)}
          className="theme-panel-soft theme-text-primary rounded-full border p-3 md:hidden"
          aria-label="Toggle navigation menu"
        >
          {isMobileOpen ? "Close" : "Menu"}
        </button>
      </div>

      {isMobileOpen ? (
        <div className="theme-header border-t px-4 py-4 md:hidden">
          <nav className="grid gap-3">
            {links.map((item) => (
              <NavLink
                key={item.label}
                to={item.to}
                onClick={() => setIsMobileOpen(false)}
                className="theme-panel-soft theme-text-primary rounded-2xl border px-4 py-3 text-sm font-medium"
              >
                {item.label}
              </NavLink>
            ))}
            <button
              type="button"
              onClick={() => {
                setIsMobileOpen(false);
                onOpenSearch();
              }}
              className="theme-panel-soft theme-text-primary rounded-2xl border px-4 py-3 text-left text-sm font-medium"
            >
              Search
            </button>
            <button
              type="button"
              onClick={onToggleTheme}
              className="theme-panel-soft theme-text-primary rounded-2xl border px-4 py-3 text-left text-sm font-medium"
            >
              Theme: {themeMode === "auto" ? `Auto (${theme})` : themeMode}
            </button>
            <Button href={repositoryUrl} target="_blank" rel="noreferrer">
              Star on GitHub
            </Button>
          </nav>
        </div>
      ) : null}
    </header>
  );
}
