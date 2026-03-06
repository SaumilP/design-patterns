import { promises as fs } from "node:fs";
import path from "node:path";

const categoryPalettes = {
  gof: {
    start: "#ffb38a",
    end: "#f59e7a",
    stroke: "#7c2d12",
    bg: "#fff1e8",
    accent: "#fb923c",
  },
  architectural: {
    start: "#c4b5fd",
    end: "#8b5cf6",
    stroke: "#4c1d95",
    bg: "#f5f3ff",
    accent: "#7c3aed",
  },
  "enterprise-integration": {
    start: "#7dd3fc",
    end: "#38bdf8",
    stroke: "#0c4a6e",
    bg: "#eff6ff",
    accent: "#0284c7",
  },
  reliability: {
    start: "#fcd34d",
    end: "#fb923c",
    stroke: "#7c2d12",
    bg: "#fff7ed",
    accent: "#ea580c",
  },
  miscellaneous: {
    start: "#94a3b8",
    end: "#64748b",
    stroke: "#1e293b",
    bg: "#f8fafc",
    accent: "#475569",
  },
};

function seededValues(seed) {
  let state = 0;
  for (const char of seed) {
    state = (state * 31 + char.charCodeAt(0)) >>> 0;
  }

  return () => {
    state = (1664525 * state + 1013904223) >>> 0;
    return state / 4294967296;
  };
}

function escapeHtml(value) {
  return value
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;");
}

export async function generatePatternPreview({
  siteRoot,
  slug,
  name,
  category,
  subcategory,
  tags,
}) {
  const palette = categoryPalettes[category];
  const outputDir = path.join(siteRoot, "public", "pattern-previews");
  await fs.mkdir(outputDir, { recursive: true });

  const rand = seededValues(slug);
  const circles = Array.from({ length: 5 }, (_, index) => {
    const cx = 160 + Math.round(rand() * 730);
    const cy = 45 + Math.round(rand() * 170);
    const radius = 18 + Math.round(rand() * 34);
    const opacity = 0.12 + rand() * 0.12;
    return `<circle cx="${cx}" cy="${cy}" r="${radius}" fill="${palette.accent}" opacity="${opacity.toFixed(2)}" />`;
  }).join("");

  const bars = Array.from({ length: 4 }, (_, index) => {
    const x = 110 + index * 165;
    const y = 146 + Math.round(rand() * 34);
    const width = 110 + Math.round(rand() * 34);
    return `<rect x="${x}" y="${y}" width="${width}" height="16" rx="8" fill="${palette.stroke}" opacity="${(0.18 + index * 0.08).toFixed(2)}" />`;
  }).join("");

  const title = escapeHtml(name);
  const subtitle = escapeHtml(subcategory || tags.slice(0, 2).join(" • ") || "design pattern");

  const svg = `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 630" fill="none">
  <defs>
    <linearGradient id="bg" x1="70" y1="70" x2="1130" y2="560" gradientUnits="userSpaceOnUse">
      <stop stop-color="${palette.start}" />
      <stop offset="1" stop-color="${palette.end}" />
    </linearGradient>
    <linearGradient id="panel" x1="190" y1="120" x2="890" y2="470" gradientUnits="userSpaceOnUse">
      <stop stop-color="${palette.bg}" />
      <stop offset="1" stop-color="#ffffff" />
    </linearGradient>
  </defs>
  <rect width="1200" height="630" rx="42" fill="#0f172a" />
  <rect x="28" y="28" width="1144" height="574" rx="34" fill="url(#bg)" />
  <g opacity=".24">
    <path d="M40 124H1160" stroke="white" />
    <path d="M40 248H1160" stroke="white" />
    <path d="M40 372H1160" stroke="white" />
    <path d="M40 496H1160" stroke="white" />
  </g>
  ${circles}
  <rect x="96" y="104" width="1008" height="422" rx="34" fill="url(#panel)" opacity=".95" />
  <rect x="140" y="148" width="144" height="42" rx="21" fill="${palette.stroke}" opacity=".12" />
  <text x="164" y="176" fill="${palette.stroke}" font-family="JetBrains Mono, monospace" font-size="22" font-weight="700">${escapeHtml(category.toUpperCase())}</text>
  <text x="140" y="280" fill="${palette.stroke}" font-family="Space Grotesk, Arial, sans-serif" font-size="72" font-weight="700">${title}</text>
  <text x="140" y="330" fill="${palette.accent}" font-family="JetBrains Mono, monospace" font-size="26" font-weight="600">${subtitle}</text>
  ${bars}
  <g transform="translate(885 170)">
    <rect width="140" height="140" rx="34" fill="${palette.stroke}" opacity=".10" />
    <path d="M70 18 114 44v52l-44 26-44-26V44l44-26Z" fill="${palette.accent}" opacity=".92" />
    <path d="M47 54h31c13 0 22 7 22 17s-9 17-22 17H47V54Z" fill="white" />
  </g>
</svg>`;

  const filename = `${slug}.svg`;
  const outputPath = path.join(outputDir, filename);
  await fs.writeFile(outputPath, svg, "utf8");

  return `/pattern-previews/${filename}`;
}
