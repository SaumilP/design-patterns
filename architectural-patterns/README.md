# Architectural Patterns

Architectural patterns describe **system-level structure**: how responsibilities are divided across layers, components, and flows.

Unlike most GoF patterns (which focus on object collaboration), architectural patterns shape:
- dependency direction and boundaries
- ownership of state and side effects
- integration points and contracts
- where complexity is allowed to live

---

## 🎯 When To Use Architectural Patterns

Use them when:
- you need a consistent approach across a larger codebase (teams, modules, services)
- you want to prevent coupling from spreading unchecked
- you are designing for testability and change over time

Avoid them when:
- the system is small and a heavy structure will slow iteration
- the pattern creates “ceremony” without improving correctness or clarity
- you cannot enforce the boundaries (architecture without enforcement decays quickly)

---

## 🧭 What To Evaluate

- How does data flow through the system?
- Where do dependencies point?
- What is the unit of testability?
- How do you replace a component without rewriting others?

---

## 🧭 Included Patterns

- Model-View-Presenter — [`model-view-presenter/`](./model-view-presenter)
- Service Locator — [`service-locator/`](./service-locator)
- State Machine — [`state-machine/`](./state-machine)
