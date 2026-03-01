package design.patterns.deadline;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    void remainingDecreasesAndEventuallyExpires() {
        MutableClock clock = new MutableClock(Instant.parse("2026-03-01T00:00:00Z"), ZoneOffset.UTC);
        Deadline d = Deadline.after(Duration.ofSeconds(5), clock);

        assertEquals(Duration.ofSeconds(5), d.remaining());
        clock.advance(Duration.ofSeconds(3));
        assertEquals(Duration.ofSeconds(2), d.remaining());
        clock.advance(Duration.ofSeconds(2));
        assertTrue(d.isExpired());
    }

    @Test
    void throwsWhenExpired() {
        MutableClock clock = new MutableClock(Instant.parse("2026-03-01T00:00:00Z"), ZoneOffset.UTC);
        Deadline d = Deadline.after(Duration.ofSeconds(1), clock);
        clock.advance(Duration.ofSeconds(1));
        assertThrows(DeadlineExceededException.class, () -> d.throwIfExpired("matchmaking"));
    }
}

