package design.patterns.observer;

import design.patterns.observer.items.Item;

/**
 * Observable Subject Class
 */
public interface Hobbit {
    public void findsAnItem(Observer observer, Item item);
    public void usesItem(Observer observer, Item item);
    public void loosesItem(Observer observer, Item item);
    public void notifyObservers(Observer observer, Item item);
}
