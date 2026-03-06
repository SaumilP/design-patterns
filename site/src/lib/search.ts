import { Document } from "flexsearch";
import type { PatternCategory, PatternRecord } from "../types/pattern";

export interface SearchResult {
  pattern: PatternRecord;
  score: number;
}

export const intentChips = [
  "Object creation",
  "Behavior selection",
  "Reliability",
  "Integration flow",
  "State transitions",
];

function levenshtein(left: string, right: string): number {
  const a = left.toLowerCase();
  const b = right.toLowerCase();
  const matrix = Array.from({ length: a.length + 1 }, () => new Array<number>(b.length + 1).fill(0));

  for (let i = 0; i <= a.length; i += 1) matrix[i][0] = i;
  for (let j = 0; j <= b.length; j += 1) matrix[0][j] = j;

  for (let i = 1; i <= a.length; i += 1) {
    for (let j = 1; j <= b.length; j += 1) {
      const cost = a[i - 1] === b[j - 1] ? 0 : 1;
      matrix[i][j] = Math.min(
        matrix[i - 1][j] + 1,
        matrix[i][j - 1] + 1,
        matrix[i - 1][j - 1] + cost,
      );
    }
  }

  return matrix[a.length][b.length];
}

function normalizedCategoryText(pattern: PatternRecord) {
  return `${pattern.category} ${pattern.categoryLabel}`.toLowerCase();
}

export function createPatternSearch(patterns: PatternRecord[]) {
  const document = new Document<PatternRecord>({
    charset: "latin:advanced",
    tokenize: "forward",
    resolution: 9,
    document: {
      id: "id",
      store: true,
      index: [
        { field: "name", tokenize: "forward" },
        { field: "aliases", tokenize: "forward" },
        { field: "keywords", tokenize: "forward" },
        { field: "tags", tokenize: "forward" },
        { field: "summary", tokenize: "full" },
        { field: "intent", tokenize: "full" },
        { field: "contentMarkdown", tokenize: "strict" },
      ],
    },
  });

  for (const pattern of patterns) {
    document.add(pattern);
  }

  return {
    search(query: string, categoryFilter?: PatternCategory | "all") {
      const trimmed = query.trim();
      const lowerQuery = trimmed.toLowerCase();
      const scores = new Map<string, number>();

      const addScore = (patternId: string, value: number) => {
        scores.set(patternId, (scores.get(patternId) ?? 0) + value);
      };

      if (trimmed.length > 1) {
        const results = document.search(trimmed, { enrich: true, limit: 24, suggest: true });
        for (const resultSet of results) {
          const fieldWeight =
            resultSet.field === "name"
              ? 20
              : resultSet.field === "aliases"
                ? 15
                : resultSet.field === "keywords"
                  ? 12
                  : resultSet.field === "tags"
                    ? 10
                    : resultSet.field === "summary"
                      ? 8
                      : resultSet.field === "intent"
                        ? 7
                        : 4;

          for (const item of resultSet.result) {
            addScore(String(item.id), fieldWeight);
          }
        }
      }

      for (const pattern of patterns) {
        const name = pattern.name.toLowerCase();
        const aliases = pattern.aliases.map((alias) => alias.toLowerCase());
        const keywords = pattern.keywords.map((keyword) => keyword.toLowerCase());
        const categoryText = normalizedCategoryText(pattern);

        if (!trimmed) {
          addScore(pattern.id, pattern.featured ? 20 : 1);
        }

        if (name === lowerQuery) addScore(pattern.id, 200);
        if (name.startsWith(lowerQuery)) addScore(pattern.id, 120);
        if (name.includes(lowerQuery) && lowerQuery) addScore(pattern.id, 70);
        if (aliases.includes(lowerQuery)) addScore(pattern.id, 130);
        if (aliases.some((alias) => alias.startsWith(lowerQuery) && lowerQuery)) addScore(pattern.id, 80);
        if (keywords.some((keyword) => keyword.includes(lowerQuery) && lowerQuery)) addScore(pattern.id, 40);
        if (categoryText.includes(lowerQuery) && lowerQuery) addScore(pattern.id, 25);

        const fuzzyDistance = Math.min(
          levenshtein(lowerQuery, name),
          ...aliases.map((alias) => levenshtein(lowerQuery, alias)),
        );
        if (lowerQuery && fuzzyDistance <= 2) {
          addScore(pattern.id, 60 - fuzzyDistance * 15);
        }

        if (categoryFilter && categoryFilter !== "all" && pattern.category === categoryFilter) {
          addScore(pattern.id, 35);
        }
      }

      return patterns
        .filter((pattern) => categoryFilter === "all" || !categoryFilter || pattern.category === categoryFilter)
        .map((pattern) => ({
          pattern,
          score: scores.get(pattern.id) ?? 0,
        }))
        .filter((entry) => (trimmed ? entry.score > 0 : true))
        .sort((left, right) => right.score - left.score || left.pattern.name.localeCompare(right.pattern.name));
    },
    suggestions(query: string) {
      const lowerQuery = query.trim().toLowerCase();
      if (!lowerQuery) {
        return intentChips;
      }

      const values = new Set<string>();
      for (const pattern of patterns) {
        if (pattern.name.toLowerCase().includes(lowerQuery)) values.add(pattern.name);
        if (pattern.categoryLabel.toLowerCase().includes(lowerQuery)) values.add(pattern.categoryLabel);
        for (const keyword of pattern.keywords) {
          if (keyword.toLowerCase().includes(lowerQuery)) values.add(keyword);
        }
      }
      return [...values].slice(0, 6);
    },
  };
}
