package homework2;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class EvenIndexTestCase {

    private final EvenIndex evenIndex = new EvenIndex();

    @Test
    @DisplayName("Primo numero pari")
    public void testFindFirstEven() {
        List<Integer> numbers = Arrays.asList(1, 3, 4, 7, 8);
        int result = evenIndex.findEvenIndex(numbers, true);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Ultimo numero pari")
    public void testFindLastEven() {
        List<Integer> numbers = Arrays.asList(1, 3, 4, 7, 8);
        int result = evenIndex.findEvenIndex(numbers, false);
        assertEquals(4, result);
    }

    @Test
    @DisplayName("Solo numeri dispari")
    public void testListWithOnlyOddNumbers() {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7);
        int result = evenIndex.findEvenIndex(numbers, true);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Lista vuota")
    public void testEmptyListThrowsException() {
        List<Integer> numbers = List.of();
        assertThrows(IllegalArgumentException.class, () -> evenIndex.findEvenIndex(numbers, true));
    }

    @Test
    @DisplayName("Lista null")
    public void testNullListThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> evenIndex.findEvenIndex(null, true));
    }

}
