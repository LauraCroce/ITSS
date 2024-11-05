package homework1;

public class KebabUtil {

    public String convertToKebabCase(String input, boolean toLowerCase, char[] delimiters) {
        // Controllo per input nullo
        if (input == null) {
            return null;
        }

        // Rimuove gli spazi all'inizio e alla fine dell'input
        input = input.trim();

        // Se non ci sono delimitatori specificati, usa lo spazio come default
        if (delimiters == null || delimiters.length == 0) {
            delimiters = new char[] {' '};
        }

        // Escape special regex characters in delimiters
        StringBuilder escapedDelimiters = new StringBuilder();
        for (char delimiter : delimiters) {
            if ("\\^$.|?*+()[]{}".contains(String.valueOf(delimiter))) {
                escapedDelimiters.append("\\");
            }
            escapedDelimiters.append(delimiter);
        }

        // Creazione di una regex per trovare i delimitatori
        String regex = "[" + String.valueOf(escapedDelimiters) + "]";

        // Sostituzione dei delimitatori con "-" e rimozione di trattini consecutivi
        String kebabCase = input.replaceAll(regex, "-")
                .replaceAll("-{2,}", "-")// Rimuove trattini consecutivi
                .replaceAll("\\s+", ""); // Rimuove spazi aal'interno della stringa

        // Controllo per input composto solo da delimitatori
        if (kebabCase.replaceAll("-", "").isEmpty()) {
            return input; // Restituisci la stringa originale se è composta solo da delimitatori
        }

        // Rimuove eventuali trattini iniziali o finali
        kebabCase = kebabCase.replaceAll("(^-|-$)", "");

        // Se specificato, convertire la stringa in minuscola
        if (toLowerCase) {
            kebabCase = kebabCase.toLowerCase();
        }

        return kebabCase;
    }
}


