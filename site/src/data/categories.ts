import type { PatternCategory } from "../types/pattern";

export interface CategoryDefinition {
  id: PatternCategory;
  label: string;
  shortLabel: string;
  description: string;
  accentClass: string;
  icon: string;
  repoFolder: string;
}

export const categories: CategoryDefinition[] = [
  {
    id: "gof",
    label: "GoF",
    shortLabel: "GoF",
    description: "Creational, structural, and behavioral fundamentals.",
    accentClass: "from-cyan-500/20 via-sky-500/10 to-transparent",
    icon: "hexagon",
    repoFolder: "gof-patterns",
  },
  {
    id: "architectural",
    label: "Architectural",
    shortLabel: "Arch",
    description: "System boundary, layering, and orchestration patterns.",
    accentClass: "from-violet-500/20 via-fuchsia-500/10 to-transparent",
    icon: "layers",
    repoFolder: "architectural-patterns",
  },
  {
    id: "enterprise-integration",
    label: "Enterprise Integration",
    shortLabel: "EIP",
    description: "Messaging, consistency, and workflow patterns.",
    accentClass: "from-emerald-500/20 via-lime-500/10 to-transparent",
    icon: "flow",
    repoFolder: "enterprise-integration-patterns",
  },
  {
    id: "reliability",
    label: "Reliability",
    shortLabel: "Rel",
    description: "Resilience, throttling, retries, and fault isolation.",
    accentClass: "from-amber-500/20 via-orange-500/10 to-transparent",
    icon: "shield",
    repoFolder: "reliability-patterns",
  },
  {
    id: "miscellaneous",
    label: "Miscellaneous",
    shortLabel: "Misc",
    description: "Useful patterns that do not sit in one classical bucket.",
    accentClass: "from-slate-500/20 via-zinc-500/10 to-transparent",
    icon: "nodes",
    repoFolder: "miscellaneous-patterns",
  },
];

export const categoryMap = Object.fromEntries(
  categories.map((category) => [category.id, category]),
) as Record<PatternCategory, CategoryDefinition>;
