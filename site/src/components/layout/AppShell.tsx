import type { ReactNode } from "react";
import { Footer } from "./Footer";
import { Header } from "./Header";

interface AppShellProps {
  children: ReactNode;
  onOpenSearch: () => void;
}

export function AppShell({ children, onOpenSearch }: AppShellProps) {
  return (
    <div className="min-h-screen bg-[radial-gradient(circle_at_top,_rgba(34,211,238,.18),_transparent_22%),radial-gradient(circle_at_80%_20%,_rgba(79,70,229,.18),_transparent_24%),linear-gradient(180deg,_#020617_0%,_#020617_55%,_#050b16_100%)] text-slate-100">
      <a
        href="#main-content"
        className="sr-only rounded-full bg-cyan-300 px-4 py-2 text-slate-950 focus:not-sr-only focus:absolute focus:left-4 focus:top-4 focus:z-50"
      >
        Skip to content
      </a>
      <div className="fixed inset-0 -z-10 bg-[linear-gradient(rgba(148,163,184,.08)_1px,transparent_1px),linear-gradient(90deg,rgba(148,163,184,.08)_1px,transparent_1px)] bg-[size:52px_52px] opacity-25" />
      <Header onOpenSearch={onOpenSearch} />
      <main id="main-content">{children}</main>
      <Footer />
    </div>
  );
}
