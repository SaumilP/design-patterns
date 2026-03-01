package design.patterns.retrybackoff;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RetryExecutorTest {
    @Test
    void retriesWithExponentialBackoff() throws Exception {
        FakeSleeper sleeper = new FakeSleeper();
        RetryExecutor retry = RetryExecutor.builder("test")
                .maxAttempts(4)
                .baseDelay(Duration.ofMillis(10))
                .maxDelay(Duration.ofMillis(100))
                .jitterRatio(0.0)
                .sleeper(sleeper)
                .build();

        AtomicInteger attempts = new AtomicInteger(0);
        String result = retry.execute(() -> {
            if (attempts.incrementAndGet() < 3) {
                throw new RuntimeException("boom");
            }
            return "ok";
        });

        assertEquals("ok", result);
        assertEquals(2, sleeper.sleeps.size());
        assertEquals(Duration.ofMillis(10), sleeper.sleeps.get(0));
        assertEquals(Duration.ofMillis(20), sleeper.sleeps.get(1));
    }

    @Test
    void stopsRetryingWhenPredicateRejects() {
        FakeSleeper sleeper = new FakeSleeper();
        RetryExecutor retry = RetryExecutor.builder("test")
                .maxAttempts(5)
                .baseDelay(Duration.ofMillis(10))
                .maxDelay(Duration.ofMillis(100))
                .retryOn(t -> !(t instanceof IllegalArgumentException))
                .sleeper(sleeper)
                .build();

        assertThrows(IllegalArgumentException.class, () -> retry.execute(() -> {
            throw new IllegalArgumentException("no retry");
        }));
        assertEquals(0, sleeper.sleeps.size());
    }
}

