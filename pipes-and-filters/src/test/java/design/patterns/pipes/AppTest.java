package design.patterns.pipes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Basic Unit Test for {@link App}
 */
public class AppTest {
    /**
     * Verifies {@code shouldRunAppMain}.
     */
    @Test
    public void shouldRunAppMain() throws Exception {
        String[] args = {};
        assertDoesNotThrow(() -> App.main(args));
    }
}
