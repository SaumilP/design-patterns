# GitHub Workflow Setup Documentation

## Overview

The `.github/workflows/ci.yml` has been enhanced to provide comprehensive build, test, and documentation generation capabilities.

---

## Workflow Stages

### 1. **Build Stage** (Maven)
- **Command:** `mvn clean package -DskipTests`
- **Purpose:** Compiles all Java source code and packages JAR files
- **Output:** JAR artifacts for each design pattern module

### 2. **Test Stage** (Maven)
- **Command:** `mvn clean test`
- **Purpose:** Executes all unit tests across all modules
- **Coverage:** All 37 design pattern implementations
- **Requirements:** JUnit 5 tests in each module

### 3. **Documentation Generation Stage** (Pandoc)

#### a. Master Book Creation
- Combines all README.md files into a single comprehensive guide
- **Input:** Root README.md + 37 pattern README.md files
- **Output:** `book-output/design-patterns-book.md`
- **Process:**
  1. Creates master header
  2. Adds timestamp
  3. Includes root README content
  4. Sequentially adds all pattern READMEs in sorted order
  5. Uses markdown section separators (---)

#### b. PDF Generation
- **Tool:** Pandoc with XeLaTeX
- **Output:** `book-output/pdf/design-patterns-book.pdf`
- **Features:**
  - Table of Contents with 3-level depth
  - Numbered sections
  - Professional formatting (1" margins, 11pt font)
  - Fallback: HTML-only if LaTeX unavailable
- **Supported:** All markdown, code blocks, tables, diagrams

#### c. HTML Generation
- **Tool:** Pandoc with Water CSS theme
- **Output:** `book-output/html/index.html`
- **Features:**
  - Standalone HTML with embedded CSS
  - Professional water.css styling
  - Table of Contents with 3-level depth
  - Syntax highlighting (Pygments)
  - Mobile-friendly responsive design

### 4. **GitHub Pages Deployment**
- **Trigger:** On every push to main branch
- **Deployment:** HTML version of documentation
- **Configuration:**
  1. `actions/configure-pages@v4` - Sets up Pages configuration
  2. `actions/upload-pages-artifact@v3` - Uploads HTML artifact
  3. `actions/deploy-pages@v4` - Deploys to GitHub Pages
- **Access:** `https://<username>.github.io/<repo>/`

### 5. **GitHub Release Creation**
- **Trigger:** On push to main branch
- **Release Artifacts:**
  1. `design-patterns-book.md` - Master markdown guide
  2. `design-patterns-book.pdf` - PDF version (if generated)
  3. `index.html` - Standalone HTML version
- **Release Tag:** `v${{ github.run_number }}` (auto-incrementing)
- **Release Name:** `Design Patterns Documentation v${{ github.run_number }}`
- **Release Notes:** Include:
  - 37 design patterns documented
  - Available formats (MD, PDF, HTML)
  - Generation timestamp
  - Commit reference

---

## File Structure Generated

```
book-output/
├── design-patterns-book.md       # Master markdown document
├── pdf/
│   └── design-patterns-book.pdf  # PDF version (optional)
└── html/
    └── index.html                # HTML version (deployed to GitHub Pages)
```

---

## GitHub Pages Requirements

To enable GitHub Pages deployment, configure in repository settings:

1. **Settings > Pages**
   - **Source:** Deploy from a branch
   - **Branch:** gh-pages
   - **Folder:** root (/)

2. **Required Permissions:**
   - GitHub Actions: Read and write permissions enabled
   - GitHub Pages: Configured for GitHub Actions deployment

---

## Workflow Execution Details

### Triggers
- **Push events:** To `main` branch
  - Runs full workflow: build → test → docs → pages → release
- **Pull request events:** To `main` branch
  - Runs build and test only (no deployment)

### Environment
- **OS:** Ubuntu Latest
- **Java:** JDK 21 (Temurin distribution)
- **Maven:** Cached from previous runs
- **Additional Tools:** Pandoc (installed on-demand)

### Conditional Steps

| Step | Condition |
|------|-----------|
| Setup Pages | `github.event_name == 'push'` |
| Upload Pages Artifact | `github.event_name == 'push'` |
| Deploy Pages | `github.event_name == 'push'` |
| Create Release | `github.event_name == 'push' && main branch` |

---

## Build Artifacts

### Maven Build Outputs
- JAR files for each pattern module in `target/` directories
- Available in GitHub Actions artifacts

### Documentation Outputs
- **Markdown:** Fully featured with code blocks, tables, diagrams
- **PDF:** Professional formatting with bookmarks and TOC
- **HTML:** Responsive design with searchable content

---

## Workflow Variables

| Variable | Source | Usage |
|----------|--------|-------|
| `github.run_number` | GitHub Actions | Auto-incrementing release version |
| `github.event.head_commit.timestamp` | Git Push Event | Release generation time |
| `github.event.head_commit.message` | Git Commit | Release notes reference |
| `GITHUB_TOKEN` | GitHub Secrets | Authentication for releases |

---

## Troubleshooting

### Build Failures
- Check Maven dependencies are resolvable
- Verify all modules listed in pom.xml exist
- Review test failures in Maven output

### Test Failures
- Review individual test logs
- Check Java compatibility (requires JDK 21)
- Verify test dependencies are available

### PDF Generation Failures
- XeLaTeX may not be available in GitHub Actions
- Workflow automatically falls back to HTML-only
- PDF generation is optional, not critical

### GitHub Pages Not Deploying
- Verify Pages is enabled in repository settings
- Check branch configuration (should use gh-pages)
- Ensure Actions have write permissions

### Release Not Created
- Verify push is to main branch
- Check GITHUB_TOKEN has release creation permissions
- Review Actions logs for authentication errors

---

## Related Files

- [CI Workflow](.github/workflows/ci.yml) - Main workflow definition
- [Root README](../../README.md) - Project overview
- [Pattern READMEs](../../*/README.md) - Individual pattern documentation
- [POM Configuration](../../pom.xml) - Maven project configuration

---

## Future Enhancements

Potential improvements to consider:

1. **Multi-format support:** Generate EPUB, Mobi formats
2. **Content indexing:** Create searchable index for documentation
3. **API documentation:** Include Javadoc in book
4. **Code coverage:** Embed test coverage reports
5. **Performance metrics:** Track build times across releases
6. **Version comparison:** Generate diff between releases

---

**Last Updated:** February 1, 2026
**Workflow Version:** 1.0
