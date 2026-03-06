import MarkdownIt from "markdown-it";
import matter from "gray-matter";
import hljs from "highlight.js";
import {
  collectHeadings,
  getFirstParagraph,
  getH1,
  linesToList,
  normalizeHeading,
  sectionContent,
  stripMarkdown,
  toTitleFromFolder,
  uniqueNormalized,
} from "./markdown-utils.mjs";

function escapeHtml(value) {
  return value
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;");
}

const markdown = new MarkdownIt({
  html: true,
  linkify: true,
  typographer: true,
});

markdown.renderer.rules.fence = (tokens, index) => {
  const token = tokens[index];
  const language = (token.info || "").trim().split(/\s+/)[0];
  const code = token.content;

  if (language === "mermaid") {
    return `<div class="mermaid">${escapeHtml(code)}</div>`;
  }

  const validLanguage = language && hljs.getLanguage(language);
  const highlighted = validLanguage
    ? hljs.highlight(code, { language }).value
    : hljs.highlightAuto(code).value;
  const label = validLanguage ? language : "code";

  return `<div class="code-block"><div class="code-block__label">${escapeHtml(label)}</div><pre><code class="hljs language-${escapeHtml(label)}">${highlighted}</code></pre></div>`;
};

const CATEGORY_LABELS = {
  gof: "GoF",
  architectural: "Architectural",
  "enterprise-integration": "Enterprise Integration",
  reliability: "Reliability",
  miscellaneous: "Miscellaneous",
};

const GOF_SUBCATEGORIES = {
  "abstract-factory": "creational",
  adapter: "structural",
  bridge: "structural",
  builder: "creational",
  chain: "behavioral",
  command: "behavioral",
  composite: "structural",
  decorator: "structural",
  facade: "structural",
  "factory-method": "creational",
  flyweight: "structural",
  interpreter: "behavioral",
  iterator: "behavioral",
  mediator: "behavioral",
  memento: "behavioral",
  observer: "behavioral",
  prototype: "creational",
  proxy: "structural",
  singleton: "creational",
  state: "behavioral",
  strategy: "behavioral",
  "template-method": "behavioral",
  visitor: "behavioral",
};

const FEATURED_PATTERNS = new Set([
  "abstract-factory",
  "observer",
  "circuit-breaker",
  "retry-backoff",
  "saga",
  "service-locator",
]);

export const MANUAL_ALIASES = {
  "abstract-factory": ["AF"],
  "factory-method": ["FM"],
  "double-checked-locking": ["DCL"],
  nullobject: ["null object", "null-object"],
  "retry-backoff": ["retry with backoff"],
  filter: ["criteria", "criteria pattern"],
  "model-view-presenter": ["MVP"],
  cqrs: ["command query responsibility segregation"],
};

function deriveCategory(folder) {
  if (folder === "gof-patterns") return "gof";
  if (folder === "architectural-patterns") return "architectural";
  if (folder === "enterprise-integration-patterns") return "enterprise-integration";
  if (folder === "reliability-patterns") return "reliability";
  return "miscellaneous";
}

function inferName(folderName, content) {
  const h1 = getH1(content);
  if (h1 && h1 !== "#") {
    return h1.replace(/\s+Pattern$/i, "").trim();
  }
  return toTitleFromFolder(folderName);
}

function inferSummary(content, frontmatterSummary) {
  if (typeof frontmatterSummary === "string" && frontmatterSummary.trim()) {
    return frontmatterSummary.trim();
  }

  const firstParagraph = stripMarkdown(getFirstParagraph(content));
  return firstParagraph || "Runnable Java example with implementation notes and trade-offs.";
}

function inferSubcategory(category, folderName, frontmatterSubcategory) {
  if (typeof frontmatterSubcategory === "string" && frontmatterSubcategory.trim()) {
    return frontmatterSubcategory.trim();
  }
  if (category === "gof") {
    return GOF_SUBCATEGORIES[folderName];
  }
  return undefined;
}

function inferAliases(folderName, name, frontmatterAliases) {
  const aliases = [
    folderName.replace(/-/g, " "),
    name,
    ...(Array.isArray(frontmatterAliases) ? frontmatterAliases : []),
    ...(MANUAL_ALIASES[folderName] ?? []),
  ];

  if (name.includes("&")) {
    aliases.push(name.replace("&", "and"));
  }

  return uniqueNormalized(aliases);
}

