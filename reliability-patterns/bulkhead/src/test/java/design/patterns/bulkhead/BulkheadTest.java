package design.patterns.bulkhead;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BulkheadTest {
    @Test
    void limitsConcurrentCalls() throws Exception {
        Bulkhead bulkhead = Bulkhead.builder("test")
                .maxConcurrent(2)
                .maxWait(Duration.ZERO)
                .build();

        CountDownLatch entered = new CountDownLatch(2);
        CountDownLatch release = new CountDownLatch(1);
        AtomicInteger rejected = new AtomicInteger(0);

        var executor = Executors.newVirtualThreadPerTaskExecutor();
        try {
            executor.submit(() -> {
                try {
                    bulkhead.execute(() -> {
                        entered.countDown();
                        release.await();
                        return null;
                    });
                    return null;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            executor.submit(() -> {
                try {
                    bulkhead.execute(() -> {
                        entered.countDown();
                        release.await();
                        return null;
                    });
                    return null;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            assertTrue(entered.await(1, TimeUnit.SECONDS));

            // Third call should be rejected immediately.
            executor.submit(() -> {
                try {
                    bulkhead.execute(() -> null);
                } catch (BulkheadFullException e) {
                    rejected.incrementAndGet();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).get(1, TimeUnit.SECONDS);
        } finally {
            release.countDown();
            executor.close();
        }

        assertEquals(1, rejected.get());
    }
}
