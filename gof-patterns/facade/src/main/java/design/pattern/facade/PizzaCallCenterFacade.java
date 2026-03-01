package design.pattern.facade;

/**
 * Facade class provides unified interface to set of different other functions
 */
public class PizzaCallCenterFacade {
    private Delivery delivery = new Delivery();
    private Discount discount = new Discount();
    private Order order = new Order();

    private static final double DELIVERY_MARGIN = 0.1;
    private static final int DELIVERY_PER_MIN_RATE = 5;

    public double getTotalOrderAmount(String areaPinCode,
                                      int pizzaAmount,
                                      int desirableDeliveryPrice) throws Exception {
        double discountAmount = discount.getDiscountAmount(pizzaAmount);
        int estimatedDeliveryTime = delivery.estimateTimeForDelivery(areaPinCode);

        double pizzaCost = order.getOrderAmount(pizzaAmount);
        double deliveryCost = estimatedDeliveryTime * DELIVERY_PER_MIN_RATE;

        double costWithDiscount = pizzaCost - (pizzaCost*discountAmount)/100.0;
        double deliveryCostWithMargin = deliveryCost + Math.max(estimatedDeliveryTime - desirableDeliveryPrice,0)*DELIVERY_MARGIN;

        double calculatedPrice = costWithDiscount + deliveryCostWithMargin;
        return calculatedPrice;
    }

    public int getEstimatedDeliveryTime(String areaPinCode){
        return delivery.estimateTimeForDelivery(areaPinCode);
    }
}
