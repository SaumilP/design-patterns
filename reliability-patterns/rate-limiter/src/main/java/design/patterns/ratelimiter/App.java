package design.patterns.ratelimiter;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public final class App {
    public static void main(String[] args) throws Exception {
        // An example: protect leaderboards from excessive refreshes.
        TokenBucketRateLimiter limiter = TokenBucketRateLimiter.builder("leaderboard-refresh")
                .capacity(5)
                .refill(5, Duration.ofSeconds(1))
                .build();

        AtomicInteger allowed = new AtomicInteger(0);
        AtomicInteger blocked = new AtomicInteger(0);

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 30; i++) {
                executor.submit(() -> {
                    try {
                        limiter.execute(() -> {
                            allowed.incrementAndGet();
                            return null;
                        }, Duration.ofMillis(10));
                    } catch (RateLimitedException e) {
                        blocked.incrementAndGet();
                    }
                    return null;
                });
            }
        }

        System.out.println("allowed=" + allowed.get() + " blocked=" + blocked.get());
    }
}

