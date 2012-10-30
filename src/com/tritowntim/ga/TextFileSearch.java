package com.tritowntim.ga;

/**
 *
 * @author Tim Dussinger
 */
public class TextFileSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        validateInput(args);
    }

    private static void validateInput(String[] args) {
        System.out.println(args.length);
        if (args.length != 2) {
            throw new RuntimeException("Program failed: invalid input. "
                    + "Please provide the complete file path "
                    + "and search string "
                    + "as arguments to this program.");
        }
    }
}
