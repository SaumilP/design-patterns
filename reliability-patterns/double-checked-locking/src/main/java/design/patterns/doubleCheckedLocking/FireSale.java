package design.patterns.doubleCheckedLocking;

import java.util.ArrayList;

/**
 * Singleton class
 */
public class FireSale {
    private static FireSale instance;
    private Inventory inventory;

    public FireSale(int inventorySize) {
        inventory = new Inventory(inventorySize, new ArrayList<Item>(inventorySize));
    }

    public static FireSale getInstance(int inventorySize){
        if(instance == null ){
            synchronized (FireSale.class){
                if(instance == null ){
                    instance = new FireSale(inventorySize);
                }
            }
        }
        return instance;
    }

    public Inventory getInventory(){
        return inventory;
    }
}
