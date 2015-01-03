package design.patterns.flyweight;

/**
 * Context Class to hold content
 */
public class NoodleOrderContext {
    private int tableNumber;

    public NoodleOrderContext(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
