package design.patterns.observer;

import design.patterns.observer.items.Item;

/**
 * Observable interface providing methods to update observers
 */
public interface Observer {
    public void update(String message, Item item);
    public boolean hasLostItem(Item item);
}
