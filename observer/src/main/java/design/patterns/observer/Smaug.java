package design.patterns.observer;

import design.patterns.observer.comparators.ItemComparator;
import design.patterns.observer.items.Item;
import design.patterns.observer.items.ItemType;
import design.patterns.observer.items.PreciousItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Observation Subscriber for {@link ItemType#ARKENSTONE}
 */
public class Smaug implements Observer {

    private List<Item> items;

    public Smaug() {
        items = new ArrayList<>();
        PreciousItem item = new PreciousItem();
        item.setName("Kings Jewell");
        item.setOwnerName("Finders Item");
        item.setRareItem(true);
        item.setType(ItemType.ARKENSTONE);
        items.add( item );
    }

    @Override public void update(String message, Item item) {
        if( item != null && item.getClass() == PreciousItem.class ){
            PreciousItem preciousItem = (PreciousItem)item;
            if( preciousItem.getType() == ItemType.ARKENSTONE){
                System.out.println(String.format(" !!!Smaug is looking for %s now !!!!", message));
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
