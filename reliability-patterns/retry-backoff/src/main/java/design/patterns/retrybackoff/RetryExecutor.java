package design.patterns.retrybackoff;

import java.time.Duration;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * Retry with exponential backoff (optionally with jitter).
 *
 * An example: retry a transient "session validation" call during an incident, without stampeding the service.
 */
public final class RetryExecutor {
    public interface Sleeper {
        void sleep(Duration duration) throws InterruptedException;
    }

    public static Builder builder(String name) {
        return new Builder(name);
    }

    private final String name;
    private final int maxAttempts;
    private final Duration baseDelay;
    private final Duration maxDelay;
    private final double jitterRatio;
    private final Predicate<Throwable> retryOn;
    private final Random random;
    private final Sleeper sleeper;

    private RetryExecutor(
            String name,
            int maxAttempts,
            Duration baseDelay,
            Duration maxDelay,
            double jitterRatio,
            Predicate<Throwable> retryOn,
            Random random,
            Sleeper sleeper
    ) {
        this.name = name;
        this.maxAttempts = maxAttempts;
        this.baseDelay = baseDelay;
        this.maxDelay = maxDelay;
        this.jitterRatio = jitterRatio;
        this.retryOn = retryOn;
        this.random = random;
        this.sleeper = sleeper;
    }

    public String name() {
        return name;
    }

    public <T> T execute(Callable<T> operation) throws Exception {
        Objects.requireNonNull(operation, "operation");

        Throwable last = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return operation.call();
            } catch (Throwable t) {
                last = t;
                if (attempt >= maxAttempts || !retryOn.test(t)) {
                    throw t;
                }
                Duration delay = computeDelay(attempt);
                sleeper.sleep(delay);
            }
        }
        throw new IllegalStateException("unreachable", last);
    }

    private Duration computeDelay(int attempt) {
        long base = baseDelay.toMillis();
        long cap = maxDelay.toMillis();
        long exp = Math.min(cap, base * (1L << Math.min(30, attempt - 1)));

        if (jitterRatio <= 0.0) {
            return Duration.ofMillis(exp);
        }

        long jitterWindow = (long) (exp * jitterRatio);
        long jitter = jitterWindow == 0 ? 0 : random.nextLong(jitterWindow + 1);
        long min = Math.max(0, exp - jitterWindow);
        long withJitter = Math.min(cap, min + jitter);
        return Duration.ofMillis(withJitter);
    }

    public static final class Builder {
        private final String name;
        private int maxAttempts = 3;
        private Duration baseDelay = Duration.ofMillis(50);
        private Duration maxDelay = Duration.ofSeconds(2);
        private double jitterRatio = 0.0;
        private Predicate<Throwable> retryOn = t -> true;
        private Random random = new Random();
        private Sleeper sleeper = d -> Thread.sleep(d.toMillis());

        private Builder(String name) {
            this.name = Objects.requireNonNull(name, "name");
            if (name.isBlank()) {
                throw new IllegalArgumentException("name must not be blank");
            }
        }

        public Builder maxAttempts(int maxAttempts) {
            if (maxAttempts < 1) {
                throw new IllegalArgumentException("maxAttempts must be >= 1");
            }
            this.maxAttempts = maxAttempts;
            return this;
        }

        public Builder baseDelay(Duration baseDelay) {
            this.baseDelay = Objects.requireNonNull(baseDelay, "baseDelay");
            if (baseDelay.isNegative()) {
                throw new IllegalArgumentException("baseDelay must be >= 0");
            }
            return this;
        }

        public Builder maxDelay(Duration maxDelay) {
            this.maxDelay = Objects.requireNonNull(maxDelay, "maxDelay");
            if (maxDelay.isZero() || maxDelay.isNegative()) {
                throw new IllegalArgumentException("maxDelay must be > 0");
            }
            return this;
        }

        public Builder jitterRatio(double jitterRatio) {
            if (jitterRatio < 0.0 || jitterRatio > 1.0) {
                throw new IllegalArgumentException("jitterRatio must be in [0, 1]");
            }
            this.jitterRatio = jitterRatio;
            return this;
        }

        public Builder retryOn(Predicate<Throwable> retryOn) {
            this.retryOn = Objects.requireNonNull(retryOn, "retryOn");
            return this;
        }

        public Builder random(Random random) {
            this.random = Objects.requireNonNull(random, "random");
            return this;
        }

        public Builder sleeper(Sleeper sleeper) {
            this.sleeper = Objects.requireNonNull(sleeper, "sleeper");
            return this;
        }

        public RetryExecutor build() {
            if (baseDelay.compareTo(maxDelay) > 0) {
                throw new IllegalArgumentException("baseDelay must be <= maxDelay");
            }
            return new RetryExecutor(name, maxAttempts, baseDelay, maxDelay, jitterRatio, retryOn, random, sleeper);
        }
    }
}

