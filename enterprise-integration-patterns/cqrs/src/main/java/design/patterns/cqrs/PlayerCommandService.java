package design.patterns.cqrs;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;

/**
 * Command side: validates intent and emits events.
 */
public final class PlayerCommandService {
    private final EventStore store;
    private final Clock clock;

    public PlayerCommandService(EventStore store, Clock clock) {
        this.store = Objects.requireNonNull(store, "store");
        this.clock = Objects.requireNonNull(clock, "clock");
    }

    public void createPlayer(String playerId, String displayName) {
        if (playerId == null || playerId.isBlank()) {
            throw new IllegalArgumentException("playerId must not be blank");
        }
        if (displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("displayName must not be blank");
        }
        Instant now = clock.instant();
        store.append(new PlayerCreated(playerId, displayName, now));
    }

    public void changeDisplayName(String playerId, String newDisplayName) {
        if (playerId == null || playerId.isBlank()) {
            throw new IllegalArgumentException("playerId must not be blank");
        }
        if (newDisplayName == null || newDisplayName.isBlank()) {
            throw new IllegalArgumentException("newDisplayName must not be blank");
        }
        Instant now = clock.instant();
        store.append(new PlayerDisplayNameChanged(playerId, newDisplayName, now));
    }
}

