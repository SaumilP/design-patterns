package design.patterns.cqrs;

import java.util.List;
import java.util.Objects;

/**
 * Simplest projector: replay all events for an aggregate into the read model.
 */
public final class Projector {
    private final EventStore store;
    private final PlayerReadModel readModel;

    public Projector(EventStore store, PlayerReadModel readModel) {
        this.store = Objects.requireNonNull(store, "store");
        this.readModel = Objects.requireNonNull(readModel, "readModel");
    }

    public void projectAggregate(String aggregateId) {
        List<DomainEvent> events = store.read(aggregateId);
        for (DomainEvent e : events) {
            readModel.apply(e);
        }
    }
}

