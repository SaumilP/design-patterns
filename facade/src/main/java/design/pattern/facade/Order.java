package design.pattern.facade;

/**
 * Order Class calculates order amounts
 */
public class Order {

    private static final int PER_ITEM_AMOUNT = 50;

    public double getOrderAmount(int orderItems){
        double amount = orderItems * PER_ITEM_AMOUNT;
        return amount;
    }
}
