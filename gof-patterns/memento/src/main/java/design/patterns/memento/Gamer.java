package design.patterns.memento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Gamer class
 */
public class Gamer {
    private int money;
    private List fruits = new ArrayList();
    private Random random = new Random();
    private static String[] fruitname = {
            "Apple", "Grape", "Banana", "Orange"
    };

    public Gamer(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public String getFruits() {
        String prefix = "";
        if ( random.nextBoolean()){
            prefix = "Delicious";
        }
        return prefix + fruitname[random.nextInt(fruitname.length)];
    }

    public void bet(){
        int dice = random.nextInt(6)+1;
        switch (dice){
            case 1: money += 100; printMessage("increase money"); break;
            case 2: money /= 100; printMessage("Decrease money"); break;
            case 6: String f = getFruits(); printMessage("Get fruit:" + f); fruits.add(f);  break;
            default: printMessage("Nothing has been done");
        }
    }

    public Memento createMemento(){
        Memento m = new Memento(money);
        Iterator iter = fruits.iterator();
        while(iter.hasNext()){
            String f = (String) iter.next();
            if(f.startsWith("Delicious")){
                m.addFruit(f);
            }
        }
        return m;
    }

    public void restoreMemento(Memento memento){
        this.money = memento.getMoney();
        this.fruits = memento.getFruits();
    }

    @Override public String toString() {
        return "Gamer{" +
               "money=" + money +
               ",fruits=" + fruits +
               '}';
    }

    private void printMessage(String s) {
        System.out.println(s);
    }
}
