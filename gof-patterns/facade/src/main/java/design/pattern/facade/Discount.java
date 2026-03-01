package design.pattern.facade;

/**
 * Class provides Discount value based on number of ordered items
 */
public class Discount {
    public double getDiscountAmount(int orderItems) throws Exception {
       if( orderItems == 0){
           throw new Exception("At least one item in order must be requested");
       }

        switch (orderItems){
            case 1:
                return 0;
            case 2:
                return 10;
            case 3:
                return 25;
            default:
                return 50;
        }
    }
}
