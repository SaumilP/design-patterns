package design.pattern.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for PizzaCallCenterFacade.
 */
public class PizzaCallCenterFacadeTest {
    /**
     * Verifies {@code shouldCalculateTotalOrderAmountWithMargin}.
     */
    @Test
    public void shouldCalculateTotalOrderAmountWithMargin() throws Exception {
        PizzaCallCenterFacade facade = new PizzaCallCenterFacade();

        double amount = facade.getTotalOrderAmount("1234 Main Street", 3, 20);

        assertEquals(263.5, amount, 0.0001);
    }

    /**
     * Verifies {@code shouldCalculateTotalOrderAmountWithoutMargin}.
     */
    @Test
    public void shouldCalculateTotalOrderAmountWithoutMargin() throws Exception {
        PizzaCallCenterFacade facade = new PizzaCallCenterFacade();

        double amount = facade.getTotalOrderAmount("99 Market Street", 3, 60);

        assertEquals(412.5, amount, 0.0001);
    }

    /**
     * Verifies {@code shouldReturnEstimatedDeliveryTime}.
     */
    @Test
    public void shouldReturnEstimatedDeliveryTime() {
        PizzaCallCenterFacade facade = new PizzaCallCenterFacade();

        int estimate = facade.getEstimatedDeliveryTime("1234 Main Street");

        assertEquals(30, estimate);
    }
}
