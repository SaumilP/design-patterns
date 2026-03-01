package design.patterns.bulkhead;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Bulkhead pattern: isolate capacity by limiting concurrent calls to a dependency.
 *
 * An example: prevent "inventory service" calls from consuming all request threads.
 */
public final class Bulkhead {
    public static Builder builder(String name) {
        return new Builder(name);
    }

    private final String name;
    private final Semaphore semaphore;
    private final Duration maxWait;

    private Bulkhead(String name, Semaphore semaphore, Duration maxWait) {
        this.name = name;
        this.semaphore = semaphore;
        this.maxWait = maxWait;
    }

    public String name() {
        return name;
    }

    public <T> T execute(Callable<T> operation) throws Exception {
        Objects.requireNonNull(operation, "operation");
        boolean acquired = semaphore.tryAcquire(maxWait.toNanos(), TimeUnit.NANOSECONDS);
        if (!acquired) {
            throw new BulkheadFullException(name);
        }
        try {
            return operation.call();
        } finally {
            semaphore.release();
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

    public static final class Builder {
        private final String name;
        private int maxConcurrent = 10;
        private Duration maxWait = Duration.ZERO;
        private boolean fair = true;

        private Builder(String name) {
            this.name = Objects.requireNonNull(name, "name");
            if (name.isBlank()) {
                throw new IllegalArgumentException("name must not be blank");
            }
        }

        public Builder maxConcurrent(int maxConcurrent) {
            if (maxConcurrent < 1) {
                throw new IllegalArgumentException("maxConcurrent must be >= 1");
            }
            this.maxConcurrent = maxConcurrent;
            return this;
        }

        public Builder maxWait(Duration maxWait) {
            this.maxWait = Objects.requireNonNull(maxWait, "maxWait");
            if (maxWait.isNegative()) {
                throw new IllegalArgumentException("maxWait must be >= 0");
            }
            return this;
        }

        public Builder fair(boolean fair) {
            this.fair = fair;
            return this;
        }

        public Bulkhead build() {
            return new Bulkhead(name, new Semaphore(maxConcurrent, fair), maxWait);
        }
    }
}

