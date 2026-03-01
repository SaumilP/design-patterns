package design.patterns.outbox;

import java.util.List;
import java.util.Objects;

/**
 * Polls the outbox and publishes messages to the broker.
 *
 * In production: run continuously, store delivery state, and make publishing idempotent.
 */
public final class OutboxPublisher {
    private final InMemoryDatabase db;
    private final InMemoryMessageBroker broker;

    public OutboxPublisher(InMemoryDatabase db, InMemoryMessageBroker broker) {
        this.db = Objects.requireNonNull(db, "db");
        this.broker = Objects.requireNonNull(broker, "broker");
    }

    public int publishOnce(int maxBatch) {
        List<InMemoryDatabase.OutboxMessage> batch = db.drainOutboxBatch(maxBatch);
        for (InMemoryDatabase.OutboxMessage msg : batch) {
            broker.publish(msg.topic(), msg.payload());
        }
        return batch.size();
    }
}

