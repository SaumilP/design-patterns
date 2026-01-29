package design.patterns.memento;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Memento.
 */
public class MementoTest {
    /**
     * Verifies {@code shouldStoreMoneyAndFruits}.
     */
    @Test
    public void shouldStoreMoneyAndFruits() {
        Memento memento = new Memento(50);
        memento.addFruit("Delicious Apple");

        List fruits = memento.getFruits();

        assertEquals(50, memento.getMoney());
        assertEquals(1, fruits.size());
        assertEquals("Delicious Apple", fruits.get(0));
    }

    /**
     * Verifies {@code shouldRestoreMoneyFromMemento}.
     */
    @Test
    public void shouldRestoreMoneyFromMemento() {
        Gamer gamer = new Gamer(5);
        Memento memento = new Memento(200);
        List fruits = new ArrayList();
        fruits.add("Delicious Orange");
        memento.setFruits(fruits);

        gamer.restoreMemento(memento);

        assertEquals(200, gamer.getMoney());
    }
}
