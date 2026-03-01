package design.patterns.doubleCheckedLocking;

/**
 * Item to be sale in Black Friday Sale
 */
public class Item {
    private String name;
    private double amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Item(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override public String toString() {
        return "Item{" +
               "name='" + name +
               ", amount=" + amount +
               '}';
    }
}
