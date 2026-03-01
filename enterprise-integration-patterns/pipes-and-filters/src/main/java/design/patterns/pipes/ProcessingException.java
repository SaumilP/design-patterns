package design.patterns.pipes;

/**
 * Pipeline Stage Processing Exception
 */
public class ProcessingException extends Exception {

    private static final long serialVersionUID = 7813752744540231707L;

    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
