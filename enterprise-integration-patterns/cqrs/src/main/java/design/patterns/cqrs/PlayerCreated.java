package design.patterns.cqrs;

import java.time.Instant;
import java.util.Objects;

public record PlayerCreated(String aggregateId, String displayName, Instant occurredAt) implements DomainEvent {
    public PlayerCreated {
        Objects.requireNonNull(aggregateId, "aggregateId");
        Objects.requireNonNull(displayName, "displayName");
        Objects.requireNonNull(occurredAt, "occurredAt");
    }
}

