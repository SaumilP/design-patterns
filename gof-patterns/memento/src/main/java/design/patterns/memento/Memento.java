package design.patterns.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Memento to hold state
 */
public class Memento {
    private int money;
    List fruits;

    public Memento(int money) {
        this.money = money;
        fruits = new ArrayList();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List getFruits() {
        return fruits;
    }

    public void setFruits(List fruits) {
        this.fruits = fruits;
    }

    public void addFruit(String f) {
        fruits.add(f);
    }
}
