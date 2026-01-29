package design.patterns.doubleCheckedLocking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Item.
 */
public class ItemTest {
    /**
     * Verifies {@code shouldExposeNameAndAmount}.
     */
    @Test
    public void shouldExposeNameAndAmount() {
        Item item = new Item("Gadget", 25.50);

        assertEquals("Gadget", item.getName());
        assertEquals(25.50, item.getAmount(), 0.0);
    }

    /**
     * Verifies {@code shouldAllowUpdatingFields}.
     */
    @Test
    public void shouldAllowUpdatingFields() {
        Item item = new Item("Old", 10.0);

        item.setName("New");
        item.setAmount(15.75);

        assertEquals("New", item.getName());
        assertEquals(15.75, item.getAmount(), 0.0);
    }
}
