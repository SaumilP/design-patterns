export function stripMarkdown(markdown) {
  return markdown
    .replace(/^---[\s\S]*?---\s*/m, "")
    .replace(/```[\s\S]*?```/g, " ")
    .replace(/`([^`]+)`/g, "$1")
    .replace(/!\[[^\]]*]\([^)]*\)/g, " ")
    .replace(/\[([^\]]+)]\(([^)]+)\)/g, "$1")
    .replace(/^#{1,6}\s+/gm, "")
    .replace(/[*_>~-]/g, " ")
    .replace(/\|/g, " ")
    .replace(/\s+/g, " ")
    .trim();
}

export function normalizeHeading(value) {
  return value
    .toLowerCase()
    .replace(/[^\p{L}\p{N}\s/-]/gu, " ")
    .replace(/\s+/g, " ")
    .trim();
}

export function collectHeadings(markdown) {
  return [...markdown.matchAll(/^##+\s+(.+)$/gm)].map((match) => match[1].trim());
}

export function getFirstParagraph(markdown) {
  const lines = markdown
    .replace(/^---[\s\S]*?---\s*/m, "")
    .split("\n")
    .map((line) => line.trim());

  let current = [];
  for (const line of lines) {
    if (!line) {
      if (current.length) {
        break;
      }
      continue;
    }

    if (
      line.startsWith("#") ||
      line.startsWith("```") ||
      line.startsWith("|") ||
      line === "---"
    ) {
      if (current.length) {
        break;
      }
      continue;
    }

    current.push(line);
  }

  return current.join(" ").trim();
}

export function getH1(markdown) {
  const matches = [...markdown.matchAll(/^#\s*(.*)$/gm)];
  for (const match of matches) {
    const value = match[1]?.trim() ?? "";
    if (value && value !== "#") {
      return value.replace(/^#\s*/, "").trim();
    }
  }
  return "";
}

export function toTitleFromFolder(folderName) {
  return folderName
    .split("-")
    .map((part) => part.charAt(0).toUpperCase() + part.slice(1))
    .join(" ");
}

export function sectionContent(markdown, candidateHeadings) {
  const headingSet = new Set(candidateHeadings.map(normalizeHeading));
  const lines = markdown.split("\n");
  let collecting = false;
  const collected = [];

  for (const line of lines) {
    const headingMatch = line.match(/^(##+)\s+(.+)$/);
    if (headingMatch) {
      const normalized = normalizeHeading(headingMatch[2]);
      if (collecting) {
        break;
      }
      if (headingSet.has(normalized)) {
        collecting = true;
        continue;
      }
    }

    if (collecting) {
      collected.push(line);
    }
  }

  return collected.join("\n").trim();
}

export function linesToList(value) {
  if (!value.trim()) {
    return [];
  }

  const bulletItems = value
    .split("\n")
    .map((line) => line.trim())
    .filter((line) => /^[-*]\s+/.test(line))
    .map((line) => line.replace(/^[-*]\s+/, "").trim())
    .filter(Boolean);

  if (bulletItems.length) {
    return bulletItems;
  }

  return stripMarkdown(value)
    .split(/(?<=\.)\s+|;\s+/)
    .map((item) => item.trim())
    .filter((item) => item.length > 12)
    .slice(0, 5);
}

export function uniqueNormalized(values) {
  const seen = new Set();
  const result = [];
  for (const value of values) {
    const trimmed = value.trim();
    if (!trimmed) {
      continue;
    }
    const key = trimmed.toLowerCase();
    if (seen.has(key)) {
      continue;
    }
    seen.add(key);
    result.push(trimmed);
  }
  return result;
}
