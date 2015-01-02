package design.pattern.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Facade Pattern Client
 */
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        PizzaCallCenterFacade callCenter = new PizzaCallCenterFacade();

        String deliveryAddress = "10 Main Street, Sandton, Johannesburg - 2196";
        int requestedPizzas = 3;
        int expectedDeliveryTime = 20;

        log.debug("Calling call center....");
        double amount = callCenter.getTotalOrderAmount(deliveryAddress, requestedPizzas, expectedDeliveryTime);
        log.debug("Address:{}\nTotal Amount of Pizza:{}\nDelivery Time:{}\nPay Amount:{}\n",
                  deliveryAddress,
                  requestedPizzas,
                  expectedDeliveryTime,
                  amount);

        deliveryAddress = "165 West Street, Sandton, Johannesburg - 2197";
        log.debug("Calling call center ....");
        double deliveryTime = callCenter.getEstimatedDeliveryTime(deliveryAddress);
        log.debug("Address:{}\nDelivery Time:{}\n", deliveryAddress, deliveryTime);
    }
}
