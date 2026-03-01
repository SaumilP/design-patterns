package design.patterns.idempotency;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdempotencyKeysTest {
    @Test
    void onlyExecutesOperationOnceForSameKey() throws Exception {
        IdempotencyKeys store = IdempotencyKeys.builder("test")
                .ttl(Duration.ofMinutes(1))
                .build();

        AtomicInteger calls = new AtomicInteger(0);
        String key = "k1";

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> futures = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                futures.add(executor.submit(() -> store.execute(key, () -> {
                    calls.incrementAndGet();
                    return "ok";
                })));
            }
            for (Future<String> f : futures) {
                assertEquals("ok", f.get());
            }
        }

        assertEquals(1, calls.get());
    }

    @Test
    void expiresAfterTtlAndAllowsReExecution() throws Exception {
        MutableClock clock = new MutableClock(Instant.parse("2026-03-01T00:00:00Z"), ZoneOffset.UTC);
        IdempotencyKeys store = IdempotencyKeys.builder("test")
                .ttl(Duration.ofSeconds(5))
                .clock(clock)
                .build();

        AtomicInteger calls = new AtomicInteger(0);
        String key = "k1";

        assertEquals("ok", store.execute(key, () -> { calls.incrementAndGet(); return "ok"; }));
        assertEquals("ok", store.execute(key, () -> { calls.incrementAndGet(); return "ok"; }));
        assertEquals(1, calls.get());

        clock.advance(Duration.ofSeconds(6));
        assertEquals("ok", store.execute(key, () -> { calls.incrementAndGet(); return "ok"; }));
        assertEquals(2, calls.get());
    }
}

