package design.patterns.singleton;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * Simple Test case for eagerly initialized singleton
 */
public class EagerInitializedSingletonTest {

    @Test
    public void test() throws Exception {
        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;

        Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            constructor.setAccessible(true);
            instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
            break;
        }
    }
}
