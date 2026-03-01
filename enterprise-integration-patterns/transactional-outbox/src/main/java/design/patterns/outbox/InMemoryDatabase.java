package design.patterns.outbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Minimal in-memory "database" with an outbox.
 *
 * In production: use a real DB transaction to atomically write domain rows + outbox rows.
 */
public final class InMemoryDatabase {
    public record Order(String orderId, String playerId, int coins) {}

    public record OutboxMessage(String messageId, String topic, String payload) {}

    private final ConcurrentHashMap<String, Order> orders = new ConcurrentHashMap<>();
    private final List<OutboxMessage> outbox = new ArrayList<>();

    public Transaction begin() {
        return new Transaction(this);
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    List<OutboxMessage> drainOutboxBatch(int max) {
        synchronized (outbox) {
            int n = Math.min(max, outbox.size());
            List<OutboxMessage> batch = new ArrayList<>(outbox.subList(0, n));
            outbox.subList(0, n).clear();
            return batch;
        }
    }

    private void commit(Transaction tx) {
        for (Order o : tx.pendingOrders) {
            orders.put(o.orderId(), o);
        }
        synchronized (outbox) {
            outbox.addAll(tx.pendingOutbox);
        }
    }

    public static final class Transaction {
        private final InMemoryDatabase db;
        private boolean committed = false;
        private final List<Order> pendingOrders = new ArrayList<>();
        private final List<OutboxMessage> pendingOutbox = new ArrayList<>();

        private Transaction(InMemoryDatabase db) {
            this.db = db;
        }

        public void insertOrder(String playerId, int coins) {
            Objects.requireNonNull(playerId, "playerId");
            String orderId = UUID.randomUUID().toString();
            pendingOrders.add(new Order(orderId, playerId, coins));
        }

        public void addOutbox(String topic, String payload) {
            Objects.requireNonNull(topic, "topic");
            Objects.requireNonNull(payload, "payload");
            pendingOutbox.add(new OutboxMessage(UUID.randomUUID().toString(), topic, payload));
        }

        public void commit() {
            if (committed) {
                throw new IllegalStateException("Already committed");
            }
            db.commit(this);
            committed = true;
        }
    }
}

