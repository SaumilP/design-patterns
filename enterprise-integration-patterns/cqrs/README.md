# CQRS Pattern (Command Query Responsibility Segregation)

## 📋 Overview

**CQRS** separates the **write model** (commands) from the **read model** (queries).
Writes typically emit **events**; reads are served from a projection optimized for queries.

An example: player profile writes (rename) emit events; profile reads come from a query store.

---

## 🎯 Intent

- Scale reads independently from writes
- Keep write-side rules strict while read-side stays fast and flexible
- Enable event-driven projections and eventually consistent read models

---

## 💡 Code Example

```java
commands.createPlayer("player-42", "RogueMage");
commands.changeDisplayName("player-42", "RogueMage_2");

projector.projectAggregate("player-42");
var view = readModel.get("player-42").orElseThrow();
```

---

## 📊 Class Diagram

```mermaid
classDiagram
    class PlayerCommandService {
        +createPlayer(id, name)
        +changeDisplayName(id, name)
    }
    class EventStore {
        +append(event)
        +read(aggregateId) List~DomainEvent~
    }
    class Projector {
        +projectAggregate(aggregateId)
    }
    class PlayerReadModel {
        +apply(event)
        +get(id) PlayerView?
    }
    class DomainEvent {
        <<sealed>>
        +aggregateId()
        +occurredAt()
    }
    class PlayerCreated
    class PlayerDisplayNameChanged

    PlayerCommandService --> EventStore : append events
    Projector --> EventStore : read events
    Projector --> PlayerReadModel : apply events
    DomainEvent <|-- PlayerCreated
    DomainEvent <|-- PlayerDisplayNameChanged
```

---

## 🔄 Sequence Diagram

```mermaid
sequenceDiagram
    actor Client
    participant Cmd as PlayerCommandService
    participant Store as EventStore
    participant Proj as Projector
    participant Read as PlayerReadModel

    Client->>Cmd: changeDisplayName(playerId, newName)
    Cmd->>Store: append(PlayerDisplayNameChanged)
    Store-->>Cmd: ok

    Client->>Proj: projectAggregate(playerId)
    Proj->>Store: read(playerId)
    Store-->>Proj: events
    loop events
        Proj->>Read: apply(event)
    end
    Client->>Read: get(playerId)
    Read-->>Client: PlayerView
```

---

## ⚖️ Trade-offs

### Advantages ✅

- Great for read-heavy systems (leaderboards, profiles)
- Clear separation of concerns
- Works naturally with event sourcing (optional)

### Disadvantages ❌

- Eventual consistency between write and read sides
- Projection code becomes part of your critical path
