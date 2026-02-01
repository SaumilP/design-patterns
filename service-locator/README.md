# Service Locator Pattern

## 📋 Overview

The **Service Locator** pattern centralizes logic for creating and accessing services, providing a single point for service instantiation and discovery.

---

## 🎯 Intent

**Problem Solved:**
- Decouple service creation from usage
- Provide single service access point
- Enable service substitution
- Centralize service configuration

---

## 👥 Roles & Responsibilities

| Role | Responsibility |
|------|-----------------|
| ServiceLocator | Provides service access |
| Service | Defines service interface |
| ServiceImpl | Concrete service implementation |

---

## ⚠️ Note

Service Locator is often considered an anti-pattern. **Dependency Injection** is the preferred modern approach.

---

## ⚖️ Trade-offs

### Advantages ✅
- Centralized service management
- Easy service substitution
- Decouples client from creation
- Dynamic service loading

### Disadvantages ❌
- Hidden dependencies
- Testing complexity
- Global state issues
- Service locator coupling
- Anti-pattern warning

---

## 🌍 Real-World Use Cases

- Legacy application service management
- Plugin systems
- Dynamic service discovery
- Configuration-driven services

---

## 📚 References

- Martin Fowler Service Locator
- Dependency Injection advantages
