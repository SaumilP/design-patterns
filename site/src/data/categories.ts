import type { PatternCategory } from "../types/pattern";

export interface CategoryDefinition {
  id: PatternCategory;
  label: string;
  shortLabel: string;
  description: string;
  accentClass: string;
  icon: string;
  repoFolder: string;
  cardGradient: string;
  badgeClass: string;
  tint: string;
}

export const categories: CategoryDefinition[] = [
  {
    id: "gof",
    label: "GoF",
    shortLabel: "GoF",
    description: "Creational, structural, and behavioral fundamentals.",
    accentClass: "from-orange-300/70 via-amber-200/60 to-rose-100/40",
    icon: "hexagon",
    repoFolder: "gof-patterns",
    cardGradient: "from-[#fff1e8] via-[#ffe0cc] to-[#ffd3c2]",
    badgeClass: "border-orange-300/60 bg-orange-100 text-orange-900",
    tint: "#fb923c",
  },
  {
    id: "architectural",
    label: "Architectural",
    shortLabel: "Arch",
    description: "System boundary, layering, and orchestration patterns.",
    accentClass: "from-violet-300/70 via-fuchsia-200/60 to-indigo-100/40",
    icon: "layers",
    repoFolder: "architectural-patterns",
    cardGradient: "from-[#f3e8ff] via-[#e9d5ff] to-[#ddd6fe]",
    badgeClass: "border-violet-300/60 bg-violet-100 text-violet-900",
    tint: "#8b5cf6",
  },
  {
    id: "enterprise-integration",
    label: "Enterprise Integration",
    shortLabel: "EIP",
    description: "Messaging, consistency, and workflow patterns.",
    accentClass: "from-sky-300/70 via-cyan-200/60 to-blue-100/40",
    icon: "flow",
    repoFolder: "enterprise-integration-patterns",
    cardGradient: "from-[#e0f2fe] via-[#dbeafe] to-[#e0f7ff]",
    badgeClass: "border-sky-300/60 bg-sky-100 text-sky-900",
    tint: "#38bdf8",
  },
  {
    id: "reliability",
    label: "Reliability",
    shortLabel: "Rel",
    description: "Resilience, throttling, retries, and fault isolation.",
    accentClass: "from-amber-300/70 via-orange-200/60 to-yellow-100/40",
    icon: "shield",
    repoFolder: "reliability-patterns",
    cardGradient: "from-[#fff7d6] via-[#ffedd5] to-[#ffe7ba]",
    badgeClass: "border-amber-300/60 bg-amber-100 text-amber-900",
    tint: "#f59e0b",
  },
  {
    id: "miscellaneous",
    label: "Miscellaneous",
    shortLabel: "Misc",
    description: "Useful patterns that do not sit in one classical bucket.",
    accentClass: "from-slate-300/70 via-zinc-200/60 to-stone-100/40",
    icon: "nodes",
    repoFolder: "miscellaneous-patterns",
    cardGradient: "from-[#e2e8f0] via-[#f1f5f9] to-[#e7e5e4]",
    badgeClass: "border-slate-300/60 bg-slate-100 text-slate-900",
    tint: "#64748b",
  },
];

export const categoryMap = Object.fromEntries(
  categories.map((category) => [category.id, category]),
) as Record<PatternCategory, CategoryDefinition>;
