import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputationsTest {

    @Test
    void testFibonacci() {
        assertEquals(0, Computations.fibonacci(0));
        assertEquals(1, Computations.fibonacci(1));
        assertEquals(5, Computations.fibonacci(5));
        assertEquals(13, Computations.fibonacci(7));
    }

    @Test
    void testNegativeFibonacci() {
        assertThrows(IllegalArgumentException.class, () -> {
            Computations.fibonacci(-1);
        });
    }

    @Test
    void testIsPrime() {
        assertTrue(Computations.isPrime(2));
        assertTrue(Computations.isPrime(13));
        assertFalse(Computations.isPrime(1));
        assertFalse(Computations.isPrime(10));
    }

    @Test
    void testIsEven() {
        assertTrue(Computations.isEven(4));
        assertFalse(Computations.isEven(5));
    }

    @Test
    void testIsOdd() {
        assertTrue(Computations.isOdd(5));
        assertFalse(Computations.isOdd(4));
    }

    @Test
    void testToCelsius() {
        assertEquals(0.0, Computations.toCelsius(32), 0.001);
        assertEquals(100.0, Computations.toCelsius(212), 0.001);
    }

    @Test
    void testToFahrenheit() {
        assertEquals(32.0, Computations.toFahrenheit(0), 0.001);
        assertEquals(212.0, Computations.toFahrenheit(100), 0.001);
    }
}