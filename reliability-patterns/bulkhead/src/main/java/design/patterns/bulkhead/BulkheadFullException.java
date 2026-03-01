package design.patterns.bulkhead;

public final class BulkheadFullException extends RuntimeException {
    public BulkheadFullException(String bulkheadName) {
        super("Bulkhead '" + bulkheadName + "' is full");
    }
}

