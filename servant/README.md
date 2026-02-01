# Servant Pattern

## 📋 Overview

The **Servant** pattern defines common functionality in a separate class (servant) that serves multiple classes, enabling code reuse without inheritance.

---

## 🎯 Intent

**Problem Solved:**
- Provide common functionality to multiple unrelated classes
- Avoid code duplication without inheritance
- Reduce class hierarchy complexity
- Enable flexible behavior sharing

---

## 👥 Roles & Responsibilities

| Role | Responsibility |
|------|-----------------|
| Servant | Provides common functionality |
| Served Classes | Use servant functionality |
| Client | Initiates servant behavior |

---

## 💡 Implementation

- Servant contains common operations
- Served classes delegate to servant
- Enables composition over inheritance
- No inheritance relationship required

---

## ⚖️ Trade-offs

### Advantages ✅
- Code reuse without inheritance
- Flexible behavior sharing
- Avoids multiple inheritance
- Clean separation of concerns
- Reusable servant components

### Disadvantages ❌
- Additional object dependencies
- Parameter passing complexity
- Servant state management
- Performance overhead
- Design complexity

---

## 🌍 Real-World Use Cases

- Utility classes
- Shared behavior across unrelated classes
- Cross-cutting concerns
- Aspect-oriented design
- Mixin functionality

---

## 📚 References

- Servant Pattern
- Design Patterns in Practice
