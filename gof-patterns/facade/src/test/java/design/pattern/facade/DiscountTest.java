package design.pattern.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for Discount.
 */
public class DiscountTest {
    /**
     * Verifies {@code shouldThrowWhenOrderItemsIsZero}.
     */
    @Test
    public void shouldThrowWhenOrderItemsIsZero() {
        Discount discount = new Discount();

        Exception error = assertThrows(Exception.class, () -> discount.getDiscountAmount(0));

        assertEquals("At least one item in order must be requested", error.getMessage());
    }

    /**
     * Verifies {@code shouldReturnTieredDiscounts}.
     */
    @Test
    public void shouldReturnTieredDiscounts() throws Exception {
        Discount discount = new Discount();

        assertEquals(0, discount.getDiscountAmount(1), 0.0);
        assertEquals(10, discount.getDiscountAmount(2), 0.0);
        assertEquals(25, discount.getDiscountAmount(3), 0.0);
        assertEquals(50, discount.getDiscountAmount(4), 0.0);
    }
}
