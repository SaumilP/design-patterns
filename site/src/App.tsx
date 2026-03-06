import { useDeferredValue, useEffect, useMemo, useState } from "react";
import { Navigate, Route, Routes, useLocation, useNavigate, useParams } from "react-router-dom";
import patternsUrl from "./content/patterns.json?url";
import relatedUrl from "./content/related-patterns.json?url";
import { AppShell } from "./components/layout/AppShell";
import { SearchCommandPalette } from "./components/search/SearchCommandPalette";
import { createPatternSearch } from "./lib/search";
import { AboutPage } from "./pages/AboutPage";
import { CatalogPage } from "./pages/CatalogPage";
import { HomePage } from "./pages/HomePage";
import { NotFoundPage } from "./pages/NotFoundPage";
import { PatternPage } from "./pages/PatternPage";
import type { PatternCategory, PatternRecord, RelatedPatternMap } from "./types/pattern";

function useRecentSearches() {
  const [recentSearches, setRecentSearches] = useState<string[]>([]);

  useEffect(() => {
    const saved = window.localStorage.getItem("pattern-recent-searches");
    if (saved) {
      setRecentSearches(JSON.parse(saved) as string[]);
    }
  }, []);

  const remember = (value: string) => {
    if (!value.trim()) {
      return;
    }
    const next = [value, ...recentSearches.filter((item) => item !== value)].slice(0, 6);
    setRecentSearches(next);
    window.localStorage.setItem("pattern-recent-searches", JSON.stringify(next));
  };

  return { recentSearches, remember };
}

function useCatalogState() {
  const location = useLocation();
  const navigate = useNavigate();
  const params = new URLSearchParams(location.search);
  const query = params.get("q") ?? "";
  const categoryParam = params.get("category") as PatternCategory | null;

  const setQuery = (value: string, category = categoryParam ?? "all") => {
    const nextParams = new URLSearchParams();
    if (value.trim()) nextParams.set("q", value);
    if (category !== "all") nextParams.set("category", category);
    navigate({ pathname: "/patterns", search: nextParams.toString() }, { replace: true });
  };

  const setCategory = (value: PatternCategory | "all") => setQuery(query, value);

  return {
    query,
    category: categoryParam ?? "all",
    setQuery,
    setCategory,
  };
}

function CatalogRoute(props: { patterns: PatternRecord[]; onOpenPalette: () => void }) {
  const { patterns, onOpenPalette } = props;
  const { query, category, setQuery, setCategory } = useCatalogState();
  const deferredQuery = useDeferredValue(query);
  const search = useMemo(() => createPatternSearch(patterns), [patterns]);

  const filtered = useMemo(() => {
    return search
      .search(deferredQuery, category)
      .map((entry) => entry.pattern);
  }, [category, deferredQuery, search]);

  return (
    <CatalogPage
      patterns={filtered}
      total={patterns.length}
      query={query}
      category={category}
      onQueryChange={(value) => setQuery(value, category)}
      onCategoryChange={setCategory}
      onIntentClick={(value) => setQuery(value, category)}
      onOpenPalette={onOpenPalette}
    />
  );
}

function CategoryRoute(props: { patterns: PatternRecord[]; onOpenPalette: () => void }) {
  const { categoryId } = useParams();
  const navigate = useNavigate();
  const category = (categoryId as PatternCategory | undefined) ?? "all";

  useEffect(() => {
    navigate({ pathname: "/patterns", search: category === "all" ? "" : `category=${category}` }, { replace: true });
  }, [category, navigate]);

  return <CatalogRoute patterns={props.patterns} onOpenPalette={props.onOpenPalette} />;
}

