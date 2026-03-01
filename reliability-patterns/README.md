# Reliability Patterns

Reliability patterns focus on **surviving real-world conditions**: partial failures, concurrency, unpredictable latency, and imperfect inputs.

They typically trade local simplicity for systemic robustness by making failure modes explicit and repeatable.

---

## 🎯 When To Use Reliability Patterns

Use them when:
- you have concurrency (threads, async work, shared resources)
- failures are expected and must be handled predictably
- your system must continue operating under degraded conditions

Avoid them when:
- you are “hardening” code without an actual reliability requirement
- the pattern hides failures instead of surfacing them (silent corruption is worse than a crash)
- the added indirection makes debugging materially harder

---

## ✅ What Good Reliability Looks Like

- explicit contracts (what happens on null, timeouts, missing data)
- bounded retries and timeouts
- clear defaults and fallback behavior
- visible operational signals (logs/metrics/traces)

---

## 🧭 Included Patterns

- Double-Checked Locking — [`double-checked-locking/`](./double-checked-locking)
- Null Object — [`nullobject/`](./nullobject)
