package design.patterns.cqrs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory event store. In production this would be an append-only durable log.
 */
public final class EventStore {
    private final ConcurrentHashMap<String, List<DomainEvent>> byAggregate = new ConcurrentHashMap<>();

    public void append(DomainEvent event) {
        Objects.requireNonNull(event, "event");
        byAggregate.compute(event.aggregateId(), (id, existing) -> {
            List<DomainEvent> next = existing == null ? new ArrayList<>() : new ArrayList<>(existing);
            next.add(event);
            return Collections.unmodifiableList(next);
        });
    }

    public List<DomainEvent> read(String aggregateId) {
        Objects.requireNonNull(aggregateId, "aggregateId");
        return byAggregate.getOrDefault(aggregateId, List.of());
    }
}

