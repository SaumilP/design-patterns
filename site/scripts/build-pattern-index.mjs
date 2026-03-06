import { promises as fs } from "node:fs";
import path from "node:path";
import { extractPatternMetadata } from "./extract-pattern-metadata.mjs";

const CATEGORY_FOLDERS = [
  "gof-patterns",
  "architectural-patterns",
  "enterprise-integration-patterns",
  "reliability-patterns",
  "miscellaneous-patterns",
];

const siteRoot = process.cwd();
const repoRoot = path.resolve(siteRoot, "..");
const patternsOutput = path.join(siteRoot, "src/content/patterns.json");
const relatedOutput = path.join(siteRoot, "src/content/related-patterns.json");
const sitemapOutput = path.join(siteRoot, "public/sitemap.xml");
const siteUrl = "https://saumilp.github.io/design-patterns";

async function exists(filePath) {
  try {
    await fs.access(filePath);
    return true;
  } catch {
    return false;
  }
}

async function listDemoCodePaths(patternPath) {
  const candidates = [
    "src/main/java",
    "src/main/kotlin",
    "src/main",
    "src",
  ];

  const matches = [];
  for (const candidate of candidates) {
    const fullPath = path.join(patternPath, candidate);
    if (await exists(fullPath)) {
      matches.push(path.relative(repoRoot, fullPath));
    }
  }

  return matches.slice(0, 3);
}

function buildSitemap(patterns) {
  const urls = [
    `${siteUrl}/`,
    `${siteUrl}/patterns`,
    `${siteUrl}/about`,
    `${siteUrl}/categories/gof`,
    `${siteUrl}/categories/architectural`,
    `${siteUrl}/categories/enterprise-integration`,
    `${siteUrl}/categories/reliability`,
    `${siteUrl}/categories/miscellaneous`,
    ...patterns.map((pattern) => `${siteUrl}/patterns/${pattern.slug}`),
  ];

  return `<?xml version="1.0" encoding="UTF-8"?>\n<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">\n${urls
    .map((url) => `  <url><loc>${url}</loc></url>`)
    .join("\n")}\n</urlset>\n`;
}

async function build() {
  const patterns = [];

  for (const categoryFolder of CATEGORY_FOLDERS) {
    const categoryPath = path.join(repoRoot, categoryFolder);
    const entries = await fs.readdir(categoryPath, { withFileTypes: true });
    const directories = entries
      .filter((entry) => entry.isDirectory())
      .map((entry) => entry.name)
      .sort((left, right) => left.localeCompare(right));

    for (const folderName of directories) {
      const patternPath = path.join(categoryPath, folderName);
      const readmePath = path.join(patternPath, "README.md");

      if (!(await exists(readmePath))) {
        continue;
      }

      const repoPath = `${categoryFolder}/${folderName}`;
      const markdownSource = await fs.readFile(readmePath, "utf8");
      const hasTests = await exists(path.join(patternPath, "src/test"));
      const demoCodePaths = await listDemoCodePaths(patternPath);

      patterns.push(
        await extractPatternMetadata({
          categoryFolder,
          folderName,
          repoPath,
          githubUrl: `https://github.com/SaumilP/design-patterns/tree/main/${repoPath}`,
          markdownSource,
          hasTests,
          demoCodePaths,
        }),
      );
    }
  }

  await fs.mkdir(path.dirname(patternsOutput), { recursive: true });
  await fs.writeFile(patternsOutput, `${JSON.stringify(patterns, null, 2)}\n`, "utf8");
  await fs.writeFile(relatedOutput, "{}\n", "utf8");
  await fs.writeFile(sitemapOutput, buildSitemap(patterns), "utf8");
}

await build();
