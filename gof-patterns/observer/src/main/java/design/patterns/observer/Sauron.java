package design.patterns.observer;

import design.patterns.observer.comparators.ItemComparator;
import design.patterns.observer.items.Item;
import design.patterns.observer.items.ItemType;
import design.patterns.observer.items.PreciousItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Observation Subscriber class
 */
public class Sauron implements Observer {
    private List<Item> items;

    public Sauron() {
        items = new ArrayList<>();
        PreciousItem item = new PreciousItem();
        item.setName("One Ring");
        item.setOwnerName("Sauron");
        item.setRareItem(true);
        item.setType(ItemType.ONE_RING);
        items.add( item );
    }

    @Override public void update(String message, Item item) {
        if( item != null && item.getClass() == PreciousItem.class ){
            PreciousItem preciousItem = (PreciousItem)item;
            if ( preciousItem.getType() == ItemType.ONE_RING ){
                System.out.println(String.format(" !!!Sauron is watching %s now !!!!", message));
            }
        }
    }

    @Override public boolean hasLostItem(Item item) {
        if( item != null && item.getClass() == PreciousItem.class){
            PreciousItem preciousItem = (PreciousItem)item;
            return isMyPrecious(preciousItem);
        }
        return false;
    }

    private boolean isMyPrecious(Item item){
        final ItemComparator comparator = new ItemComparator();
        for (Item myItems : items) {
            if ( comparator.compare( myItems, item) == 0 ){
                return true;
            }
        }
        return false;
    }
}