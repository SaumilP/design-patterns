package design.patterns.hedgerequests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Hedge requests: issue duplicate request(s) when the primary is slow, and take the first successful response.
 *
 * Warning: only safe for idempotent operations (reads), or when combined with idempotency keys.
 *
 * An example: hedging "get player profile" reads across replicas to reduce tail latency.
 */
public final class HedgeRequests implements AutoCloseable {
    public static Builder builder(String name) {
        return new Builder(name);
    }

    private final String name;
    private final Duration hedgeDelay;
    private final int maxHedges;
    private final ExecutorService executor;
    private final ScheduledExecutorService scheduler;

    private HedgeRequests(String name, Duration hedgeDelay, int maxHedges) {
        this.name = name;
        this.hedgeDelay = hedgeDelay;
        this.maxHedges = maxHedges;
        this.executor = Executors.newVirtualThreadPerTaskExecutor();
        this.scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("hedge-scheduler-" + name);
            t.setDaemon(true);
            return t;
        });
    }

    public String name() {
        return name;
    }

    public <T> T executeIdempotent(Callable<T> operation) throws Exception {
        Objects.requireNonNull(operation, "operation");

        int attempts = 1 + maxHedges;
        AtomicInteger remaining = new AtomicInteger(attempts);
        AtomicReference<Throwable> lastFailure = new AtomicReference<>(null);

        CompletableFuture<T> result = new CompletableFuture<>();
        List<Future<?>> running = new ArrayList<>(attempts);
        List<Future<?>> scheduled = new ArrayList<>(maxHedges);

        for (int i = 0; i < attempts; i++) {
            Duration delay = hedgeDelay.multipliedBy(i);
            if (delay.isZero()) {
                running.add(startAttempt(operation, result, remaining, lastFailure));
                continue;
            }
            scheduled.add(scheduler.schedule(() -> {
                Future<?> f = startAttempt(operation, result, remaining, lastFailure);
                synchronized (running) {
                    running.add(f);
                }
            }, delay.toNanos(), TimeUnit.NANOSECONDS));
        }

        result.whenComplete((v, t) -> {
            for (Future<?> f : scheduled) {
                f.cancel(true);
            }
            synchronized (running) {
                for (Future<?> f : running) {
                    f.cancel(true);
                }
            }
        });

        try {
            return result.get();
        } catch (java.util.concurrent.ExecutionException e) {
            Throwable c = e.getCause();
            if (c instanceof Exception ex) {
                throw ex;
            }
            throw e;
        } finally {
            // ensure scheduled tasks don't linger if caller bails out early
            for (Future<?> f : scheduled) {
                f.cancel(true);
            }
        }
    }

    private <T> Future<?> startAttempt(
            Callable<T> operation,
            CompletableFuture<T> result,
            AtomicInteger remaining,
            AtomicReference<Throwable> lastFailure
    ) {
        return executor.submit(() -> {
            if (result.isDone()) {
                remaining.decrementAndGet();
                return;
            }
            try {
                T value = operation.call();
                result.complete(value);
            } catch (Throwable t) {
                lastFailure.set(t);
                if (remaining.decrementAndGet() == 0) {
                    Throwable last = lastFailure.get();
                    result.completeExceptionally(last == null ? new RuntimeException("All hedged attempts failed") : last);
                }
            }
        });
    }

    @Override
    public void close() {
        scheduler.shutdownNow();
        executor.shutdownNow();
    }

    public static final class Builder {
        private final String name;
        private Duration hedgeDelay = Duration.ofMillis(10);
        private int maxHedges = 1;

        private Builder(String name) {
            this.name = Objects.requireNonNull(name, "name");
            if (name.isBlank()) {
                throw new IllegalArgumentException("name must not be blank");
            }
        }

        public Builder hedgeDelay(Duration hedgeDelay) {
            this.hedgeDelay = Objects.requireNonNull(hedgeDelay, "hedgeDelay");
            if (hedgeDelay.isNegative()) {
                throw new IllegalArgumentException("hedgeDelay must be >= 0");
            }
            return this;
        }

        public Builder maxHedges(int maxHedges) {
            if (maxHedges < 0) {
                throw new IllegalArgumentException("maxHedges must be >= 0");
            }
            this.maxHedges = maxHedges;
            return this;
        }

        public HedgeRequests build() {
            return new HedgeRequests(name, hedgeDelay, maxHedges);
        }
    }
}