function HomeRoute(props: { patterns: PatternRecord[]; onOpenPalette: () => void }) {
  const { patterns, onOpenPalette } = props;
  const [query, setQuery] = useState("");
  const deferredQuery = useDeferredValue(query);
  const search = useMemo(() => createPatternSearch(patterns), [patterns]);

  const featuredPatterns = useMemo(() => {
    if (deferredQuery.trim()) {
      return search.search(deferredQuery).slice(0, 6).map((entry) => entry.pattern);
    }
    return patterns.filter((pattern) => pattern.featured).slice(0, 6);
  }, [deferredQuery, patterns, search]);

  const testedPatterns = patterns.filter((pattern) => pattern.hasTests).length;
  const examplesIncluded = patterns.reduce((sum, pattern) => sum + (pattern.demoCodePaths?.length ?? 0), 0);
  const totalCategories = new Set(patterns.map((pattern) => pattern.category)).size;

  return (
    <HomePage
      featuredPatterns={featuredPatterns}
      query={query}
      onQueryChange={setQuery}
      onIntentClick={setQuery}
      onOpenPalette={onOpenPalette}
      stats={{
        totalPatterns: patterns.length,
        totalCategories,
        examplesIncluded,
        testedPatterns,
      }}
    />
  );
}

function PatternRoute(props: { patterns: PatternRecord[]; relatedPatterns: RelatedPatternMap }) {
  const { slug } = useParams();
  const pattern = props.patterns.find((item) => item.slug === slug);
  if (!pattern) {
    return <NotFoundPage />;
  }
  const related = (props.relatedPatterns[pattern.id] ?? [])
    .map((id) => props.patterns.find((candidate) => candidate.id === id))
    .filter((item): item is PatternRecord => Boolean(item));
  return <PatternPage pattern={pattern} related={related} />;
}

export default function App() {
  const [patterns, setPatterns] = useState<PatternRecord[]>([]);
  const [relatedPatterns, setRelatedPatterns] = useState<RelatedPatternMap>({});
  const [isLoading, setIsLoading] = useState(true);
  const [paletteOpen, setPaletteOpen] = useState(false);
  const { recentSearches, remember } = useRecentSearches();

  useEffect(() => {
    let active = true;

    async function loadContent() {
      try {
        const [patternsResponse, relatedResponse] = await Promise.all([
          fetch(patternsUrl),
          fetch(relatedUrl),
        ]);
        const [patternsPayload, relatedPayload] = await Promise.all([
          patternsResponse.json(),
          relatedResponse.json(),
        ]);
        if (!active) return;
        setPatterns(patternsPayload as PatternRecord[]);
        setRelatedPatterns(relatedPayload as RelatedPatternMap);
      } finally {
        if (active) setIsLoading(false);
      }
    }

    void loadContent();
    return () => {
      active = false;
    };
  }, []);

  const search = useMemo(() => createPatternSearch(patterns), [patterns]);

  useEffect(() => {
    const onKeyDown = (event: KeyboardEvent) => {
      if ((event.ctrlKey || event.metaKey) && event.key.toLowerCase() === "k") {
        event.preventDefault();
        setPaletteOpen(true);
      }
    };
    window.addEventListener("keydown", onKeyDown);
    return () => window.removeEventListener("keydown", onKeyDown);
  }, []);

  return (
    <AppShell onOpenSearch={() => setPaletteOpen(true)}>
      <SearchCommandPalette
        isOpen={paletteOpen}
        onClose={() => setPaletteOpen(false)}
        patterns={patterns}
        recentSearches={recentSearches}
        onRememberSearch={remember}
        search={search}
      />
      {isLoading ? (
        <div className="mx-auto max-w-3xl px-4 py-24 text-center md:px-6">
          <div className="font-mono text-xs uppercase tracking-[0.28em] text-cyan-300">Loading</div>
          <h1 className="mt-4 font-display text-4xl font-bold text-white">Building the catalog view</h1>
          <p className="mt-4 text-lg text-slate-400">Loading the generated pattern index and search sources.</p>
        </div>
      ) : (
        <Routes>
          <Route path="/" element={<HomeRoute patterns={patterns} onOpenPalette={() => setPaletteOpen(true)} />} />
          <Route path="/patterns" element={<CatalogRoute patterns={patterns} onOpenPalette={() => setPaletteOpen(true)} />} />
          <Route path="/patterns/:slug" element={<PatternRoute patterns={patterns} relatedPatterns={relatedPatterns} />} />
          <Route path="/categories/:categoryId" element={<CategoryRoute patterns={patterns} onOpenPalette={() => setPaletteOpen(true)} />} />
          <Route path="/about" element={<AboutPage />} />
          <Route path="/404" element={<NotFoundPage />} />
          <Route path="*" element={<Navigate to="/404" replace />} />
        </Routes>
      )}
    </AppShell>
  );
}
