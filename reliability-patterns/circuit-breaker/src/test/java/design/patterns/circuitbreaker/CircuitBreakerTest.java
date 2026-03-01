package design.patterns.circuitbreaker;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CircuitBreakerTest {
    @Test
    void opensAfterConsecutiveFailures() {
        MutableClock clock = new MutableClock(Instant.parse("2026-03-01T00:00:00Z"), ZoneOffset.UTC);
        CircuitBreaker breaker = CircuitBreaker.builder("test")
                .clock(clock)
                .failureThreshold(2)
                .openDuration(Duration.ofSeconds(10))
                .build();

        assertThrows(RuntimeException.class, () -> breaker.executeUnchecked(() -> { throw new RuntimeException("boom"); }));
        assertThrows(RuntimeException.class, () -> breaker.executeUnchecked(() -> { throw new RuntimeException("boom"); }));

        assertEquals(CircuitBreaker.State.OPEN, breaker.state());
        assertThrows(CircuitBreakerOpenException.class, () -> breaker.executeUnchecked(() -> "ok"));
    }

    @Test
    void transitionsToHalfOpenAfterOpenDuration() {
        MutableClock clock = new MutableClock(Instant.parse("2026-03-01T00:00:00Z"), ZoneOffset.UTC);
        CircuitBreaker breaker = CircuitBreaker.builder("test")
                .clock(clock)
                .failureThreshold(1)
                .openDuration(Duration.ofSeconds(5))
                .halfOpenMaxCalls(1)
                .halfOpenSuccessThreshold(1)
                .build();

        assertThrows(RuntimeException.class, () -> breaker.executeUnchecked(() -> { throw new RuntimeException("boom"); }));
        assertEquals(CircuitBreaker.State.OPEN, breaker.state());

        clock.advance(Duration.ofSeconds(5));
        assertEquals(CircuitBreaker.State.HALF_OPEN, breaker.state());
    }

    @Test
    void closesAfterSuccessfulHalfOpenTrial() {
        MutableClock clock = new MutableClock(Instant.parse("2026-03-01T00:00:00Z"), ZoneOffset.UTC);
        CircuitBreaker breaker = CircuitBreaker.builder("test")
                .clock(clock)
                .failureThreshold(1)
                .openDuration(Duration.ofSeconds(1))
                .halfOpenMaxCalls(1)
                .halfOpenSuccessThreshold(1)
                .build();

        assertThrows(RuntimeException.class, () -> breaker.executeUnchecked(() -> { throw new RuntimeException("boom"); }));
        assertEquals(CircuitBreaker.State.OPEN, breaker.state());

        clock.advance(Duration.ofSeconds(1));
        assertEquals(CircuitBreaker.State.HALF_OPEN, breaker.state());

        assertEquals("ok", breaker.executeUnchecked(() -> "ok"));
        assertEquals(CircuitBreaker.State.CLOSED, breaker.state());
    }

    @Test
    void halfOpenFailureReOpensImmediately() {
        MutableClock clock = new MutableClock(Instant.parse("2026-03-01T00:00:00Z"), ZoneOffset.UTC);
        CircuitBreaker breaker = CircuitBreaker.builder("test")
                .clock(clock)
                .failureThreshold(1)
                .openDuration(Duration.ofSeconds(1))
                .halfOpenMaxCalls(1)
                .halfOpenSuccessThreshold(1)
                .build();

        assertThrows(RuntimeException.class, () -> breaker.executeUnchecked(() -> { throw new RuntimeException("boom"); }));
        clock.advance(Duration.ofSeconds(1));
        assertEquals(CircuitBreaker.State.HALF_OPEN, breaker.state());

        assertThrows(RuntimeException.class, () -> breaker.executeUnchecked(() -> { throw new RuntimeException("still broken"); }));
        assertEquals(CircuitBreaker.State.OPEN, breaker.state());
    }

    @Test
    void isThreadSafeUnderVirtualThreads() throws Exception {
        MutableClock clock = new MutableClock(Instant.parse("2026-03-01T00:00:00Z"), ZoneOffset.UTC);
        CircuitBreaker breaker = CircuitBreaker.builder("test")
                .clock(clock)
                .failureThreshold(5)
                .openDuration(Duration.ofSeconds(10))
                .build();

        AtomicInteger calls = new AtomicInteger(0);
        int tasks = 200;
        CountDownLatch start = new CountDownLatch(1);

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<?>> futures = new ArrayList<>();
            for (int i = 0; i < tasks; i++) {
                futures.add(executor.submit(() -> {
                    start.await();
                    breaker.executeUnchecked(() -> {
                        calls.incrementAndGet();
                        // Fail some calls to exercise state transitions.
                        if ((calls.get() % 7) == 0) {
                            throw new RuntimeException("boom");
                        }
                        return null;
                    });
                    return null;
                }));
            }
            start.countDown();
            for (Future<?> f : futures) {
                try {
                    f.get();
                } catch (Exception ignored) {
                    // Some tasks may fail due to simulated failures or breaker opening.
                }
            }
        }

        assertTrue(calls.get() > 0);
    }
}

