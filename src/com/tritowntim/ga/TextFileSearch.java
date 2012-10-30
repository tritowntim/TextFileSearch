package com.tritowntim.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
        } else if (hasBlankSearchCriteria(args[1])) {
            throw new RuntimeException("Program failed: no search criteria provided. "
                    + "Please provide search criteria "
                    + " as the second argument to this program.");
        }
    }

    static boolean fileExists(String path) {
        return (new File(path)).exists();
    }

    static boolean hasBlankSearchCriteria(String criteria) {
        return criteria == null || criteria.length() == 0;
    }

    static String readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line.trim());
        }

        reader.close();
        return builder.toString();
    }
}
