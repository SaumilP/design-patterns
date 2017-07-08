package design.patterns.singleton;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StaticBlockSingletonTest {

    @Test
    public void shouldSuccessfullyCreateSingletonFromStaticBlock() throws Exception {
        StaticBlockSingleton instance = StaticBlockSingleton.getInstance();
        assertThat(instance, is(notNullValue()));
    }
}