package design.patterns.deadline;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * Deadline propagation: represent a single end-to-end time budget and pass it to all downstream calls.
 *
 * In a real system, this can be carried via request context headers (e.g., "deadline" or "timeout-ms").
 */
public final class Deadline {
    public static Deadline after(Duration timeout) {
        return after(timeout, Clock.systemUTC());
    }

    public static Deadline after(Duration timeout, Clock clock) {
        Objects.requireNonNull(timeout, "timeout");
        Objects.requireNonNull(clock, "clock");
        if (timeout.isZero() || timeout.isNegative()) {
            throw new IllegalArgumentException("timeout must be > 0");
        }
        Instant now = clock.instant();
        return new Deadline(clock, now, now.plus(timeout));
    }

    private final Clock clock;
    private final Instant start;
    private final Instant due;

    private Deadline(Clock clock, Instant start, Instant due) {
        this.clock = clock;
        this.start = start;
        this.due = due;
    }

    public Instant start() {
        return start;
    }

    public Instant due() {
        return due;
    }

    public Duration remaining() {
        Instant now = clock.instant();
        Duration r = Duration.between(now, due);
        return r.isNegative() ? Duration.ZERO : r;
    }

    public boolean isExpired() {
        return remaining().isZero();
    }

    public void throwIfExpired(String context) {
        if (isExpired()) {
            throw new DeadlineExceededException(context, due);
        }
    }
}

