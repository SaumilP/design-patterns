package design.patterns.singleton;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreadSafeSingletonTest {

    @Test
    public void shouldSuccessfullyCreateThreadsafeSingleton() throws Exception {
        ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
        assertThat(instance, is(notNullValue()));
    }

    @Test
    public void shouldSuccessfullyCreateDoublyLockedThreadsafeSingleton() throws Exception {
        ThreadSafeSingleton instance = ThreadSafeSingleton.getInstanceUsingDoubleLocking();
        assertThat(instance, is(notNullValue()));
    }
}