package design.patterns.saga;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SagaTest {
    @Test
    void successfulSagaDoesNotRunCompensation() {
        AtomicBoolean reserved = new AtomicBoolean(false);
        AtomicBoolean granted = new AtomicBoolean(false);

        Saga saga = Saga.builder("ok")
                .step("reserve", () -> reserved.set(true), () -> reserved.set(false))
                .step("grant", () -> granted.set(true), () -> granted.set(false))
                .build();

        Saga.Result r = saga.run();
        assertTrue(r.success());
        assertTrue(reserved.get());
        assertTrue(granted.get());
    }

    @Test
    void failureTriggersCompensationInReverseOrder() {
        AtomicBoolean reserved = new AtomicBoolean(false);
        AtomicInteger wallet = new AtomicInteger(100);
        AtomicBoolean granted = new AtomicBoolean(false);

        Saga saga = Saga.builder("purchase")
                .step("reserve", () -> reserved.set(true), () -> reserved.set(false))
                .step("charge", () -> wallet.addAndGet(-60), () -> wallet.addAndGet(60))
                .step("grant", () -> { throw new RuntimeException("downstream"); }, () -> granted.set(false))
                .build();

        Saga.Result r = saga.run();
        assertFalse(r.success());
        assertFalse(reserved.get());
        assertEquals(100, wallet.get());
        assertFalse(granted.get());
    }
}