function extractIntentProblemApplicability(content) {
  const intentSection = sectionContent(content, ["Intent"]);
  const fallbackIntentSection = sectionContent(content, ["When to use", "Use When", "Overview"]);
  const intent = stripMarkdown(intentSection || fallbackIntentSection).slice(0, 420);
  const problemSection = sectionContent(content, ["Problem", "Problem Solved"]);
  const applicabilitySection = sectionContent(content, [
    "Applicability",
    "Use When",
    "When to use",
    "When not to use",
  ]);

  return {
    intent: intent || undefined,
    problem: stripMarkdown(problemSection).slice(0, 320) || undefined,
    applicability: linesToList(applicabilitySection),
  };
}

function extractTradeOffs(content) {
  const extractSubsection = (labels) => {
    const pattern = new RegExp(
      `^###\\s+.*(?:${labels.join("|")}).*$([\\s\\S]*?)(?=^###\\s+|^##\\s+|\\Z)`,
      "gim",
    );
    const match = pattern.exec(content);
    return match?.[1]?.trim() ?? "";
  };

  const advantages = linesToList(extractSubsection(["Advantages", "Pros"]));
  const disadvantages = linesToList(extractSubsection(["Disadvantages", "Cons"]));
  const tradeOffsSection = sectionContent(content, [
    "Trade-offs",
    "Tradeoffs",
    "Advantages",
    "Disadvantages",
  ]);
  return {
    advantages,
    disadvantages,
    tradeOffs: linesToList(tradeOffsSection),
  };
}

function extractAlternatives(content) {
  const alternativesSection = sectionContent(content, [
    "Alternatives",
    "Alternatives & Similar Patterns",
    "Related patterns",
    "When NOT to Use",
  ]);
  return linesToList(alternativesSection);
}

function deriveKeywords({ name, category, subcategory, aliases, tags, headings, summary, intent }) {
  const seed = [
    name,
    category,
    subcategory ?? "",
    ...aliases,
    ...tags,
    ...headings,
    ...summary.split(/\W+/),
    ...(intent ?? "").split(/\W+/),
  ];

  const domainKeywords = [];
  const normalizedName = name.toLowerCase();
  if (normalizedName.includes("factory")) domainKeywords.push("object creation", "family of objects");
  if (normalizedName.includes("observer")) domainKeywords.push("event-driven", "pub-sub");
  if (normalizedName.includes("state")) domainKeywords.push("state transitions", "state-driven behavior");
  if (normalizedName.includes("breaker")) domainKeywords.push("resilience", "retry remote calls");
  if (normalizedName.includes("saga")) domainKeywords.push("message flow", "distributed transaction");

  return uniqueNormalized(
    [...seed, ...domainKeywords]
      .map((value) => value.trim())
      .filter((value) => value.length > 2 && !/^\d+$/.test(value)),
  );
}

function readingTimeMinutes(content) {
  const words = stripMarkdown(content).split(/\s+/).filter(Boolean).length;
  return Math.max(1, Math.ceil(words / 180));
}

export async function extractPatternMetadata({
  categoryFolder,
  folderName,
  repoPath,
  githubUrl,
  markdownSource,
  hasTests,
  demoCodePaths,
}) {
  const parsed = matter(markdownSource);
  const category = deriveCategory(categoryFolder);
  const name = String(parsed.data.name || inferName(folderName, parsed.content));
  const summary = inferSummary(parsed.content, parsed.data.summary);
  const subcategory = inferSubcategory(category, folderName, parsed.data.subcategory);
  const aliases = inferAliases(folderName, name, parsed.data.aliases);
  const headings = collectHeadings(parsed.content);
  const tags = uniqueNormalized([
    ...(Array.isArray(parsed.data.tags) ? parsed.data.tags : []),
    category,
    subcategory ?? "",
    ...headings.map((heading) => normalizeHeading(heading)),
  ]);
  const { intent, problem, applicability } = extractIntentProblemApplicability(parsed.content);
  const { advantages, disadvantages, tradeOffs } = extractTradeOffs(parsed.content);
  const alternatives = extractAlternatives(parsed.content);
  const keywords = deriveKeywords({
    name,
    category,
    subcategory,
    aliases,
    tags,
    headings,
    summary,
    intent,
  });

  return {
    id: folderName,
    slug: folderName,
    name,
    category,
    subcategory,
    summary,
    intent,
    problem,
    applicability,
    advantages,
    disadvantages,
    tradeOffs,
    alternatives,
    keywords,
    aliases,
    tags,
    githubPath: repoPath,
    githubUrl,
    demoCodePaths,
    hasTests,
    readingTimeMinutes: readingTimeMinutes(parsed.content),
    contentHtml: markdown.render(parsed.content),
    contentMarkdown: parsed.content.trim(),
    relatedPatternIds: [],
    categoryLabel: CATEGORY_LABELS[category],
    headingIndex: headings,
    excerpt: stripMarkdown(parsed.content).slice(0, 260),
    featured: FEATURED_PATTERNS.has(folderName),
  };
}
