package design.pattern.methodobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for RefactoredAccount.
 */
public class RefactoredAccountTest {
    /**
     * Verifies {@code shouldComputeValueUsingMethodObject}.
     */
    @Test
    public void shouldComputeValueUsingMethodObject() {
        RefactoredAccount account = new RefactoredAccount(120, 25, 1920, 3);

        double result = account.compute();

        assertEquals(1_613_500.0, result, 0.0);
    }
}
