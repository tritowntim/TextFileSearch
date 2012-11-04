package com.tritowntim.ga;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 *
 * @author Tim Dussinger
 */
public class FileReader {

    // The program returns both a count of the times the string appears in the file
    // and the average number of words between each instance of the search string.
    String readFile(String path) throws IOException {

        Utility.log(Level.INFO, "reading file '" + path + "': begin");

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().length() > 0) {
                builder.append(line.replace("--", " ").trim()).append(" ");
            }
        }

        reader.close();
        Utility.log(Level.INFO, "reading file '" + path + "': end");

        String fileContents = builder.toString().toLowerCase();

        // todo: validate file actually has contents
        return fileContents;
    }
}
