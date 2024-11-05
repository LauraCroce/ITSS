package homework1;

public class Main {
    public static void main(String[] args){

        String input,output;
        boolean toLowerCase;
        char[] delimiters;

        //Creazione dell'oggetto KebabUtil
        KebabUtil kebabUtils = new KebabUtil();

        input = "kebab util";
        toLowerCase = false;
        delimiters = null;

        output = kebabUtils.convertToKebabCase(input, toLowerCase, delimiters);

        System.out.println("test: " + input);
        System.out.println("output : " + output);
    }
}
