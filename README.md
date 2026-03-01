![CI](https://github.com/SaumilP/design-patterns/actions/workflows/ci.yml/badge.svg)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![Java](https://img.shields.io/badge/Java-11%2B-orange)
[![Pages](https://img.shields.io/badge/Github-Pages-green)](https://saumilp.github.io/design-patterns)
![GitHub Repo stars](https://img.shields.io/github/stars/saumilp/design-patterns)

# Design Patterns in Java — Practical, Runnable Examples

A curated, production-oriented collection of **classic software design patterns implemented in Java**.  
Each pattern is expressed as **small, focused, runnable code** intended to demonstrate *intent*, *structure*, and *trade-offs* — not just theory.

This repository is designed as a **long-term reference** for backend engineers, architects, and senior developers.

---

## 🎯 Objectives

- Provide **clear, minimal implementations** of well-known design patterns
- Emphasize **when to use vs. when to avoid** each pattern
- Encourage **good architectural judgment**, not pattern overuse
- Serve as a **reference companion** for real-world system design

---

## 🧠 Who This Is For

- Backend engineers (Java / JVM ecosystem)
- Software architects and technical leads
- Developers preparing for **system design interviews**
- Anyone who wants **practical pattern fluency**, not just definitions

---

## 🛠 Tech Stack

- **Language:** Java (21+)
- **Build Tool:** Maven
- **Paradigm:** Object-Oriented Design
- **Testing:** JUnit (_where applicable_)

---

## 🚀 Quick Start

### Prerequisites
- Java 21+
- Maven 3.8+

### Build & Test
```bash
mvn clean test
```

### Import into IDE

Open the project as a Maven project using pom.xml in IntelliJ IDEA or Eclipse.

## 📚 Pattern Index

The repository is organized into **five root modules** to make it easier to navigate by intent:

| Category | Folder | What It Covers |
|----------|--------|----------------|
| GoF Patterns | [`gof-patterns/`](./gof-patterns) | Classic object-oriented patterns (creational/structural/behavioral) |
| Architectural Patterns | [`architectural-patterns/`](./architectural-patterns) | System- and layer-level structuring patterns |
| Enterprise Integration Patterns | [`enterprise-integration-patterns/`](./enterprise-integration-patterns) | Integration flows, pipelines, and message-oriented thinking |
| Reliability Patterns | [`reliability-patterns/`](./reliability-patterns) | Concurrency/robustness patterns and failure-mode handling |
| Miscellaneous Patterns | [`miscellaneous-patterns/`](./miscellaneous-patterns) | Useful patterns that don’t fit cleanly elsewhere |

### GoF Patterns

| Pattern | Folder | Typical Use Case |
|---------|--------|------------------|
| Abstract Factory | [`gof-patterns/abstract-factory/`](./gof-patterns/abstract-factory) | Families of related objects |
| Adapter | [`gof-patterns/adapter/`](./gof-patterns/adapter) | Interface compatibility |
| Bridge | [`gof-patterns/bridge/`](./gof-patterns/bridge) | Decoupling abstraction & implementation |
| Builder | [`gof-patterns/builder/`](./gof-patterns/builder) | Complex object construction |
| Chain of Responsibility | [`gof-patterns/chain/`](./gof-patterns/chain) | Request pipelines |
| Command | [`gof-patterns/command/`](./gof-patterns/command) | Action encapsulation |
| Composite | [`gof-patterns/composite/`](./gof-patterns/composite) | Tree structures |
| Decorator | [`gof-patterns/decorator/`](./gof-patterns/decorator) | Runtime behavior extension |
| Facade | [`gof-patterns/facade/`](./gof-patterns/facade) | Simplified subsystem access |
| Factory Method | [`gof-patterns/factory-method/`](./gof-patterns/factory-method) | Delegating object creation |
| Flyweight | [`gof-patterns/flyweight/`](./gof-patterns/flyweight) | Memory optimization |
| Interpreter | [`gof-patterns/interpreter/`](./gof-patterns/interpreter) | DSL-like grammars |
| Iterator | [`gof-patterns/iterator/`](./gof-patterns/iterator) | Collection traversal |
| Mediator | [`gof-patterns/mediator/`](./gof-patterns/mediator) | Interaction centralization |
| Memento | [`gof-patterns/memento/`](./gof-patterns/memento) | State snapshots |
| Observer | [`gof-patterns/observer/`](./gof-patterns/observer) | Event-driven updates |
| Prototype | [`gof-patterns/prototype/`](./gof-patterns/prototype) | Cloning costly objects |
| Proxy | [`gof-patterns/proxy/`](./gof-patterns/proxy) | Controlled access |
| Singleton | [`gof-patterns/singleton/`](./gof-patterns/singleton) | Controlled global access |
| State | [`gof-patterns/state/`](./gof-patterns/state) | State-driven behavior |
| Strategy | [`gof-patterns/strategy/`](./gof-patterns/strategy) | Algorithm selection |
| Template Method | [`gof-patterns/template-method/`](./gof-patterns/template-method) | Algorithm skeletons |
| Visitor | [`gof-patterns/visitor/`](./gof-patterns/visitor) | Operations on object graphs |

### Architectural Patterns

- Model-View-Presenter — [`architectural-patterns/model-view-presenter/`](./architectural-patterns/model-view-presenter)
- Service Locator — [`architectural-patterns/service-locator/`](./architectural-patterns/service-locator)
- State Machine — [`architectural-patterns/state-machine/`](./architectural-patterns/state-machine)

### Enterprise Integration Patterns

- Pipes & Filters — [`enterprise-integration-patterns/pipes-and-filters/`](./enterprise-integration-patterns/pipes-and-filters)

### Reliability Patterns

- Double-Checked Locking — [`reliability-patterns/double-checked-locking/`](./reliability-patterns/double-checked-locking)
- Circuit Breaker — [`reliability-patterns/circuit-breaker/`](./reliability-patterns/circuit-breaker)
- Null Object — [`reliability-patterns/nullobject/`](./reliability-patterns/nullobject)
- Rate Limiter — [`reliability-patterns/rate-limiter/`](./reliability-patterns/rate-limiter)
- Retry with Backoff — [`reliability-patterns/retry-backoff/`](./reliability-patterns/retry-backoff)
- Bulkhead — [`reliability-patterns/bulkhead/`](./reliability-patterns/bulkhead)
- Hedge Requests — [`reliability-patterns/hedge-requests/`](./reliability-patterns/hedge-requests)
- Timeout & Deadline Propagation — [`reliability-patterns/timeout-deadline-propagation/`](./reliability-patterns/timeout-deadline-propagation)
- Idempotency Keys — [`reliability-patterns/idempotency-keys/`](./reliability-patterns/idempotency-keys)

### Miscellaneous Patterns

- Criteria / Filter — [`miscellaneous-patterns/filter/`](./miscellaneous-patterns/filter)
- Lazy Sequence — [`miscellaneous-patterns/lazy-sequence/`](./miscellaneous-patterns/lazy-sequence)
- Method Object — [`miscellaneous-patterns/method-object/`](./miscellaneous-patterns/method-object)
- Servant — [`miscellaneous-patterns/servant/`](./miscellaneous-patterns/servant)

---

## 🧩 How to Read Each Pattern

For each pattern folder, focus on:

1. Intent — what problem is being solved 
2. Roles — participants and responsibilities 
3. Trade-offs — costs vs. benefits 
4. Alternatives — simpler options to consider

> Patterns are tools, not goals. Overuse is a design smell.

---

## 🧪 Testing Philosophy

- Patterns that demonstrate behavior include unit tests
- Tests focus on observable behavior, not implementation details
- Examples remain intentionally small and readable

---

## 🧭 Design Principles Emphasized

- SOLID principles
- Composition over inheritance
- Separation of concerns
- Dependency inversion
- Explicit design trade-offs

---

## 🛣 Roadmap

- [ ] More patterns
- [ ] Better formatted PDF Book
- [ ] Better formatted github-pages
- [ ] Visible Diagrams in PDF Book

---

## 🤝 Contributing

Contributions are welcome and encouraged!

- Improved examples with clearer intent
- Additional tests that demonstrate behavior
- Documentation clarifications
- New patterns with justification

see [CONTRIBUTING.md](./CONTRIBUTING.md) for details.

---

## 📜 License

This project is licensed under the [MIT License](./LICENSE) — free to use, modify, and distribute.
