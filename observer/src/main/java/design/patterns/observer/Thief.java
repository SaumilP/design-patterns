package design.patterns.observer;

import design.patterns.observer.items.Item;
import design.patterns.observer.items.ItemType;
import design.patterns.observer.items.PreciousItem;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Subject Class to be observed
 */
public class Thief implements Hobbit {

    private String name;
    private Map<ItemType,Observer> observers;

    public Thief(String name) {
        this.name = name;
        observers = new EnumMap<>(ItemType.class);
    }

    public String getName() {
        return name;
    }

    @Override public void findsAnItem(Observer observer, Item item) {
        System.out.println(String.format("%s finds a precious item[%s] of Type[%s]", getName(), item.getName(), item.getType().getName() ) );
        if ( observer.hasLostItem( item ) ){
            observers.put( item.getType(), observer );
        }
    }

    @Override public void usesItem(Observer observer, Item item) {
        System.out.println(String.format("%s uses item[%s]", getName(), item.getName() ) );
        notifyObservers(observer,item);
    }

    @Override public void loosesItem(Observer observer, Item item) {
        System.out.println(String.format("%s looses or removes item[%s]", getName(), item.getName() ) );
        if ( item != null && observers != null && observers.size() > 0 && observers.containsKey(item.getType()) ){
            Iterator<Map.Entry<ItemType, Observer>> iterator = observers.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<ItemType, Observer> entry = iterator.next();
                PreciousItem preciousItem = null;
                if( item instanceof PreciousItem ){
                    preciousItem = (PreciousItem) item;
                }

                if( preciousItem != null && entry.getKey() == item.getType() ){
                    System.out.println(String.format("Observer[%s] not interested anymore in [%s]", observer.getClass().getSimpleName(), getName() ) );
                    iterator.remove();
                }
            }
        }
    }

    @Override public void notifyObservers(Observer observer, Item item) {
        if( item != null && observers != null && observers.size() > 0 ){
            for (Map.Entry<ItemType, Observer> itemTypeObserverEntry : observers.entrySet()) {
                if(itemTypeObserverEntry.getKey() == item.getType() ){
                    System.out.println(String.format("Notifying Observer[%s]... ", observer.getClass().getSimpleName() ) );
                    itemTypeObserverEntry.getValue().update(getName(), item);
                }
            }
        }
    }
}
