package design.patterns.bulkhead;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public final class App {
    public static void main(String[] args) throws Exception {
        // An example: isolate capacity for the "inventory service".
        // Domain: Gaming industry, where the inventory service is responsible for managing in-game items and resources.
        Bulkhead inventoryBulkhead = Bulkhead.builder("inventory-service")
                .maxConcurrent(5)
                .maxWait(Duration.ofMillis(20))
                .build();

        AtomicInteger ok = new AtomicInteger();
        AtomicInteger rejected = new AtomicInteger();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 50; i++) {
                executor.submit(() -> {
                    try {
                        inventoryBulkhead.execute(() -> {
                            ok.incrementAndGet();
                            Thread.sleep(30);
                            return null;
                        });
                    } catch (BulkheadFullException e) {
                        rejected.incrementAndGet();
                    }
                    return null;
                });
            }
        }

        System.out.println("ok=" + ok.get() + " rejected=" + rejected.get());
    }
}

