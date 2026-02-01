# Filter Pattern

## 📋 Overview

The **Filter** pattern (or Criteria pattern) provides a way to filter collections of objects using different criteria in a flexible and reusable manner.

---

## 🎯 Intent

**Problem Solved:**
- Filter collections by different criteria
- Combine multiple filter criteria
- Avoid multiple if-else statements
- Enable reusable filter components

---

## 👥 Roles & Responsibilities

| Role | Responsibility |
|------|-----------------|
| Criteria | Defines filtering interface |
| ConcreteCriteria | Implements specific filter logic |
| Filter | Applies criteria to collection |

---

## 💡 Implementation

- Each filter criterion is a separate component
- Filters can be combined/chained
- Enables dynamic filtering logic
- Reusable and testable filters

---

## ⚖️ Trade-offs

### Advantages ✅
- Flexible filtering logic
- Reusable criteria
- Easy combination of filters
- Clear separation of concerns
- Testable filter logic

### Disadvantages ❌
- Additional classes required
- Performance with many filters
- Memory overhead for criteria objects
- Learning curve
- Over-engineering for simple cases

---

## 🌍 Real-World Use Cases

- Database query builders
- Collection filtering
- Stream filtering
- Search functionality
- Data validation chains

---

## 📚 References

- Criteria Pattern
- Chain of Responsibility Pattern
