import { promises as fs } from "node:fs";
import path from "node:path";
import { MANUAL_ALIASES } from "./extract-pattern-metadata.mjs";

const siteRoot = process.cwd();
const patternsPath = path.join(siteRoot, "src/content/patterns.json");
const relatedOutput = path.join(siteRoot, "src/content/related-patterns.json");

const MANUAL_RELATIONS = {
  "abstract-factory": ["factory-method", "builder", "prototype"],
  observer: ["mediator", "strategy", "state"],
  "circuit-breaker": ["retry-backoff", "rate-limiter", "bulkhead"],
  "retry-backoff": ["circuit-breaker", "timeout-deadline-propagation", "bulkhead"],
  saga: ["cqrs", "transactional-outbox", "pipes-and-filters"],
};

function overlap(left, right) {
  const rightSet = new Set(right);
  return left.filter((item) => rightSet.has(item)).length;
}

function score(left, right) {
  let value = 0;
  if (left.category === right.category) value += 10;
  if (left.subcategory && left.subcategory === right.subcategory) value += 6;
  value += overlap(left.tags, right.tags) * 3;
  value += overlap(left.keywords, right.keywords) * 2;
  value += overlap(left.aliases, right.aliases) * 2;
  value += overlap(left.alternatives ?? [], right.keywords) * 2;

  const manual = MANUAL_RELATIONS[left.id] ?? [];
  if (manual.includes(right.id)) value += 20;

  const aliasManual = MANUAL_ALIASES[left.id] ?? [];
  if (aliasManual.some((alias) => right.keywords.includes(alias))) value += 2;

  return value;
}

async function build() {
  const patterns = JSON.parse(await fs.readFile(patternsPath, "utf8"));
  const relatedMap = {};

  for (const pattern of patterns) {
    const related = patterns
      .filter((candidate) => candidate.id !== pattern.id)
      .map((candidate) => ({ id: candidate.id, score: score(pattern, candidate) }))
      .filter((candidate) => candidate.score > 0)
      .sort((left, right) => right.score - left.score)
      .slice(0, 4)
      .map((candidate) => candidate.id);

    relatedMap[pattern.id] = related;
    pattern.relatedPatternIds = related;
  }

  await fs.writeFile(patternsPath, `${JSON.stringify(patterns, null, 2)}\n`, "utf8");
  await fs.writeFile(relatedOutput, `${JSON.stringify(relatedMap, null, 2)}\n`, "utf8");
}

await build();
