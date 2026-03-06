export type PatternCategory =
  | "gof"
  | "architectural"
  | "enterprise-integration"
  | "reliability"
  | "miscellaneous";

export interface PatternRecord {
  id: string;
  slug: string;
  name: string;
  category: PatternCategory;
  subcategory?: string;
  summary: string;
  intent?: string;
  problem?: string;
  applicability?: string[];
  tradeOffs?: string[];
  alternatives?: string[];
  keywords: string[];
  aliases: string[];
  tags: string[];
  githubPath: string;
  githubUrl: string;
  demoCodePaths?: string[];
  hasTests?: boolean;
  readingTimeMinutes?: number;
  contentHtml?: string;
  contentMarkdown?: string;
  relatedPatternIds?: string[];
  categoryLabel: string;
  headingIndex?: string[];
  excerpt?: string;
  featured?: boolean;
}

export type RelatedPatternMap = Record<string, string[]>;
