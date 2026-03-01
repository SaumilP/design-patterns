package design.patterns.retrybackoff;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public final class App {
    public static void main(String[] args) throws Exception {
        RetryExecutor retry = RetryExecutor.builder("session-validate")
                .maxAttempts(5)
                .baseDelay(Duration.ofMillis(50))
                .maxDelay(Duration.ofSeconds(1))
                .jitterRatio(0.2)
                .build();

        AtomicInteger attempts = new AtomicInteger(0);
        String result = retry.execute(() -> {
            int n = attempts.incrementAndGet();
            if (n < 3) {
                throw new RuntimeException("Transient failure");
            }
            return "OK";
        });

        System.out.println("result=" + result + " attempts=" + attempts.get());
    }
}

