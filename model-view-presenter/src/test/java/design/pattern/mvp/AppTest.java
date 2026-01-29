package design.pattern.mvp;

import org.junit.jupiter.api.Test;

import java.awt.AWTError;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Basic Test for {@link App}
 */
public class AppTest {
    /**
     * Verifies {@code shouldRunAppMain}.
     */
    @Test
    public void shouldRunAppMain(){

    PrintStream originalOut = System.out;

    PrintStream originalErr = System.err;

    ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();

    ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outBuffer));

    System.setErr(new PrintStream(errBuffer));

    try {
        assumeFalse(GraphicsEnvironment.isHeadless(), "GUI tests require a display.");
        try {
            Toolkit.getDefaultToolkit();

    } finally {

        System.setOut(originalOut);

        System.setErr(originalErr);

    }

    } catch (AWTError error) {
            assumeFalse(true, "GUI tests require a functional display.");
        }
        String[] args = {};
        assertDoesNotThrow(() -> App.main(args));
    }
}
