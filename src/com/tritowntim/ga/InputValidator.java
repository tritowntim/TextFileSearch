package com.tritowntim.ga;

import java.io.File;

/**
 *
 * @author Tim Dussinger
 */
public class InputValidator {

    void validateInput(String[] args) {
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

    boolean fileExists(String path) {
        return (new File(path)).exists();
    }

    boolean hasBlankSearchCriteria(String criteria) {
        return criteria == null || criteria.length() == 0;
    }
}
