package design.patterns.deadline;

import java.time.Instant;

public final class DeadlineExceededException extends RuntimeException {
    public DeadlineExceededException(String context, Instant due) {
        super("Deadline exceeded (" + context + ", due=" + due + ")");
    }
}

