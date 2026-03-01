package design.patterns.visitor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Tests for AppTest.
 */
public class AppTest {
    /**
     * Verifies {@code test}.
     */
    @Test public void shouldRunAppMain(){
 PrintStream originalOut = System.out;
 PrintStream originalErr = System.err;
 ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
 ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();
 System.setOut(new PrintStream(outBuffer));
 System.setErr(new PrintStream(errBuffer));
 try {
        String[] args = {};
        assertDoesNotThrow(() -> App.main( args ));
 } finally {
     System.setOut(originalOut);
     System.setErr(originalErr);
 }
 }
}
