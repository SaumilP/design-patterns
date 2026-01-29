package design.pattern.methodobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Account.
 */
public class AccountTest {
    /**
     * Verifies {@code shouldCalculateValueUsingLegacyFormula}.
     */
    @Test
    public void shouldCalculateValueUsingLegacyFormula() {
        Account account = new Account();

        double result = account.calculateValue(120, 25, 1920);

        assertEquals(1_613_500.0, result, 0.0);
    }
}
