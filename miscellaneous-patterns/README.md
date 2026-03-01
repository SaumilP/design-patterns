# Miscellaneous Patterns

Not every useful pattern fits neatly into GoF, EIP, reliability, or architecture buckets.

This section contains patterns that are still valuable in practice, but are:
- more niche
- not part of the GoF catalog
- often used as implementation techniques rather than “named architecture”

---

## 🎯 When To Use These Patterns

Use them when:
- they directly address a pain point you can name (complex method, duplicated behavior, lazy computation)
- you want to isolate complexity behind a small, testable surface

Avoid them when:
- the pattern is only being introduced because it “sounds right”
- the team does not share the vocabulary (a pattern nobody recognizes becomes private cleverness)

---

## 🧭 Included Patterns

- Criteria / Filter — [`filter/`](./filter)
- Lazy Sequence — [`lazy-sequence/`](./lazy-sequence)
- Method Object — [`method-object/`](./method-object)
- Servant — [`servant/`](./servant)
