package design.patterns.doubleCheckedLocking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for FireSale.
 */
public class FireSaleTest {
    /**
     * Verifies {@code shouldReturnSameInstanceForRepeatedCalls}.
     */
    @Test
    public void shouldReturnSameInstanceForRepeatedCalls() {
        FireSale first = FireSale.getInstance(2);
        FireSale second = FireSale.getInstance(5);

        assertSame(first, second);
    }

    /**
     * Verifies {@code shouldExposeInventoryThatAcceptsItems}.
     */
    @Test
    public void shouldExposeInventoryThatAcceptsItems() {
        Inventory inventory = new Inventory(1, new ArrayList<>());

        boolean accepted = inventory.stashItemForSale(new Item("Poster", 9.99));

        assertTrue(accepted);
    }
}
