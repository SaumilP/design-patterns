package design.patterns.circuitbreaker;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A small, thread-safe Circuit Breaker implementation with the classic states:
 * CLOSED -> OPEN -> HALF_OPEN -> CLOSED.
 *
 * This implementation uses consecutive failures to open and consecutive successes to close (from HALF_OPEN).
 */
public final class CircuitBreaker {
    public enum State {
        CLOSED,
        OPEN,
        HALF_OPEN,
    }

    public static Builder builder(String name) {
        return new Builder(name);
    }

    private final String name;
    private final int failureThreshold;
    private final Duration openDuration;
    private final int halfOpenMaxCalls;
    private final int halfOpenSuccessThreshold;
    private final Clock clock;

    private final ReentrantLock transitionLock = new ReentrantLock();
    private final AtomicInteger consecutiveFailures = new AtomicInteger(0);
    private final AtomicInteger consecutiveHalfOpenSuccesses = new AtomicInteger(0);
    private volatile State state = State.CLOSED;
    private volatile Instant openUntil = Instant.EPOCH;
    private volatile Semaphore halfOpenPermits;

    private CircuitBreaker(
            String name,
            int failureThreshold,
            Duration openDuration,
            int halfOpenMaxCalls,
            int halfOpenSuccessThreshold,
            Clock clock
    ) {
        this.name = name;
        this.failureThreshold = failureThreshold;
        this.openDuration = openDuration;
        this.halfOpenMaxCalls = halfOpenMaxCalls;
        this.halfOpenSuccessThreshold = halfOpenSuccessThreshold;
        this.clock = clock;
    }

    public String name() {
        return name;
    }

    public State state() {
        maybeTransitionFromOpen();
        return state;
    }

    public <T> T execute(Callable<T> operation) throws Exception {
        Objects.requireNonNull(operation, "operation");

        if (!tryAcquirePermission()) {
            throw new CircuitBreakerOpenException(name, state, openUntil);
        }

        try {
            T result = operation.call();
            onSuccess();
            return result;
        } catch (Exception e) {
            onFailure();
            throw e;
        } catch (Throwable t) {
            onFailure();
            throw t;
        } finally {
            releasePermission();
        }
    }

    public <T> T executeUnchecked(Callable<T> operation) {
        try {
            return execute(operation);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convenience for demonstrating circuit breaker usage with virtual threads.
     * The circuit breaker itself is thread-safe and does not depend on a particular threading model.
     */
    public void demoWithVirtualThreads(int tasks, Runnable operation) throws InterruptedException {
        Objects.requireNonNull(operation, "operation");
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < tasks; i++) {
                executor.submit(() -> executeUnchecked(() -> {
                    operation.run();
                    return null;
                }));
            }
        }
    }

    private boolean tryAcquirePermission() {
        maybeTransitionFromOpen();

        State s = state;
        if (s == State.CLOSED) {
            return true;
        }
        if (s == State.OPEN) {
            return false;
        }

        // HALF_OPEN
        Semaphore permits = halfOpenPermits;
        return permits != null && permits.tryAcquire();
    }

    private void releasePermission() {
        if (state != State.HALF_OPEN) {
            return;
        }
        Semaphore permits = halfOpenPermits;
        if (permits != null) {
            permits.release();
        }
    }

    private void onSuccess() {
        State s = state;
        if (s == State.CLOSED) {
            consecutiveFailures.set(0);
            return;
        }
        if (s == State.HALF_OPEN) {
            int successes = consecutiveHalfOpenSuccesses.incrementAndGet();
            if (successes >= halfOpenSuccessThreshold) {
                transitionToClosed();
            }
        }
    }

    private void onFailure() {
        State s = state;
        if (s == State.CLOSED) {
            int failures = consecutiveFailures.incrementAndGet();
            if (failures >= failureThreshold) {
                transitionToOpen();
            }
            return;
        }
        if (s == State.HALF_OPEN) {
            transitionToOpen();
        }
    }

    private void maybeTransitionFromOpen() {
        if (state != State.OPEN) {
            return;
        }
        Instant now = clock.instant();
        if (now.isBefore(openUntil)) {
            return;
        }
        transitionToHalfOpen();
    }

    private void transitionToOpen() {
        transitionLock.lock();
        try {
            state = State.OPEN;
            consecutiveFailures.set(0);
            consecutiveHalfOpenSuccesses.set(0);
            halfOpenPermits = null;
            openUntil = clock.instant().plus(openDuration);
        } finally {
            transitionLock.unlock();
        }
    }

    private void transitionToHalfOpen() {
        transitionLock.lock();
        try {
            if (state != State.OPEN) {
                return;
            }
            state = State.HALF_OPEN;
            consecutiveHalfOpenSuccesses.set(0);
            halfOpenPermits = new Semaphore(halfOpenMaxCalls);
        } finally {
            transitionLock.unlock();
        }
    }

    private void transitionToClosed() {
        transitionLock.lock();
        try {
            state = State.CLOSED;
            openUntil = Instant.EPOCH;
            consecutiveFailures.set(0);
            consecutiveHalfOpenSuccesses.set(0);
            halfOpenPermits = null;
        } finally {
            transitionLock.unlock();
        }
    }

    public static final class Builder {
        private final String name;
        private int failureThreshold = 3;
        private Duration openDuration = Duration.ofSeconds(5);
        private int halfOpenMaxCalls = 1;
        private int halfOpenSuccessThreshold = 1;
        private Clock clock = Clock.systemUTC();

        private Builder(String name) {
            this.name = Objects.requireNonNull(name, "name");
            if (name.isBlank()) {
                throw new IllegalArgumentException("name must not be blank");
            }
        }

        public Builder failureThreshold(int failureThreshold) {
            if (failureThreshold < 1) {
                throw new IllegalArgumentException("failureThreshold must be >= 1");
            }
            this.failureThreshold = failureThreshold;
            return this;
        }

        public Builder openDuration(Duration openDuration) {
            this.openDuration = Objects.requireNonNull(openDuration, "openDuration");
            if (openDuration.isNegative() || openDuration.isZero()) {
                throw new IllegalArgumentException("openDuration must be > 0");
            }
            return this;
        }

        public Builder halfOpenMaxCalls(int halfOpenMaxCalls) {
            if (halfOpenMaxCalls < 1) {
                throw new IllegalArgumentException("halfOpenMaxCalls must be >= 1");
            }
            this.halfOpenMaxCalls = halfOpenMaxCalls;
            return this;
        }

        public Builder halfOpenSuccessThreshold(int halfOpenSuccessThreshold) {
            if (halfOpenSuccessThreshold < 1) {
                throw new IllegalArgumentException("halfOpenSuccessThreshold must be >= 1");
            }
            this.halfOpenSuccessThreshold = halfOpenSuccessThreshold;
            return this;
        }

        public Builder clock(Clock clock) {
            this.clock = Objects.requireNonNull(clock, "clock");
            return this;
        }

        public CircuitBreaker build() {
            if (halfOpenSuccessThreshold > halfOpenMaxCalls) {
                throw new IllegalArgumentException("halfOpenSuccessThreshold must be <= halfOpenMaxCalls");
            }
            return new CircuitBreaker(
                    name,
                    failureThreshold,
                    openDuration,
                    halfOpenMaxCalls,
                    halfOpenSuccessThreshold,
                    clock
            );
        }
    }
}

