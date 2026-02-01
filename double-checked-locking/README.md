# Double-Checked Locking Pattern

## 📋 Overview

The **Double-Checked Locking** pattern is a thread-safe lazy initialization technique that minimizes synchronization overhead.

---

## 🎯 Intent

**Problem Solved:**
- Thread-safe lazy initialization
- Minimize synchronization overhead
- Defer object creation until needed
- Efficient repeated access

---

## 💡 Implementation

```java
public class DoubleCheckedLocking {
    private volatile static Instance instance;
    
    public static Instance getInstance() {
        if (instance == null) {
            synchronized(DoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }
}
```

---

## ⚠️ Considerations

- Requires volatile keyword
- Subtle synchronization issues
- Java Memory Model dependent
- Bill Pugh Singleton often better
- Enum Singleton recommended

---

## 📚 References

- Double-Checked Locking pattern
- Effective Java Chapter on Lazy Initialization
