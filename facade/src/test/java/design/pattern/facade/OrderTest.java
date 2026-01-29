package design.pattern.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Order.
 */
public class OrderTest {
    /**
     * Verifies {@code shouldCalculateOrderAmount}.
     */
    @Test
    public void shouldCalculateOrderAmount() {
        Order order = new Order();

        double amount = order.getOrderAmount(3);

        assertEquals(150, amount, 0.0);
    }
}
