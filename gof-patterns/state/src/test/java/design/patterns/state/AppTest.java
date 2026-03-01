package design.patterns.state;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Basic Test for {@link App}
 */
public class AppTest {
    /**
     * Verifies {@code shouldRunAppMain}.
     */
    @Test
    public void shouldRunAppMain(){
        String[] args = {};
        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outBuffer));
        System.setErr(new PrintStream(errBuffer));
        try {
            assertDoesNotThrow(() -> App.main(args));
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }
    }
}
