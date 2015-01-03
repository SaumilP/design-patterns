package design.patterns.observer;

import design.patterns.observer.items.ItemType;
import design.patterns.observer.items.PreciousItem;

/**
 * Observer Design Pattern Client
 */
public class App {
    public static void main(String[] args){
        Hobbit hobbitThief = new Thief("Bilbo Baggins");
        Observer dragon = new Smaug();
        Observer evilVillan = new Sauron();

        /* Rare Items */
        PreciousItem rullingRing = new PreciousItem("Gold Ring", ItemType.ONE_RING, true);
        PreciousItem mustHaveRock = new PreciousItem("Shining Rock", ItemType.ARKENSTONE, true);

        System.out.println(" RARE ITEM-1....");
        hobbitThief.findsAnItem(evilVillan, rullingRing);
        hobbitThief.usesItem( evilVillan, rullingRing );
        hobbitThief.loosesItem(evilVillan, rullingRing);

        System.out.println("\n RARE ITEM-2....");
        hobbitThief.findsAnItem(dragon, mustHaveRock);
        hobbitThief.usesItem( dragon, mustHaveRock );
        hobbitThief.loosesItem(dragon, mustHaveRock);
    }
}
