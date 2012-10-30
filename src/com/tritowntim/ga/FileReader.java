package com.tritowntim.ga;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 *
 * @author Tim Dussinger
 */
public class FileReader {

    // The program returns both a count of the times the string appears in the file
    // and the average number of words between each instance of the search string.

    /*
     * 
     * for each line
     *   tokenize line by space to seperate into words
     *   for each word
     *     load word into arraylist
     *  >>> but search string cannot be limited by line
     * 
     * build huge honking string out of file
     *  find text via indexOf()
     *  continue finding via indexOf()
     *  count spaces between each instance
     *  calculate average
     * 
     * 
     * 
     */
    String readFile(String path) throws IOException {
        Utility.log(path + " begin");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line.trim());
        }

        reader.close();
        String fileContents = builder.toString();
        Utility.log(path + " end");
        return fileContents;
    }
}
