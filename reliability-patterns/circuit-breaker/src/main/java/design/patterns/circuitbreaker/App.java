package design.patterns.circuitbreaker;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public final class App {
    public static void main(String[] args) throws InterruptedException {
        CircuitBreaker breaker = CircuitBreaker.builder("external-api")
                .failureThreshold(3)
                .openDuration(Duration.ofSeconds(2))
                .halfOpenMaxCalls(1)
                .halfOpenSuccessThreshold(1)
                .build();

        // Demo: run many calls using virtual threads.
        breaker.demoWithVirtualThreads(25, () -> {
            if (ThreadLocalRandom.current().nextInt(4) == 0) {
                throw new RuntimeException("Simulated downstream failure");
            }
        });

        System.out.println("Breaker state: " + breaker.state());
    }
}

