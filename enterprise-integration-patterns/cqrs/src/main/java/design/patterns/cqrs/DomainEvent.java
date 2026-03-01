package design.patterns.cqrs;

import java.time.Instant;

public sealed interface DomainEvent permits PlayerCreated, PlayerDisplayNameChanged {
    String aggregateId();
    Instant occurredAt();
}

