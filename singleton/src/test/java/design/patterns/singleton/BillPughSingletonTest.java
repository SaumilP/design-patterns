package design.patterns.singleton;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for BillPughSingletonTest.
 */
public class BillPughSingletonTest {

    /**
     * Verifies {@code shouldSuccessfullyCreateBillPuggedSingleton}.
     */
    @Test
    public void shouldSuccessfullyCreateBillPuggedSingleton() throws Exception {
        BillPughSingleton instance = BillPughSingleton.getInstance();
        assertThat(instance, is(notNullValue()));
    }
}