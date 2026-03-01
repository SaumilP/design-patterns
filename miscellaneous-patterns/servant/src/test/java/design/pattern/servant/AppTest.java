package design.pattern.servant;

import design.patterns.servant.App;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
        String[] args = {};
        assertDoesNotThrow(() -> App.main(args));

    } finally {

        System.setOut(originalOut);

        System.setErr(originalErr);

    }

    }
}
