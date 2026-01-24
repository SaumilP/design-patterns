# Formatting & Static Analysis (Spotless + Checkstyle)

This repo is intended to be **reference-quality**. Formatting and static analysis help keep examples consistent and easy to read.

## What you get in this upgrade pack

- **Spotless (Maven):** consistent formatting (recommended: Google Java Format)
- **Checkstyle:** baseline style checks (readability + common pitfalls)
- **CI integration:** runs formatting checks + style checks on PRs

> Note: This pack includes *ready-to-drop files* plus a **pom.xml snippet**. You will paste the snippet into your existing `pom.xml`.

---

## 1) Add these files to the repo

- `config/checkstyle/checkstyle.xml`
- `config/checkstyle/suppressions.xml` (optional but recommended)
- `docs/FORMATTING.md` (this file)
- `pom.snippets/spotless-checkstyle.xml` (paste into your `pom.xml`)
- Update `.github/workflows/ci.yml` to run `mvn verify`

---

## 2) Paste the Maven snippet

Open your root `pom.xml` and paste the contents of:
- `pom.snippets/spotless-checkstyle.xml`

This snippet adds:
- Spotless plugin (format check)
- Maven Checkstyle plugin (style checks)
- A `verify` phase configuration so CI can run `mvn verify`

---

## 3) How to use locally

### Run formatting checks
```bash
mvn -q spotless:check
```

### Apply formatting
```bash
mvn -q spotless:apply
```

### Run style checks
```bash
mvn -q checkstyle:check
```

### Run everything (what CI runs)
```bash
mvn -q verify
```

---

## 4) Customization tips

- If Checkstyle is too strict for educational examples, keep only the most valuable rules.
- If certain examples require non-standard naming, add a suppression in `suppressions.xml`.
