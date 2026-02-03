#!/usr/bin/env python3
import argparse
import hashlib
import os
import json
import re
import subprocess
from pathlib import Path

MERMAID_BLOCK_RE = re.compile(r"```mermaid\n(.*?)\n```", re.S)

def render_mermaid_blocks(markdown: str, image_dir: Path, out_markdown_path: Path) -> str:
    image_dir.mkdir(parents=True, exist_ok=True)
    images = []

    def render_block(match: re.Match) -> str:
        content = match.group(1)
        digest = hashlib.sha1(content.encode("utf-8")).hexdigest()[:12]
        mmd_path = image_dir / f"diagram-{digest}.mmd"
        png_path = image_dir / f"diagram-{digest}.png"

        if not png_path.exists():
            mmd_path.write_text(content, encoding="utf-8")
            # Create a Puppeteer config file with sandboxing flags so CI runners
            # (like GitHub Actions) that require `--no-sandbox` can launch Chromium.
            puppeteer_cfg = {
                "args": ["--no-sandbox", "--disable-setuid-sandbox"]
            }
            cfg_path = image_dir / f"puppeteer-config-{digest}.json"
            cfg_path.write_text(json.dumps(puppeteer_cfg), encoding="utf-8")

            subprocess.run(
                [
                    "mmdc",
                    "-i",
                    str(mmd_path),
                    "-o",
                    str(png_path),
                    "--backgroundColor",
                    "#ffffff",
                    "--puppeteerConfigFile",
                    str(cfg_path),
                ],
                check=True,
            )
        images.append(png_path)

        rel_path = os.path.relpath(png_path, out_markdown_path.parent)
        return f"![Mermaid diagram]({rel_path})"

    return MERMAID_BLOCK_RE.sub(render_block, markdown)


def main() -> int:
    parser = argparse.ArgumentParser(description="Render Mermaid blocks to PNGs and replace with images.")
    parser.add_argument("input", help="Input markdown path")
    parser.add_argument("output", help="Output markdown path")
    parser.add_argument("image_dir", help="Directory for rendered images")
    args = parser.parse_args()

    in_path = Path(args.input)
    out_path = Path(args.output)
    image_dir = Path(args.image_dir)

    markdown = in_path.read_text(encoding="utf-8")
    rendered = render_mermaid_blocks(markdown, image_dir, out_path)
    out_path.write_text(rendered, encoding="utf-8")
    return 0

if __name__ == "__main__":
    raise SystemExit(main())
