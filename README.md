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

- **Language:** Java (11+)
- **Build Tool:** Maven
- **Paradigm:** Object-Oriented Design
- **Testing:** JUnit (_where applicable_)

---

## 🚀 Quick Start

### Prerequisites
- Java 11+ (Java 17/21 recommended)
- Maven 3.8+

### Build & Test
```bash
mvn clean test
```

### Import into IDE

Open the project as a Maven project using pom.xml in IntelliJ IDEA or Eclipse.

## 📚 Pattern Index

### Creational Patterns

| Pattern          | Folder              | Typical Use Case            |
|------------------|---------------------|-----------------------------|
| Abstract Factory | `abstract-factory/` | Families of related objects |
| Builder          | `builder/`          | Complex object construction |
| Factory Method   | `factory-method/`   | Delegating object creation  |
| Prototype        | `prototype/`        | Cloning costly objects      |
| Singleton        | `singleton/`        | Controlled global access    |

### Structural Patterns

| Pattern   | Folder       | Typical Use Case              |
|-----------|--------------|-------------------------------|
| Adapter   | `adapter/`   | Interface compatibility       |
| Bridge    | `bridge/`    | Decoupling abstraction & impl |
| Composite | `composite/` | Tree structures               |
| Decorator | `decorator/` | Runtime behavior extension    |
| Facade    | `facade/`    | Simplified subsystem access   |
| Flyweight | `flyweight/` | Memory optimization           |
| Proxy     | `proxy/`     | Controlled access             |


### Behavioral Patterns

| Pattern                 | Folder                  | Typical Use Case            |
|-------------------------|-------------------------|-----------------------------|
| Chain of Responsibility | `chain/`                | Request pipelines           |
| Command                 | `command/`              | Action encapsulation        |
| Interpreter             | `interpreter/`          | DSL-like grammars           |
| Iterator                | `iterator/`             | Collection traversal        |
| Mediator                | `mediator/`             | Interaction centralization  |
| Memento                 | `memento/`              | State snapshots             |
| Observer                | `observer/`             | Event-driven updates        |
| State                   | `state/`                | Workflow/state machines     |
| Strategy                | `strategy/`             | Algorithm selection         |
| Template Method         | `template-method/`      | Algorithm skeletons         |
| Visitor                 | `visitor/`              | Operations on object graphs |
| MVP                     | `model-view-presenter/` | Presentation separation     |

### Hybrid / Reference Patterns

- Finite State Machine — `state-machine/`
- Pipes & Filters — `pipes-and-filters/`
- Service Locator — `service-locator/`
- Double-Checked Locking — `double-checked-locking/`
- Method Object — `method-object/`

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

- [ ] Add a short README.md inside each pattern folder 
- [ ] Add UML-style diagrams (Mermaid) per pattern
- [ ] Add pattern comparison guide (e.g. Strategy vs State vs Command)
- [ ] Enforce formatting & static analysis
- [ ] Publish tagged releases (v1.x)

---

## 🤝 Contributing

Contributions are welcome and encouraged.

**Suggested contributions**:

- Improved examples with clearer intent
- Additional tests that demonstrate behavior
- Documentation clarifications
- New patterns with justification

**Workflow**:

1. Fork the repository 
2. Create a branch (feat/<pattern-name> or docs/<improvement>)
3. Commit focused changes 
4. Open a PR with rationale and trade-offs

---

## 📜 License

This project is licensed under the MIT License — free to use, modify, and distribute.