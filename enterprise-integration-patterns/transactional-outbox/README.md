# Transactional Outbox Pattern

## 📋 Overview

The **Transactional Outbox** pattern ensures that when your service writes domain data, it also writes the integration event to an **outbox table** in the **same database transaction**.
A separate publisher later reads the outbox and publishes events to a broker.

An example: buying coins writes an order + emits `coins.purchased` without losing events during crashes.

---

## 🎯 Intent

- Prevent “write succeeded but event publish failed” inconsistencies
- Ensure at-least-once publication with a recoverable outbox

---

## 💡 Code Example

```java
var tx = db.begin();
tx.insertOrder(playerId, coins);
tx.addOutbox("coins.purchased", jsonPayload);
tx.commit();

publisher.publishOnce(100);
```

---

## 📊 Class Diagram

```mermaid
classDiagram
    class PurchaseService
    class InMemoryDatabase
    class Transaction
    class OutboxPublisher
    class InMemoryMessageBroker
    PurchaseService --> InMemoryDatabase : begin tx
    Transaction --> InMemoryDatabase : commit
    OutboxPublisher --> InMemoryDatabase : drain outbox
    OutboxPublisher --> InMemoryMessageBroker : publish
```

---

## 🔄 Sequence Diagram

```mermaid
sequenceDiagram
    actor Client
    participant Service as PurchaseService
    participant DB as Database
    participant Outbox as OutboxTable
    participant Pub as OutboxPublisher
    participant Broker as MessageBroker

    Client->>Service: purchaseCoins(playerId, coins)
    Service->>DB: BEGIN
    Service->>DB: INSERT Order
    Service->>Outbox: INSERT OutboxMessage
    Service->>DB: COMMIT

    Pub->>Outbox: poll batch
    Outbox-->>Pub: messages
    Pub->>Broker: publish(topic, payload)
```

---

## ⚖️ Trade-offs

### Advantages ✅
- Strong consistency between domain write and event record
- Simple recovery: publish from outbox after restart

### Disadvantages ❌
- Requires polling/streaming outbox and operational tooling
- Needs idempotent publishing/consumers (at-least-once)
