package design.patterns.singleton;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class StaticBlockSingleton {
    private static StaticBlockSingleton instance;

    public StaticBlockSingleton() {
    }

    static {
        try{
            instance = new StaticBlockSingleton();
        } catch( Exception ex ){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}
