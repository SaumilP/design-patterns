# Template Method Pattern

## 📋 Overview

The **Template Method** pattern defines the skeleton of an algorithm in a base class, letting subclasses override specific steps.

---

## 🎯 Intent

**Problem Solved:**
- Algorithm structure is fixed, but steps vary
- Avoid code duplication in algorithm implementations
- Control subclass overrides

---

## 👥 Roles & Responsibilities

| Role | Responsibility |
|------|-----------------|
| AbstractClass | Defines template method and steps |
| ConcreteClass | Implements specific steps |

---

## 💡 Code Example

```java
public abstract class DataProcessor {
    public final void process() {
        readData();
        validateData();
        processData();
        writeData();
    }
    
    abstract void readData();
    abstract void validateData();
    abstract void processData();
    abstract void writeData();
}

public class XMLProcessor extends DataProcessor {
    @Override
    void readData() { /* XML reading */ }
    
    @Override
    void validateData() { /* XML validation */ }
    
    @Override
    void processData() { /* XML processing */ }
    
    @Override
    void writeData() { /* XML writing */ }
}

// Usage
DataProcessor processor = new XMLProcessor();
processor.process();
```

**Reasoning:** Defines algorithm structure; lets subclasses implement steps; prevents duplication.

---

## ⚖️ Trade-offs

### Advantages ✅
- Code reuse
- Controls extension points
- Defines skeleton once
- Consistent algorithm structure
- Promotes inheritance

### Disadvantages ❌
- Inheritance hierarchy required
- Tight coupling between base and derived
- Hollywood Principle violation possible
- Inflexible for diverse algorithms
- Violates Composition over Inheritance

---

## 🌍 Real-World Use Cases

- Framework hook methods
- Stream processing pipelines
- Database connection pooling
- Junit test setup/teardown
- Application lifecycle methods

---

## 📚 References

- Gang of Four Design Patterns
- Template Method variations
