# GoF Patterns (Gang of Four)

The **Gang of Four (GoF)** patterns are the classic, foundational design patterns described in *Design Patterns: Elements of Reusable Object-Oriented Software* (Gamma, Helm, Johnson, Vlissides).

They are not frameworks or libraries. They are **reusable collaboration shapes** between objects that help you:
- reduce coupling
- make change cheaper
- express intent in code
- avoid ad-hoc, inconsistent designs

---

## 🎯 When To Use GoF Patterns

Use them when:
- requirements are expected to change (new variants, new behaviors, new object families)
- you want to isolate volatile decisions (creation strategy, algorithm choice, request routing)
- you need to keep dependencies one-directional and explicit

Avoid them when:
- a simple function, small class, or direct composition is enough
- you are “pattern-matching” instead of solving a real problem
- the pattern introduces more indirection than the change you are trying to accommodate

> Patterns are tools, not goals. If the code becomes harder to read, you probably applied too much structure.

---

## 🧩 Common Groups

GoF patterns are commonly grouped as:
- **Creational:** control object creation (e.g., Builder, Abstract Factory)
- **Structural:** compose types and relationships (e.g., Adapter, Decorator)
- **Behavioral:** distribute responsibilities and algorithms (e.g., Strategy, Observer)

This repo keeps **each pattern in its own folder** as a runnable Maven module.

---

## 📚 What To Look For In Each Example

- **Intent:** what problem the pattern solves
- **Roles:** who collaborates with whom
- **Trade-offs:** what you pay for flexibility
- **Alternatives:** simpler options that may be better

---

## 🧭 Included Patterns

### Creational

- Abstract Factory — [`abstract-factory/`](./abstract-factory)
- Builder — [`builder/`](./builder)
- Factory Method — [`factory-method/`](./factory-method)
- Prototype — [`prototype/`](./prototype)
- Singleton — [`singleton/`](./singleton)

### Structural

- Adapter — [`adapter/`](./adapter)
- Bridge — [`bridge/`](./bridge)
- Composite — [`composite/`](./composite)
- Decorator — [`decorator/`](./decorator)
- Facade — [`facade/`](./facade)
- Flyweight — [`flyweight/`](./flyweight)
- Proxy — [`proxy/`](./proxy)

### Behavioral

- Chain of Responsibility — [`chain/`](./chain)
- Command — [`command/`](./command)
- Interpreter — [`interpreter/`](./interpreter)
- Iterator — [`iterator/`](./iterator)
- Mediator — [`mediator/`](./mediator)
- Memento — [`memento/`](./memento)
- Observer — [`observer/`](./observer)
- State — [`state/`](./state)
- Strategy — [`strategy/`](./strategy)
- Template Method — [`template-method/`](./template-method)
- Visitor — [`visitor/`](./visitor)
