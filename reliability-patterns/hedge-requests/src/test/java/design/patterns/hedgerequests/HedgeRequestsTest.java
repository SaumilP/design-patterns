package design.patterns.hedgerequests;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HedgeRequestsTest {
    @Test
    void returnsFirstSuccessfulAttemptAndCancelsSlowPrimary() throws Exception {
        CountDownLatch primaryStarted = new CountDownLatch(1);
        CountDownLatch unblockPrimary = new CountDownLatch(1);
        AtomicInteger calls = new AtomicInteger(0);

        try (HedgeRequests hedger = HedgeRequests.builder("test")
                .hedgeDelay(Duration.ofMillis(10))
                .maxHedges(1)
                .build()) {
            try {
                String result = hedger.executeIdempotent(() -> {
                    int n = calls.incrementAndGet();
                    if (n == 1) {
                        primaryStarted.countDown();
                        try {
                            unblockPrimary.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException("primary cancelled", e);
                        }
                        return "slow";
                    }
                    return "fast";
                });

                assertTrue(primaryStarted.await(1, TimeUnit.SECONDS));
                assertEquals("fast", result);
                assertTrue(calls.get() >= 2);
            } finally {
                unblockPrimary.countDown();
            }
        }
    }
}
