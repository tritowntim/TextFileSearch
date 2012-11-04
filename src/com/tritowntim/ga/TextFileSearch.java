package com.tritowntim.ga;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Main class for project.
 *
 * @author Tim Dussinger
 */
public class TextFileSearch {

    /**
     * Main method for finding search strings in file and calculating average
     * words between instances.
     *
     * @param args the command line arguments
     * @throws IOException Issue while reading file
     */
    public static void main(String[] args) throws IOException {
        InputValidator inputValidator = new InputValidator();
        inputValidator.validateInput(args);

        FileReader fileReader = new FileReader();
        String fileContents = fileReader.readFile(args[0]);

        WordSearcher wordSearcher = new WordSearcher();
        ArrayList<Integer> instances = wordSearcher.findInstances(fileContents, args[1]);
        int instanceCount = instances.size();

        BigDecimal avgWordsBtwn = wordSearcher.calcAvgWordsBtwnInstances(instances, fileContents, args[1]);

        System.out.println("Found " + instanceCount + " occurrence" + (instanceCount == 1 ? "" : "s") + " of '" + args[1] + "' within " + args[0]);
        System.out.println("Average of " + avgWordsBtwn + " word" + (avgWordsBtwn.equals(BigDecimal.ONE) ? "" : "s") + " between each occurrence of '" + args[1] + "' within " + args[0]);

    }
}
