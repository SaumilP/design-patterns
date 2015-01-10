package design.patterns.doubleCheckedLocking;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Product Inventory
 */
public class Inventory {
    private int totalNoOfSize;
    private List<Item> items;
    private Lock lock = new ReentrantLock();

    public Inventory(int totalNoOfSize, List<Item> items) {
        this.totalNoOfSize = totalNoOfSize;
        this.items = items;
    }

    public boolean stashItemForSale(Item item){
        if( items.size() < totalNoOfSize ){
            lock.lock();
            try{
                if( items.size() < totalNoOfSize ){
                    items.add(item);
                    System.out.println("[" + Thread.currentThread() + "] Stashing " + item.toString() );
                    return true;
                }
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

}
