package homework2;

import java.util.List;

public class EvenIndex {

    //Costruttore di default della classe EvenIndex
    public EvenIndex() {

    }

    /**
     * Trova l'indice del primo o ultimo numero pari nella lista.
     * @param numbers la lista di numeri interi
     * @param findFirst true se vogliamo trovare il primo numero pari nella lista,
     *                  false se vogliamo trovare l'ultimo numero pari nella lista
     * @return l'indice del primo o ultimo numero pari nella lista,
     *         -1 se non viene trovato alcun numero pari.
     * @throws IllegalArgumentException se la lista è null o vuota, o se la lista non contiene numeri positivi
     */
    public int findEvenIndex(List<Integer> numbers, boolean findFirst) {

        //Verifica se la lista è null o vuota, se lo è lancia un'eccezione
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input non valido"); // Input non valido
        }

        // Verifica se la lista contiene solo numeri non positivi (<=0) o zero
        if (numbers.stream().allMatch(num -> num <= 0)) {
            throw new IllegalArgumentException("La lista non contiene numeri positivi");
        }

        //Inizializza la variabile index con -1, valore di default se non si trova alcun numero pari
        int index = -1;

        //Se findFirst è true, cerca il primo numero pari nella lista
        if (findFirst) {
            //Itera la lista dal primo elemento all'ultimo
            for (int i = 0; i < numbers.size(); i++) {
                int number = numbers.get(i);
                //Controlla se il numero corrente è pari
                if (number % 2 == 0) {
                    return i; //Ritorna l'indice appena trova il primo numero pari
                }
            }
        } else {
            // Se findFirst è false, cerca l'ultimo numero pari nella lista
            // Itera la lista dall'ultimo elemento al primo
            for (int i = numbers.size() - 1; i >= 0; i--) {
                int number = numbers.get(i);
                //Controlla se il numero corrente è pari
                if (number % 2 == 0) {
                    return i; //Ritorna l'indice dell'ultimo numero pari trovato
                }
            }
        }
        //Ritorna -1 se non viene trovato alcun numero pari
        return index;
    }
}
