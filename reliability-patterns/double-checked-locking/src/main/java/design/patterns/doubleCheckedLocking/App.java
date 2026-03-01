package design.patterns.doubleCheckedLocking;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Double-Checked Locking Design Pattern Client
 */
public class App {
    public static void main(String[] args ){
        ExecutorService service = Executors.newFixedThreadPool(3);
        for(int i=0; i<3; i++){
            service.execute( new Runnable() {
                @Override public void run() {
                    Random random = new Random(10);
                    Item item = new Item("Item" + random.nextInt(), 3 * random.nextInt());
                    while( FireSale.getInstance(100).getInventory().stashItemForSale(item ) );
                }
            });
        }
    }
}
