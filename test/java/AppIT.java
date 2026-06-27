import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration-style test - simulates a broader workflow combining
 * multiple App operations together, as opposed to isolated unit tests.
 * Runs in the Maven "integration-test" phase via the Failsafe plugin.
 */
public class AppIT {

    private final App app = new App();

    @Test
    void testEndToEndCalculationWorkflow() {
        int sum = app.add(10, 5);
        int product = app.multiply(sum, 2);
        double result = app.divide(product, 3);

        assertEquals(15, sum);
        assertEquals(30, product);
        assertEquals(10.0, result);
    }

    @Test
    void testPrimeCheckWorkflow() {
        int candidate = app.add(2, 5); // 7
        assertTrue(app.isPrime(candidate));
    }
}
