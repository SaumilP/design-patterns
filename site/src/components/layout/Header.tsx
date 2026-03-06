import { useEffect, useState } from "react";
import { Link, NavLink } from "react-router-dom";
import { repositoryUrl } from "../../lib/github";
import { Button } from "../common/Button";

interface HeaderProps {
  onOpenSearch: () => void;
}

export function Header({ onOpenSearch }: HeaderProps) {
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
    <header className={`sticky top-0 z-40 border-b border-white/8 bg-slate-950/82 backdrop-blur-xl transition ${isCompact ? "shadow-2xl" : ""}`}>
      <div className={`mx-auto flex max-w-7xl items-center justify-between gap-4 px-4 transition-all md:px-6 ${isCompact ? "py-3" : "py-4"}`}>
        <Link to="/" className="flex items-center gap-3">
          <img src={`${import.meta.env.BASE_URL}logo-icon.svg`} alt="" className="h-11 w-11" />
          <div>
            <div className="font-display text-sm font-semibold uppercase tracking-[0.3em] text-cyan-300">Pattern Hex</div>
            <div className="text-xs text-slate-400">Practical Java Design Patterns</div>
          </div>
        </Link>

        <nav className="hidden items-center gap-6 md:flex">
          {links.map((item) => (
            <NavLink
              key={item.label}
              to={item.to}
              className={({ isActive }) =>
                isActive ? "text-sm font-semibold text-slate-50" : "text-sm font-medium text-slate-400 hover:text-slate-50"
              }
            >
              {item.label}
            </NavLink>
          ))}
          <button
            type="button"
            onClick={onOpenSearch}
            className="rounded-full border border-white/12 bg-white/[0.04] px-4 py-2 text-sm font-medium text-slate-200 transition hover:border-white/24 hover:bg-white/[0.08]"
            aria-label="Open search palette"
          >
            Search
            <span className="ml-2 rounded-md border border-white/10 px-2 py-0.5 font-mono text-xs text-slate-400">Ctrl K</span>
          </button>
          <a href={repositoryUrl} target="_blank" rel="noreferrer" className="text-sm font-medium text-slate-400 hover:text-slate-50">
            GitHub
          </a>
          <Button href={repositoryUrl} target="_blank" rel="noreferrer">
            Star on GitHub
          </Button>
        </nav>

        <button
          type="button"
          onClick={() => setIsMobileOpen((value) => !value)}
          className="rounded-full border border-white/12 p-3 text-slate-100 md:hidden"
          aria-label="Toggle navigation menu"
        >
          {isMobileOpen ? "Close" : "Menu"}
        </button>
      </div>

      {isMobileOpen ? (
        <div className="border-t border-white/8 bg-slate-950 px-4 py-4 md:hidden">
          <nav className="grid gap-3">
            {links.map((item) => (
              <NavLink
                key={item.label}
                to={item.to}
                onClick={() => setIsMobileOpen(false)}
                className="rounded-2xl border border-white/8 bg-white/[0.03] px-4 py-3 text-sm font-medium text-slate-200"
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
              className="rounded-2xl border border-white/8 bg-white/[0.03] px-4 py-3 text-left text-sm font-medium text-slate-200"
            >
              Search
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
