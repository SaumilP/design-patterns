# Release Notes — v1.0.0

**Repository:** SaumilP/design-patterns  
**Release:** v1.0.0  
**Type:** Initial stable reference release

---

## Highlights
- Established a **reference-grade repository structure** for Java design pattern examples.
- Added a **pattern index** and consistent documentation guidelines.
- Introduced **CI quality gates** (build + tests + formatting/style checks).
- Added a **per-pattern README template** and **Mermaid C4-lite diagrams** for consistent architecture explanations.

---

## Included patterns
This release covers representative patterns across:
- Creational
- Structural
- Behavioral
- Hybrid/reference patterns (where present)

> See the root README Pattern Index for the full list.

---

## Developer experience
- `mvn clean verify` runs:
  - tests
  - Spotless format check (Google Java Format)
  - Checkstyle rules (moderate baseline)

---

## Upgrade notes
If upgrading from earlier commits:
- Add `config/checkstyle/*`
- Paste `pom.snippets/spotless-checkstyle.xml` into your `pom.xml`
- CI expects `mvn verify` to pass

---

## Next (v1.1.x)
- Add per-pattern mini READMEs across all modules
- Add a short “pattern comparison” guide (Strategy vs State vs Command)
- Add Mermaid diagrams for every pattern folder
