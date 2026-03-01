package design.patterns.circuitbreaker;

import java.time.Instant;

public final class CircuitBreakerOpenException extends RuntimeException {
    private final String circuitName;
    private final CircuitBreaker.State state;
    private final Instant openUntil;

    public CircuitBreakerOpenException(String circuitName, CircuitBreaker.State state, Instant openUntil) {
        super("Circuit breaker '" + circuitName + "' is " + state + " (openUntil=" + openUntil + ")");
        this.circuitName = circuitName;
        this.state = state;
        this.openUntil = openUntil;
    }

    public String circuitName() {
        return circuitName;
    }

    public CircuitBreaker.State state() {
        return state;
    }

    public Instant openUntil() {
        return openUntil;
    }
}

