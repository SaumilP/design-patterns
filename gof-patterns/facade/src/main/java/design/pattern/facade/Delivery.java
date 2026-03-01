package design.pattern.facade;

/**
 * Class providing order delivery estimates
 */
public class Delivery {
    public int estimateTimeForDelivery(String address){
        if( address != null && !address.isEmpty() ){
            if( address.toLowerCase().contains("1234") ){
                return 30;
            }
        }
        return 60;
    }
}
