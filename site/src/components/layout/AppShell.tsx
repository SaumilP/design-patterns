import type { ReactNode } from "react";
import { Footer } from "./Footer";
import { Header } from "./Header";

interface AppShellProps {
  children: ReactNode;
  onOpenSearch: () => void;
  theme: "dark" | "light";
  onToggleTheme: () => void;
}

export function AppShell({ children, onOpenSearch, theme, onToggleTheme }: AppShellProps) {
  return (
    <div className="theme-shell min-h-screen">
      <a
        href="#main-content"
        className="sr-only rounded-full bg-cyan-300 px-4 py-2 text-slate-950 focus:not-sr-only focus:absolute focus:left-4 focus:top-4 focus:z-50"
      >
        Skip to content
      </a>
      <div className="theme-grid fixed inset-0 -z-10 bg-[size:52px_52px] opacity-25" />
      <Header onOpenSearch={onOpenSearch} theme={theme} onToggleTheme={onToggleTheme} />
      <main id="main-content">{children}</main>
      <Footer />
    </div>
  );
}
