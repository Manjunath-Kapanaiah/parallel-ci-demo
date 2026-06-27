import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private final App app = new App();

    @Test
    void testAdd() {
        assertEquals(8, app.add(5, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(2, app.subtract(5, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(15, app.multiply(5, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2.5, app.divide(5, 2));
    }

    @Test
    void testDivideByZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> app.divide(5, 0));
    }

    @Test
    void testIsPrimeTrue() {
        assertTrue(app.isPrime(7));
    }

    @Test
    void testIsPrimeFalse() {
        assertFalse(app.isPrime(8));
    }
}
