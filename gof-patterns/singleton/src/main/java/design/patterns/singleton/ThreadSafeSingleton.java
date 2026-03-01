package design.patterns.singleton;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    public ThreadSafeSingleton() {
    }

    public static synchronized ThreadSafeSingleton getInstance(){
        if ( instance == null ){
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public static ThreadSafeSingleton getInstanceUsingDoubleLocking(){
        if ( instance == null ){
            synchronized (ThreadSafeSingleton.class){
                if ( instance == null ){
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
