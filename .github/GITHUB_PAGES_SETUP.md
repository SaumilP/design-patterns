# Pages Configuration for Design Patterns Documentation

This file documents the GitHub Pages setup needed for the workflow to function properly.

## Required Configuration Steps

### 1. Enable GitHub Actions Permissions

In your repository settings:

1. Go to **Settings > Actions > General**
2. Under "Workflow permissions":
   - Select: **Read and write permissions**
   - Check: **Allow GitHub Actions to create and approve pull requests**
3. Click **Save**

### 2. Configure GitHub Pages

In your repository settings:

1. Go to **Settings > Pages**
2. Under "Source":
   - Select: **Deploy from a branch**
   - Choose Branch: **gh-pages**
   - Choose Folder: **/ (root)**
3. Click **Save**

**Note:** The workflow automatically creates the `gh-pages` branch and pushes content there.

### 3. Verify GitHub Pages Settings

After first successful workflow run:

1. Go to **Settings > Pages**
2. You should see a green checkmark: ✅ "Your site is published at https://[username].github.io/[repo-name]/"
3. Click the link to view your published documentation

## Workflow Behavior

### On Push to Main Branch:
1. ✅ Maven builds and tests all modules
2. ✅ Combines all README.md files into comprehensive guide
3. ✅ Generates HTML version with professional styling
4. ✅ Generates PDF version (if LaTeX available)
5. ✅ Deploys HTML to GitHub Pages
6. ✅ Creates release with all documentation formats
7. ✅ Tags release with auto-incrementing version

### On Pull Request:
1. ✅ Maven builds and tests all modules
2. ⏭️ Skips documentation generation
3. ⏭️ Skips GitHub Pages deployment
4. ⏭️ Skips release creation

## Documentation Access Points

After successful workflow execution, documentation will be available at:

### GitHub Pages (Web)
- **URL:** `https://[username].github.io/[repo-name]/`
- **Format:** Interactive HTML with table of contents
- **Availability:** Immediate after deployment (usually 1-2 minutes)

### GitHub Releases
- **Location:** Repository > Releases tab
- **Formats:** 
  - `design-patterns-book.md` - Master markdown file
  - `design-patterns-book.pdf` - PDF version
  - `index.html` - Standalone HTML
- **Versions:** Each push creates a new release with auto-incrementing version number

### GitHub Actions Artifacts
- **Location:** Repository > Actions > Latest Run
- **Contents:** Book output files (if needed for review)
- **Retention:** 90 days (GitHub default)

## Troubleshooting

### GitHub Pages Not Showing

**Problem:** After first workflow run, GitHub Pages URL shows 404 error.

**Solutions:**
1. Verify `gh-pages` branch exists (Settings > Branches)
2. Check GitHub Pages source is set to `gh-pages` branch
3. Wait 2-3 minutes for DNS propagation
4. Manually trigger workflow: **Actions > Java CI > Run workflow**

### Release Not Created

**Problem:** After push to main, no release appears in Releases tab.

**Solutions:**
1. Verify branch is exactly named `main` (not `master`)
2. Check GitHub Actions permissions (see step 1 above)
3. Review Actions logs for authentication errors
4. Ensure GITHUB_TOKEN has release creation scope

### PDF Generation Fails

**Problem:** Release created but PDF file is missing or invalid.

**Explanation:** PDF generation requires XeLaTeX, which may not be available in CI environment.

**Current Behavior:** Workflow continues with fallback to HTML-only generation. PDF is optional.

**If PDF is Critical:**
- Consider local PDF generation as alternative
- Document will still be available in Markdown and HTML formats

### Build or Test Failures

**Problem:** Workflow stops at Maven build or test stage.

**Solutions:**
1. Check test code for Java 21 compatibility
2. Verify all module dependencies are available
3. Review Maven pom.xml configuration
4. Check for any new module additions not in pom.xml

## Next Steps

1. ✅ Verify all configurations above are complete
2. ✅ Make a test push to main branch
3. ✅ Monitor Actions run in repository Actions tab
4. ✅ Verify documentation appears at GitHub Pages URL
5. ✅ Download release artifacts to verify quality

## Additional Resources

- [GitHub Actions Documentation](https://docs.github.com/actions)
- [GitHub Pages Documentation](https://docs.github.com/pages)
- [Pandoc Documentation](https://pandoc.org)
- [Design Patterns Workflow Setup](./../WORKFLOW_SETUP.md)

---

**Configuration Date:** February 1, 2026
**Last Verified:** GitHub Actions v4, Pages Actions v3-v4
