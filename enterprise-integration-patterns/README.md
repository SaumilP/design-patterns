# Enterprise Integration Patterns (EIP)

Enterprise Integration Patterns are patterns for **moving data between systems** reliably, observably, and at scale. They show up when you integrate services using:
- messaging and event streams
- batch and file-based exchange
- pipelines and staged processing
- adapters, translators, and routing

In practice, EIP thinking is about designing **flows**: how messages enter, transform, route, and leave your system.

---

## 🎯 When To Use EIP

Use these patterns when:
- you integrate heterogeneous systems (different schemas, protocols, lifecycles)
- you need to decouple producers from consumers
- you want resilience to partial failures and variable throughput
- you need traceability (where did this message go, why, and what happened?)

Avoid them when:
- a direct call is sufficient and does not create tight coupling risk
- you are adding queues/pipes without a real need (operational overhead is real)
- you cannot support the required operational posture (monitoring, retries, DLQs)

---

## 🧭 Key Questions These Patterns Answer

- Where does a message come from, and what contract does it follow?
- How do we validate, enrich, transform, and route it?
- What happens on failure: retry, compensate, dead-letter, or drop?
- How do we observe the journey end-to-end?

---

## 🧭 Included Patterns

- Pipes & Filters — [`pipes-and-filters/`](./pipes-and-filters)
- Saga — [`saga/`](./saga)
- CQRS — [`cqrs/`](./cqrs)
- Transactional Outbox — [`transactional-outbox/`](./transactional-outbox)
