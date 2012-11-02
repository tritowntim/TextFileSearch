package com.tritowntim.ga;

import java.io.IOException;

/**
 *
 * @author Tim Dussinger
 */
public class TextFileSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        InputValidator inputValidator = new InputValidator();
        inputValidator.validateInput(args);
        
        FileReader fileReader = new FileReader();
        fileReader.readFile(args[0], false);
    }
}
