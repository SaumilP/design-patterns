package design.patterns.idempotency;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public final class App {
    public static void main(String[] args) throws Exception {
        IdempotencyKeys idempotency = IdempotencyKeys.builder("purchase")
                .ttl(Duration.ofMinutes(5))
                .build();

        AtomicInteger charged = new AtomicInteger(0);
        String key = "purchase:player-42:order-1001";

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var futures = executor.invokeAll(java.util.List.of(
                    () -> idempotency.execute(key, () -> { charged.incrementAndGet(); return "OK"; }),
                    () -> idempotency.execute(key, () -> { charged.incrementAndGet(); return "OK"; }),
                    () -> idempotency.execute(key, () -> { charged.incrementAndGet(); return "OK"; })
            ));
            for (var f : futures) {
                System.out.println(f.get());
            }
        }

        System.out.println("charged=" + charged.get());
    }
}

