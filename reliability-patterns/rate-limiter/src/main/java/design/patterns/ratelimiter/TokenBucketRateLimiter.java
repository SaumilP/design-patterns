package design.patterns.ratelimiter;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.LongSupplier;

/**
 * Token bucket rate limiter.
 *
 * Typical use-case (gaming): limit "search players" queries per player to protect matchmaking and leaderboards.
 */
public final class TokenBucketRateLimiter {
    public interface Sleeper {
        void sleep(Duration duration) throws InterruptedException;
    }

    public static Builder builder(String name) {
        return new Builder(name);
    }

    private final String name;
    private final int capacity;
    private final int refillTokens;
    private final long refillPeriodNanos;
    private final LongSupplier nanoTime;
    private final Sleeper sleeper;

    private final ReentrantLock lock = new ReentrantLock();
    private int availableTokens;
    private long lastRefillNanos;

    private TokenBucketRateLimiter(
            String name,
            int capacity,
            int refillTokens,
            Duration refillPeriod,
            LongSupplier nanoTime,
            Sleeper sleeper
    ) {
        this.name = name;
        this.capacity = capacity;
        this.refillTokens = refillTokens;
        this.refillPeriodNanos = refillPeriod.toNanos();
        this.nanoTime = nanoTime;
        this.sleeper = sleeper;
        this.availableTokens = capacity;
        this.lastRefillNanos = nanoTime.getAsLong();
    }

    public String name() {
        return name;
    }

    public boolean tryAcquire() {
        lock.lock();
        try {
            refillIfNeeded();
            if (availableTokens <= 0) {
                return false;
            }
            availableTokens--;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean tryAcquire(Duration timeout) throws InterruptedException {
        Objects.requireNonNull(timeout, "timeout");
        long deadline = nanoTime.getAsLong() + timeout.toNanos();

        while (nanoTime.getAsLong() < deadline) {
            if (tryAcquire()) {
                return true;
            }
            Duration sleepFor = timeUntilNextToken();
            if (sleepFor.isZero()) {
                continue;
            }
            sleeper.sleep(sleepFor);
        }
        return false;
    }

    public void acquire() throws InterruptedException {
        while (!tryAcquire(Duration.ofDays(3650))) {
            // tryAcquire will only return false on timeout; this is practically unbounded.
        }
    }

    public <T> T execute(Callable<T> operation, Duration timeout) throws Exception {
        Objects.requireNonNull(operation, "operation");
        if (!tryAcquire(timeout)) {
            throw new RateLimitedException(name);
        }
        return operation.call();
    }

    private void refillIfNeeded() {
        long now = nanoTime.getAsLong();
        long elapsed = now - lastRefillNanos;
        if (elapsed <= 0) {
            return;
        }
        long periods = elapsed / refillPeriodNanos;
        if (periods <= 0) {
            return;
        }
        long toAdd = periods * (long) refillTokens;
        availableTokens = (int) Math.min((long) capacity, (long) availableTokens + toAdd);
        lastRefillNanos += periods * refillPeriodNanos;
    }

    private Duration timeUntilNextToken() {
        lock.lock();
        try {
            refillIfNeeded();
            if (availableTokens > 0) {
                return Duration.ZERO;
            }
            long now = nanoTime.getAsLong();
            long elapsed = now - lastRefillNanos;
            long remaining = refillPeriodNanos - (elapsed % refillPeriodNanos);
            if (remaining <= 0) {
                return Duration.ZERO;
            }
            return Duration.ofNanos(Math.min(remaining, TimeUnit.MILLISECONDS.toNanos(50)));
        } finally {
            lock.unlock();
        }
    }

    public static final class Builder {
        private final String name;
        private int capacity = 10;
        private int refillTokens = 10;
        private Duration refillPeriod = Duration.ofSeconds(1);
        private LongSupplier nanoTime = System::nanoTime;
        private Sleeper sleeper = d -> Thread.sleep(d.toMillis());

        private Builder(String name) {
            this.name = Objects.requireNonNull(name, "name");
            if (name.isBlank()) {
                throw new IllegalArgumentException("name must not be blank");
            }
        }

        public Builder capacity(int capacity) {
            if (capacity < 1) {
                throw new IllegalArgumentException("capacity must be >= 1");
            }
            this.capacity = capacity;
            return this;
        }

        public Builder refill(int tokens, Duration period) {
            if (tokens < 1) {
                throw new IllegalArgumentException("tokens must be >= 1");
            }
            this.refillTokens = tokens;
            this.refillPeriod = Objects.requireNonNull(period, "period");
            if (period.isZero() || period.isNegative()) {
                throw new IllegalArgumentException("period must be > 0");
            }
            return this;
        }

        public Builder nanoTime(LongSupplier nanoTime) {
            this.nanoTime = Objects.requireNonNull(nanoTime, "nanoTime");
            return this;
        }

        public Builder sleeper(Sleeper sleeper) {
            this.sleeper = Objects.requireNonNull(sleeper, "sleeper");
            return this;
        }

        public TokenBucketRateLimiter build() {
            return new TokenBucketRateLimiter(name, capacity, refillTokens, refillPeriod, nanoTime, sleeper);
        }
    }
}

