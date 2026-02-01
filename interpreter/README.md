# Interpreter Pattern

## 📋 Overview

The **Interpreter** pattern defines a grammatical representation for a language and an interpreter to interpret sentences in that language.

---

## 🎯 Intent

**Problem Solved:**
- Define grammar for domain-specific language
- Parse and execute expressions
- Build abstract syntax trees
- Evaluate language sentences

---

## 👥 Roles & Responsibilities

| Role | Responsibility |
|------|-----------------|
| AbstractExpression | Defines interpret interface |
| TerminalExpression | Implements primitive expressions |
| NonTerminalExpression | Implements composite expressions |
| Context | Global information for interpreter |

---

## 💡 Implementation

- Grammar defined by class hierarchy
- Each expression type is a class
- interpret() evaluates expression
- Recursive interpretation of tree

---

## ⚖️ Trade-offs

### Advantages ✅
- Easy to modify grammar
- Grammar in code form
- Extensible expressions
- Recursive evaluation
- Natural language representation

### Disadvantages ❌
- Complex for large grammars
- Performance overhead
- Many classes required
- Difficult to parse
- Memory consumption

---

## 🌍 Real-World Use Cases

- Expression evaluators
- DSL (Domain-Specific Language) implementation
- SQL query processing
- Regular expression engines
- Configuration file parsing

---

## 📚 References

- Gang of Four Design Patterns
- Language design patterns
