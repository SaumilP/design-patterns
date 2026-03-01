package design.pattern.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Delivery.
 */
public class DeliveryTest {
    /**
     * Verifies {@code shouldReturnThirtyMinutesForMatchingPinCode}.
     */
    @Test
    public void shouldReturnThirtyMinutesForMatchingPinCode() {
        Delivery delivery = new Delivery();

        int estimate = delivery.estimateTimeForDelivery("1234 Main Street");

        assertEquals(30, estimate);
    }

    /**
     * Verifies {@code shouldReturnSixtyMinutesForUnknownAddress}.
     */
    @Test
    public void shouldReturnSixtyMinutesForUnknownAddress() {
        Delivery delivery = new Delivery();

        int estimate = delivery.estimateTimeForDelivery("99 Market Street");

        assertEquals(60, estimate);
    }

    /**
     * Verifies {@code shouldReturnSixtyMinutesForNullAddress}.
     */
    @Test
    public void shouldReturnSixtyMinutesForNullAddress() {
        Delivery delivery = new Delivery();

        int estimate = delivery.estimateTimeForDelivery(null);

        assertEquals(60, estimate);
    }
}
