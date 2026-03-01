package design.patterns.singleton;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for LazyInitializedSingletonTest.
 */
public class LazyInitializedSingletonTest {

    /**
     * Verifies {@code shouldSuccessfullyCreateLazyInitializedSingleton}.
     */
    @Test
    public void shouldSuccessfullyCreateLazyInitializedSingleton() throws Exception {
        LazyInitializedSingleton instance = LazyInitializedSingleton.getInstance();
        assertThat(instance, is(notNullValue()));
    }
}