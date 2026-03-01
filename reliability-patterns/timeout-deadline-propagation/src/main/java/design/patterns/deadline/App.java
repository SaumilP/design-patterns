package design.patterns.deadline;

import java.time.Duration;
import java.util.concurrent.Executors;

public final class App {
    public static void main(String[] args) {
        // An example: "join matchmaking" request has a single 200ms budget end-to-end.
        Deadline deadline = Deadline.after(Duration.ofMillis(200));

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                deadline.throwIfExpired("pre-check");
                simulateCall("player-profile", deadline.remaining());

                deadline.throwIfExpired("matchmaking");
                simulateCall("matchmaking", deadline.remaining());
                return null;
            });
        }
    }

    private static void simulateCall(String name, Duration timeout) throws InterruptedException {
        System.out.println("Calling " + name + " with timeout=" + timeout.toMillis() + "ms");
        Thread.sleep(Math.min(50, timeout.toMillis()));
    }
}

