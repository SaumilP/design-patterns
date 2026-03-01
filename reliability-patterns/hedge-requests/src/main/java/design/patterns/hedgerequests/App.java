package design.patterns.hedgerequests;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public final class App {
    public static void main(String[] args) throws Exception {
        try (HedgeRequests hedger = HedgeRequests.builder("player-profile")
                .hedgeDelay(Duration.ofMillis(20))
                .maxHedges(1)
                .build()) {

            CountDownLatch slowReplica = new CountDownLatch(1);
            AtomicInteger calls = new AtomicInteger(0);

            String value = hedger.executeIdempotent(() -> {
                int n = calls.incrementAndGet();
                if (n == 1) {
                    slowReplica.await();
                    return "slow";
                }
                return "fast";
            });

            System.out.println("value=" + value + " calls=" + calls.get());
            slowReplica.countDown();
        }
    }
}

