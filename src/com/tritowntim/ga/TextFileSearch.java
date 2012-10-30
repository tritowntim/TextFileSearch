package com.tritowntim.ga;

import java.io.File;

/**
 *
 * @author Tim Dussinger
 */
public class TextFileSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        validateInput(args);
    }

    private static void validateInput(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("Program failed: invalid input. "
                    + "Please provide the complete file path "
                    + "and search string "
                    + "as arguments to this program.");
        } else if (!fileExists(args[0])) {
            throw new RuntimeException("Program failed: file not found. "
                    + "There was no file found at the provided path of '"
                    + args[0]
                    + "'. Please try again.");
        }
    }

    static boolean fileExists(String path) {
        return (new File(path)).exists();
    }
}
