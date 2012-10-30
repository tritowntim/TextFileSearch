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

    String readFile(String path) throws IOException {
        Utility.log(path + " begin");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line.trim());
        }

        reader.close();
        String fileContents = builder.toString().toLowerCase();
        Utility.log(path + " end");
        
        // todo: validate file actually has contents
        return fileContents;
    }
}
