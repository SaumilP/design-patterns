# Rate Limiter Pattern

## 📋 Overview

The **Rate Limiter** pattern controls how frequently an operation is allowed to run. It protects shared resources from spikes and prevents a single client from monopolizing capacity.

An example: limit **leaderboard refresh** or **matchmaking search** calls per player to reduce load.

---

## 🎯 Intent

**Problem Solved:**
- Prevent overload caused by bursts (client retries, bot traffic, thundering herds)
- Enforce fairness per key (player, session, API key, tenant)
- Provide predictable capacity planning and cost control

---

## 💡 Code Example

```java
var limiter = TokenBucketRateLimiter.builder("leaderboard-refresh")
    .capacity(5)
    .refill(5, Duration.ofSeconds(1))
    .build();

limiter.execute(() -> refreshLeaderboard(), Duration.ofMillis(10));
```

---

## 📊 Class Diagram

```mermaid
classDiagram
    class TokenBucketRateLimiter {
        +builder(name) Builder
        +tryAcquire() boolean
        +tryAcquire(timeout) boolean
        +execute(op, timeout) T
    }
    class Builder {
        +capacity(n) Builder
        +refill(tokens, period) Builder
        +build() TokenBucketRateLimiter
    }
    class RateLimitedException
    Builder ..> TokenBucketRateLimiter : builds
    TokenBucketRateLimiter ..> RateLimitedException : throws
```

![TokenBucketRateLimiter class diagram](rate-limiter-class-diagram.png)

---

## 🔄 Sequence Diagram

```mermaid
sequenceDiagram
    actor Client
    participant RL as RateLimiter

    Client->>RL: tryAcquire()
    alt tokens available
        RL-->>Client: true
        Client->>RL: execute(op)
        RL-->>Client: result
    else empty
        RL-->>Client: false
    end
```

---

## ⚖️ Trade-offs

### Advantages ✅
- Protects systems from bursts and abuse
- Improves fairness across clients
- Reduces tail latency under load

### Disadvantages ❌
- Requires tuning (capacity, refill rate)
- Needs a strategy per-key and storage (in-memory vs distributed)
