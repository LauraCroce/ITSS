package homework1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KebabUtilParameterizedTest {

    // Istanza della classe KebabUtil per eseguire i test
    KebabUtil kebabUtil = new KebabUtil();

    /**
     * Fornisce una serie di argomenti per i test di `convertToKebabCase` che coprono vari boundary cases.
     * Ogni set di argomenti rappresenta un caso limite o una situazione edge case che il metodo dovrebbe gestire correttamente.
     *
     * @return Stream di argomenti che rappresentano le combinazioni di input per i test.
     */
    static Stream<Arguments> provideBoundaryCases() {
        return Stream.of(
                //Input nullo; il metodo dovrebbe restituire null.
                Arguments.of(null, true, null, null, "Caso 1"),

                //Input vuoto; il metodo dovrebbe restituire una stringa vuota.
                Arguments.of("", true, null, "", "Caso 2"),

                //Singolo carattere in maiuscolo con minuscolo attivo.
                Arguments.of("A", true, null, "a", "Caso 3"),

                //Singolo carattere in maiuscolo con minuscolo disattivo.
                Arguments.of("A", false, null, "A", "Caso 4"),

                //Stringa composta solo da delimitatori; dovrebbe restare invariata.
                Arguments.of("-----", true, new char[]{'-'}, "-----", "Caso 5"),

                //Nessun delimitatore specificato; usa lo spazio come delimitatore predefinito.
                Arguments.of("Hello World", true, null, "hello-world", "Caso 6"),

                //Array di delimitatori vuoto; usa lo spazio come delimitatore predefinito.
                Arguments.of("Hello World", false, new char[]{}, "Hello-World", "Caso 7"),

                //Stringa senza delimitatori; dovrebbe restare invariata.
                Arguments.of("HelloWorld", true, new char[]{'-'}, "helloworld", "Caso 8"),

                //Delimitatori speciali (underscore e virgola).
                Arguments.of("Hello_World, Test!", true, new char[]{'_', ',','!'}, "hello-world-test", "Caso 9"),

                //Stringa molto lunga per testare le prestazioni.
                Arguments.of(generateLongString(1000), true, new char[]{' '}, generateExpectedLongOutput(1000), "Caso 10"),

                //Solo numeri e delimitatori; i numeri rimangono invariati.
                Arguments.of("123 456 789", true, null, "123-456-789", "Caso 11"),

                //Caratteri accentati e speciali per testare il supporto UTF-8.
                Arguments.of("Città e natura", true, new char[]{' '}, "città-e-natura", "Caso 12"),

                //Simboli matematici come delimitatori.
                Arguments.of("Math+Physics=Science", true, new char[]{'+', '='}, "math-physics-science", "Caso 13"),

                //Spazi iniziali e finali nella stringa.
                Arguments.of("  Spaced Out  ", true, new char[]{' '}, "spaced-out", "Caso 14"),

               // --- Casi Avanzati e Creativi ---

                //Sequenza di delimitatori misti.
                Arguments.of("One...Two--Three//Four", true, new char[]{'.', '-', '/'}, "one-two-three-four", "Caso 15"),

                //Parentesi e punteggiatura come delimitatori.
                Arguments.of("(Hello), [World]!", true, new char[]{' ', ',', '(', ')', '!',']', '[' }, "hello-world", "Caso 16"),

                //Caratteri asiatici nella stringa; dovrebbe restare invariata.
                Arguments.of("こんにちは 世界", true, new char[]{' '}, "こんにちは-世界", "Caso 17")
        );
    }

    /**
     * Test parametrizzato per verificare il comportamento del metodo `convertToKebabCase`
     * in diversi boundary cases.
     *
     * @param input       La stringa di input da convertire.
     * @param toLowerCase Flag che indica se la stringa risultante deve essere in minuscolo.
     * @param delimiters  Array di delimitatori da sostituire con "-".
     * @param expected    L'output atteso della conversione.
     * @param description Descrizione del caso di test per una migliore tracciabilità dei risultati.
     */
    @ParameterizedTest(name = "{4}") // Utilizza la descrizione del caso per migliorare la leggibilità dei risultati
    @MethodSource("provideBoundaryCases") // Usa il metodo "provideBoundaryCases" come fonte di dati
    @DisplayName("Test dei boundary cases per convertToKebabCase")
    void testBoundaryCases(String input, boolean toLowerCase, char[] delimiters, String expected, String description) {
        // Esegue il metodo convertToKebabCase e verifica il risultato rispetto all'output atteso
        String result = kebabUtil.convertToKebabCase(input, toLowerCase, delimiters);
        assertEquals(expected, result, description);
    }

    /**
     * Metodo helper per generare una stringa lunga utilizzata nei test,
     * con parole ripetute e separate da spazi.
     *
     * @param length Lunghezza desiderata della stringa.
     * @return Una stringa lunga generata per i test.
     */
    private static String generateLongString(int length) {
        return "a ".repeat(length / 2).trim(); // Ripete "a " per raggiungere la lunghezza specificata
    }

    /**
     * Metodo helper per generare l'output atteso di una stringa lunga,
     * sostituendo gli spazi con trattini.
     *
     * @param length Lunghezza desiderata della stringa.
     * @return L'output atteso con delimitatori sostituiti da trattini.
     */
    private static String generateExpectedLongOutput(int length) {
        return "a-".repeat(length / 2).trim().replaceAll("-$", ""); // Ripete "a-" per generare l'output atteso
    }
}
