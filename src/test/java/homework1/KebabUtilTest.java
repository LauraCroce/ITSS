package homework1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class KebabUtilTest {

    private final KebabUtil kebabUtil = new KebabUtil();

    @Test
    @DisplayName("Converti stringa in kebab case con lettere minuscole")
    void testConMinuscole() {
        // Verifica che la stringa venga convertita in kebab case con lettere minuscole
        String input = "Simple test";
        String expected = "simple-test";
        String result = kebabUtil.convertToKebabCase(input, true, new char[]{' '});
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Converti stringa in kebab case mantenendo le maiuscole originali")
    void testSenzaConversioneMinuscole() {
        // Verifica che la stringa venga convertita in kebab case mantenendo le maiuscole originali
        String input = "Another Test";
        String expected = "Another-Test";
        String result = kebabUtil.convertToKebabCase(input, false, new char[]{' '});
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Converti stringa con delimitatore di default (spazio)")
    void testConDelimitatoreDiDefault() {
        // Verifica che lo spazio sia usato come delimitatore di default se l'array dei delimitatori è vuoto
        String input = "This is a default test";
        String expected = "this-is-a-default-test";
        String result = kebabUtil.convertToKebabCase(input, true, new char[]{});
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Gestione input nullo")
    void testInputNull() {
        // Verifica che con un input nullo il metodo restituisca null
        assertNull(kebabUtil.convertToKebabCase(null, true, new char[]{' '}));
    }
}

