package design.patterns.cqrs;

import java.time.Instant;
import java.util.Objects;

public record PlayerDisplayNameChanged(String aggregateId, String newDisplayName, Instant occurredAt) implements DomainEvent {
    public PlayerDisplayNameChanged {
        Objects.requireNonNull(aggregateId, "aggregateId");
        Objects.requireNonNull(newDisplayName, "newDisplayName");
        Objects.requireNonNull(occurredAt, "occurredAt");
    }
}

