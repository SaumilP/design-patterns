package design.patterns.singleton;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for StaticBlockSingletonTest.
 */
public class StaticBlockSingletonTest {

    /**
     * Verifies {@code shouldSuccessfullyCreateSingletonFromStaticBlock}.
     */
    @Test
    public void shouldSuccessfullyCreateSingletonFromStaticBlock() throws Exception {
        StaticBlockSingleton instance = StaticBlockSingleton.getInstance();
        assertThat(instance, is(notNullValue()));
    }
}