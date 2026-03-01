package design.patterns.ratelimiter;

public final class RateLimitedException extends RuntimeException {
    public RateLimitedException(String limiterName) {
        super("Rate limited by '" + limiterName + "'");
    }
}

