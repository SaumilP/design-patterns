import { useEffect } from "react";
import { pagesUrl } from "./github";

function ensureMeta(selector: string, factory: () => HTMLMetaElement | HTMLLinkElement) {
  let element = document.head.querySelector(selector) as HTMLMetaElement | HTMLLinkElement | null;
  if (!element) {
    element = factory();
    document.head.appendChild(element);
  }
  return element;
}

export function useSeo(args: { title: string; description: string; path: string }) {
  const { title, description, path } = args;

  useEffect(() => {
    document.title = `${title} | Design Patterns`;

    const metaDescription = ensureMeta('meta[name="description"]', () => {
      const meta = document.createElement("meta");
      meta.name = "description";
      return meta;
    }) as HTMLMetaElement;
    metaDescription.content = description;

    const ogTitle = ensureMeta('meta[property="og:title"]', () => {
      const meta = document.createElement("meta");
      meta.setAttribute("property", "og:title");
      return meta;
    }) as HTMLMetaElement;
    ogTitle.content = `${title} | Design Patterns`;

    const ogDescription = ensureMeta('meta[property="og:description"]', () => {
      const meta = document.createElement("meta");
      meta.setAttribute("property", "og:description");
      return meta;
    }) as HTMLMetaElement;
    ogDescription.content = description;

    const canonical = ensureMeta('link[rel="canonical"]', () => {
      const link = document.createElement("link");
      link.rel = "canonical";
      return link;
    }) as HTMLLinkElement;
    canonical.href = `${pagesUrl}${path}`;
  }, [description, path, title]);
}
