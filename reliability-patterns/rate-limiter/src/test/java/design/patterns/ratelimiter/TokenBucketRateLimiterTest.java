package design.patterns.ratelimiter;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenBucketRateLimiterTest {
    @Test
    void drainsCapacityThenBlocksUntilRefill() {
        FakeNanoTime nanoTime = new FakeNanoTime();
        TokenBucketRateLimiter limiter = TokenBucketRateLimiter.builder("test")
                .capacity(2)
                .refill(1, Duration.ofSeconds(1))
                .nanoTime(nanoTime)
                .sleeper(d -> {})
                .build();

        assertTrue(limiter.tryAcquire());
        assertTrue(limiter.tryAcquire());
        assertFalse(limiter.tryAcquire());

        nanoTime.advance(Duration.ofSeconds(1).toNanos());
        assertTrue(limiter.tryAcquire());
        assertFalse(limiter.tryAcquire());
    }
}

