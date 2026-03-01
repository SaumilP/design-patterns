package design.patterns.doubleCheckedLocking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for Inventory.
 */
public class InventoryTest {
    /**
     * Verifies {@code shouldAcceptItemsUntilCapacityReached}.
     */
    @Test
    public void shouldAcceptItemsUntilCapacityReached() {
        Inventory inventory = new Inventory(1, new ArrayList<>());

        assertTrue(inventory.stashItemForSale(new Item("Poster", 3.99)));
        assertFalse(inventory.stashItemForSale(new Item("Sticker", 1.25)));
    }
}
