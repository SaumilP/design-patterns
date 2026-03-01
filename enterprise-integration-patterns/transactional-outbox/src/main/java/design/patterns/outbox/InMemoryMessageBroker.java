package design.patterns.outbox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Minimal in-memory broker that keeps published messages.
 */
public final class InMemoryMessageBroker {
    private final ConcurrentHashMap<String, List<String>> topics = new ConcurrentHashMap<>();

    public void publish(String topic, String payload) {
        topics.compute(topic, (t, existing) -> {
            List<String> next = existing == null ? new ArrayList<>() : new ArrayList<>(existing);
            next.add(payload);
            return List.copyOf(next);
        });
    }

    public List<String> readTopic(String topic) {
        return topics.getOrDefault(topic, List.of());
    }
}

