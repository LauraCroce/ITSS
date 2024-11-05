package homework2;

// Importazione delle librerie necessarie per i test
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

// Classe di test per la classe EvenIndex
class EvenIndexTest {
    // Istanza della classe EvenIndex da testare
    EvenIndex evenIndex = new EvenIndex();

    

    // Test per verificare che venga sollevata un'eccezione quando si passa una lista di numeri invalidi
    @Property(generation = GenerationMode.RANDOMIZED) // Indica che i dati di input saranno generati casualmente
    @Report(Reporting.GENERATED) // Crea un report dei test generati
    @StatisticsReport(format = Histogram.class) // Report statistico in formato istogramma
    void testInvalid(@ForAll @Size(max = 100) List<@IntRange(min = -200, max = 0) Integer> numbers,
                     @ForAll boolean findFirst) {
        // Verifica che venga sollevata un'eccezione se la lista contiene numeri non validi (solo negativi o zero)
        assertThrows(IllegalArgumentException.class, () -> evenIndex.findEvenIndex(numbers, findFirst));
    }

    // Test per verificare il comportamento quando si passa una lista di numeri dispari
    @Property(generation = GenerationMode.RANDOMIZED)
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void testFail(@ForAll("odds") List<@IntRange(min = 1, max = 100) Integer> oddNumbers,
                  @ForAll boolean findFirst) {
        // Verifica che tutti i numeri nella lista siano dispari
        assertTrue(oddNumbers.stream().allMatch(n -> n % 2 != 0));

        // Il risultato atteso è -1 poiché non ci sono numeri pari nella lista
        int expected = -1;
        int actual = evenIndex.findEvenIndex(oddNumbers, findFirst);

        // Verifica che il risultato effettivo corrisponda a quello atteso
        assertEquals(expected, actual);
    }

    // Test per verificare il comportamento con una lista contenente numeri misti (pari e dispari)
    @Property(generation = GenerationMode.RANDOMIZED)
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void testPass(@ForAll @Size(min = 20, max = 50) List<@IntRange(min = 1) Integer> originalNumbers,
                  @ForAll boolean findFirst,
                  @ForAll @Size(value = 3) @UniqueElements List<@IntRange(max = 19) Integer> indexesToAddEvens,
                  @ForAll("evens") @Size(value = 3) List<Integer> evensToAdd) {

        // Crea una copia della lista originale per essere modificata
        List<Integer> numbers = new ArrayList<>(originalNumbers);

        // Ordina gli indici in ordine crescente per l'inserimento dei numeri pari
        Collections.sort(indexesToAddEvens);

        // Aggiunge i numeri pari alla lista nei rispettivi indici specificati
        for (int i = 0; i < indexesToAddEvens.size(); i++) {
            numbers.add(indexesToAddEvens.get(i), evensToAdd.get(i));
        }

        int expected;
        if (findFirst) {
            expected = findFirstEven(numbers); // Trova l'indice del primo numero pari
        } else {
            expected = findLastEven(numbers); // Trova l'indice dell'ultimo numero pari
        }

        int actual = evenIndex.findEvenIndex(numbers, findFirst);

        // Raccoglie statistiche per la distribuzione dei numeri pari e dispari
        for (Integer number : numbers) {
            Statistics.label("Distribuzione di numeri pari e dispari").collect(number % 2 == 0 ? "Pari" : "Dispari");
        }


        // Verifica che l'indice effettivo corrisponda a quello atteso
        assertEquals(expected, actual);
    }

    // Metodo ausiliario per trovare l'indice del primo numero pari nella lista
    private int findFirstEven(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                return i; // Restituisce l'indice del primo numero pari trovato
            }
        }
        return -1; // Nessun numero pari trovato
    }

    // Metodo ausiliario per trovare l'indice dell'ultimo numero pari nella lista
    private int findLastEven(List<Integer> numbers) {
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                return i; // Restituisce l'indice dell'ultimo numero pari trovato
            }
        }
        return -1; // Nessun numero pari trovato
    }

    // Genera una lista di numeri dispari
    @Provide
    Arbitrary<List<Integer>> odds() {
        return Arbitraries.integers().between(1, 100)
                .filter(n -> n % 2 != 0) // Filtro per numeri dispari
                .list()
                .ofMinSize(1) // Minimo 1 elemento
                .ofMaxSize(100); // Massimo 100 elementi
    }

    // Genera una lista di numeri pari
    @Provide
    Arbitrary<List<Integer>> evens() {
        return Arbitraries.randomValue(this::generateEven).list();
    }

    // Metodo per generare un numero pari casuale
    private Integer generateEven(Random random) {
        int evenNumber;
        evenNumber = random.nextInt(100) * 2; // Genera un numero pari casuale
        Statistics.label("Numeri pari").collect(evenNumber); // Raccoglie statistiche sui numeri pari generati
        return evenNumber;
    }
}
