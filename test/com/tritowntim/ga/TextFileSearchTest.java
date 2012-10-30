package com.tritowntim.ga;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Tim Dussinger
 */
public class TextFileSearchTest {

    private String mainSourceFile;
    private String invalidSourceFile;
    private final String searchForThisText = "search-for-this-text";
        
    @Before
    public void setup() {
        mainSourceFile = System.getProperty("user.dir") + "/src/com/tritowntim/ga/TextFileSearch.java";
        invalidSourceFile = System.getProperty("user.dir") + "/src/com/tritowntim/ga/TextFileSearch-does-not-exist.java";
    }

    @Test()
    public void twoArgs() throws Exception {
        String[] arguments = new String[2];
        arguments[0] = mainSourceFile;
        arguments[1] = "b";
        TextFileSearch.main(arguments);
    }

    @Test(expected = RuntimeException.class)
    public void zeroArgs() throws Exception {
        TextFileSearch.main(null);
    }

    @Test(expected = RuntimeException.class)
    public void oneArg() throws Exception {
        String[] arguments = new String[1];
        arguments[0] = "a";
        TextFileSearch.main(arguments);
    }

    @Test(expected = RuntimeException.class)
    public void threeArg() throws Exception {
        String[] arguments = new String[3];
        arguments[0] = "a";
        arguments[1] = "b";
        arguments[2] = "c";
        TextFileSearch.main(arguments);
    }

    @Test
    public void fileExists() throws Exception {
        assertTrue(TextFileSearch.fileExists(mainSourceFile));
    }

    @Test
    public void fileDoesNotExist() throws Exception {
        assertFalse(TextFileSearch.fileExists(invalidSourceFile));
    }

    @Test
    public void validFile() throws Exception {
        String[] arguments = new String[2];
        arguments[0] = mainSourceFile;
        arguments[1] = searchForThisText;
        TextFileSearch.main(arguments);
    }

    @Test(expected = RuntimeException.class)
    public void invalidFile() throws Exception {
        String[] arguments = new String[2];
        arguments[0] = invalidSourceFile;
        arguments[1] = searchForThisText;
        TextFileSearch.main(arguments);
    }
}
