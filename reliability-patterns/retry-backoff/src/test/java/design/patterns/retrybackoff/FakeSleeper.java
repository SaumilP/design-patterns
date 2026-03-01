package design.patterns.retrybackoff;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

final class FakeSleeper implements RetryExecutor.Sleeper {
    final List<Duration> sleeps = new ArrayList<>();

    @Override
    public void sleep(Duration duration) {
        sleeps.add(duration);
    }
}

