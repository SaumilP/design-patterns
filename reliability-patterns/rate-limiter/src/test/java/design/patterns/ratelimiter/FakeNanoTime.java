package design.patterns.ratelimiter;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongSupplier;

final class FakeNanoTime implements LongSupplier {
    private final AtomicLong nowNanos = new AtomicLong(0);

    void set(long nanos) {
        nowNanos.set(nanos);
    }

    void advance(long nanos) {
        nowNanos.addAndGet(nanos);
    }

    @Override
    public long getAsLong() {
        return nowNanos.get();
    }
}

