package design.patterns.idempotency;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Idempotency keys: ensure "at most once" effect for a client-provided key.
 *
 * An example: prevent double-charging when purchasing in-game currency if the client retries.
 */
public final class IdempotencyKeys {
    public static Builder builder(String name) {
        return new Builder(name);
    }

    private record Entry(Instant expiresAt, CompletableFuture<Object> future) {}

    private final String name;
    private final Duration ttl;
    private final boolean removeOnFailure;
    private final Clock clock;
    private final ConcurrentHashMap<String, Entry> entries = new ConcurrentHashMap<>();

    private IdempotencyKeys(String name, Duration ttl, boolean removeOnFailure, Clock clock) {
        this.name = name;
        this.ttl = ttl;
        this.removeOnFailure = removeOnFailure;
        this.clock = clock;
    }

    public String name() {
        return name;
    }

    public <T> T execute(String key, Callable<T> operation) throws Exception {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(operation, "operation");
        if (key.isBlank()) {
            throw new IllegalArgumentException("key must not be blank");
        }

        while (true) {
            Entry existing = entries.get(key);
            if (existing != null) {
                if (isExpired(existing)) {
                    entries.remove(key, existing);
                    continue;
                }
                return await(existing);
            }

            Instant expiresAt = clock.instant().plus(ttl);
            Entry created = new Entry(expiresAt, new CompletableFuture<>());
            Entry prev = entries.putIfAbsent(key, created);
            if (prev != null) {
                continue;
            }

            try {
                T value = operation.call();
                created.future.complete(value);
                return value;
            } catch (Exception e) {
                created.future.completeExceptionally(e);
                if (removeOnFailure) {
                    entries.remove(key, created);
                }
                throw e;
            } catch (Throwable t) {
                created.future.completeExceptionally(t);
                if (removeOnFailure) {
                    entries.remove(key, created);
                }
                throw t;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T await(Entry entry) throws Exception {
        try {
            return (T) entry.future.get();
        } catch (java.util.concurrent.ExecutionException e) {
            Throwable c = e.getCause();
            if (c instanceof Exception ex) {
                throw ex;
            }
            throw e;
        }
    }

    private boolean isExpired(Entry entry) {
        return clock.instant().isAfter(entry.expiresAt);
    }

    public static final class Builder {
        private final String name;
        private Duration ttl = Duration.ofMinutes(10);
        private boolean removeOnFailure = true;
        private Clock clock = Clock.systemUTC();

        private Builder(String name) {
            this.name = Objects.requireNonNull(name, "name");
            if (name.isBlank()) {
                throw new IllegalArgumentException("name must not be blank");
            }
        }

        public Builder ttl(Duration ttl) {
            this.ttl = Objects.requireNonNull(ttl, "ttl");
            if (ttl.isZero() || ttl.isNegative()) {
                throw new IllegalArgumentException("ttl must be > 0");
            }
            return this;
        }

        public Builder removeOnFailure(boolean removeOnFailure) {
            this.removeOnFailure = removeOnFailure;
            return this;
        }

        public Builder clock(Clock clock) {
            this.clock = Objects.requireNonNull(clock, "clock");
            return this;
        }

        public IdempotencyKeys build() {
            return new IdempotencyKeys(name, ttl, removeOnFailure, clock);
        }
    }
}

