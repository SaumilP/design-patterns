package design.patterns.singleton;

/**
 * Created by PATEL1 on 12/28/14.
 */
public class BillPughSingleton {
    private BillPughSingleton() {
    }

    private static class SingletonHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
