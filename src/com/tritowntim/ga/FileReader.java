package com.tritowntim.ga;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * Class for reading file contents into a String.
 * 
 * @author Tim Dussinger
 */
public class FileReader {

    /**
     * Read file contents into a String.
     * 
     * @param path Complete path/address of file
     * @return File contents, without any line breaks, and with dashes replaced by strings.
     * @throws IOException
     */
    public String readFile(String path) throws IOException {

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
        
        return fileContents;
    }
}
