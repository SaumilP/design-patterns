package design.patterns.singleton;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class LazyInitializedSingleton {
    private static LazyInitializedSingleton instance;

    public LazyInitializedSingleton() {
    }

    public static LazyInitializedSingleton getInstance(){
        if ( instance == null ){
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}
