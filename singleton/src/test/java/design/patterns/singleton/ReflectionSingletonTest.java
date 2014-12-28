package design.patterns.singleton;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class ReflectionSingletonTest {

    private static final Logger Log = LoggerFactory.getLogger(ReflectionSingletonTest.class);

    @Test public void test() throws Exception {
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
