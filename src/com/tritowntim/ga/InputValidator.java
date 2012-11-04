package com.tritowntim.ga;

import java.io.File;

/**
 * Class to input project command line input.
 * 
 * @author Tim Dussinger
 */
public class InputValidator {

    /**
     * Validate that command line input arguments are accurate.
     * 
     * @param args Original input from command line: file name and search string, respectively.
     */
    public void validateInput(String[] args) {
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

    // whether or not file exists at specified path
    boolean fileExists(String path) {
        return (new File(path)).exists();
    }

    // whether or not given String is blank
    boolean hasBlankSearchCriteria(String value) {
        return value == null || value.length() == 0;
    }
}
