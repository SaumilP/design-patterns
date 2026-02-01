# State Pattern

## 📋 Overview

The **State** pattern allows an object to alter its behavior when its internal state changes. The object will appear to change its class.

---

## 🎯 Intent

**Problem Solved:**
- Object behavior varies based on state
- Large conditional statements checking state
- State transitions need management

---

## 👥 Roles & Responsibilities

| Role | Responsibility |
|------|-----------------|
| Context | Defines interface, delegates to State |
| State | Defines interface for state-specific behavior |
| ConcreteState | Implements state-specific behavior |

---

## 💡 Code Example

```java
public interface State {
    void handle(Context context);
}

public class Context {
    private State state;
    
    public Context(State state) {
        this.state = state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public void request() {
        state.handle(this);
    }
}

public class ConcreteStateA implements State {
    @Override
    public void handle(Context context) {
        System.out.println("State A handling");
        context.setState(new ConcreteStateB());
    }
}

// Usage
Context context = new Context(new ConcreteStateA());
context.request();
context.request();
```

**Reasoning:** Encapsulates state-dependent behavior; enables state-specific transitions.

---

## ⚖️ Trade-offs

### Advantages ✅
- Eliminates large conditional statements
- Localizes state-specific behavior
- Easy to add new states
- State transitions explicit
- Supports complex state machines

### Disadvantages ❌
- Increased number of classes
- More complex than simple conditionals
- State dependency coupling
- Increased memory usage
- Performance overhead

---

## 🌍 Real-World Use Cases

- Workflow engines
- State machines
- Game object states
- TCP connection states
- Order processing workflows

---

## 📚 References

- Gang of Four Design Patterns
- State Machine Design
