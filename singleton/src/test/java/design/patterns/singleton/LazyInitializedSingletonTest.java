package design.patterns.singleton;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LazyInitializedSingletonTest {

    @Test
    public void shouldSuccessfullyCreateLazyInitializedSingleton() throws Exception {
        LazyInitializedSingleton instance = LazyInitializedSingleton.getInstance();
        assertThat(instance, is(notNullValue()));
    }
}