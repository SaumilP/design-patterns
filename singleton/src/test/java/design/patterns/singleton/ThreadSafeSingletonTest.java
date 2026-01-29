package design.patterns.singleton;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for ThreadSafeSingletonTest.
 */
public class ThreadSafeSingletonTest {

    /**
     * Verifies {@code shouldSuccessfullyCreateThreadsafeSingleton}.
     */
    @Test
    public void shouldSuccessfullyCreateThreadsafeSingleton() throws Exception {
        ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
        assertThat(instance, is(notNullValue()));
    }

    /**
     * Verifies {@code shouldSuccessfullyCreateDoublyLockedThreadsafeSingleton}.
     */
    @Test
    public void shouldSuccessfullyCreateDoublyLockedThreadsafeSingleton() throws Exception {
        ThreadSafeSingleton instance = ThreadSafeSingleton.getInstanceUsingDoubleLocking();
        assertThat(instance, is(notNullValue()));
    }
}