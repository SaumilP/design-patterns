package design.patterns.singleton;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class EagerInitializedSingleton {
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    private EagerInitializedSingleton(){
    }

    public static EagerInitializedSingleton getInstance(){
        return instance;
    }
}
