import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Part4Test {

    @Test
    void failingTest() {
        System.out.println("Running failingTest...");
        assertEquals(1, 2);
    }

    @Test
    void passingTest() {
        System.out.println("Running passingTest...");
        assertEquals(5, 5);
    }

    @Test
    void anotherPassingTest() {
        System.out.println("Running anotherPassingTest...");
        assertTrue(true);
    }
}