package design.patterns.cqrs;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Query side: optimized for reads and updated by projecting events.
 */
public final class PlayerReadModel {
    public record PlayerView(String playerId, String displayName) {}

    private final ConcurrentHashMap<String, PlayerView> players = new ConcurrentHashMap<>();

    public void apply(DomainEvent event) {
        Objects.requireNonNull(event, "event");
        if (event instanceof PlayerCreated e) {
            players.put(e.aggregateId(), new PlayerView(e.aggregateId(), e.displayName()));
        } else if (event instanceof PlayerDisplayNameChanged e) {
            players.computeIfPresent(e.aggregateId(), (id, prev) -> new PlayerView(id, e.newDisplayName()));
        }
    }

    public Optional<PlayerView> get(String playerId) {
        return Optional.ofNullable(players.get(playerId));
    }
}

