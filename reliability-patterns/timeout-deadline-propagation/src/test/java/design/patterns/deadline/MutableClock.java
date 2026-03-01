package design.patterns.deadline;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

final class MutableClock extends Clock {
    private Instant instant;
    private final ZoneId zoneId;

    MutableClock(Instant initialInstant, ZoneId zoneId) {
        this.instant = initialInstant;
        this.zoneId = zoneId;
    }

    void advance(java.time.Duration duration) {
        this.instant = this.instant.plus(duration);
    }

    @Override
    public ZoneId getZone() {
        return zoneId;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return new MutableClock(instant, zone);
    }

    @Override
    public Instant instant() {
        return instant;
    }
}

