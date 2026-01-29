package design.pattern.mvp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Model.
 */
public class ModelTest {
    /**
     * Verifies {@code shouldDefaultPassword}.
     */
    @Test
    public void shouldDefaultPassword() {
        Model model = new Model();

        assertEquals("topsecretpassword", model.getPassword());
    }

    /**
     * Verifies {@code shouldAllowPasswordUpdate}.
     */
    @Test
    public void shouldAllowPasswordUpdate() {
        Model model = new Model();

        model.setPassword("newsecret");

        assertEquals("newsecret", model.getPassword());
    }
}
