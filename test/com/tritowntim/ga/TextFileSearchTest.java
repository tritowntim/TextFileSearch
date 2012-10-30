package com.tritowntim.ga;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Tim Dussinger
 */
public class TextFileSearchTest {

    private String mainSourceFilePath;
    private String invalidSourceFilePath;
    private String testSourceFilePath;
    private final String searchForThisText = "search-for-this-text";
    private InputValidator iv;

    @Before
    public void setup() {
        mainSourceFilePath = System.getProperty("user.dir") + "/src/com/tritowntim/ga/TextFileSearch.java";
        invalidSourceFilePath = System.getProperty("user.dir") + "/src/com/tritowntim/ga/TextFileSearch-does-not-exist.java";
        testSourceFilePath = System.getProperty("user.dir") + "/test/com/tritowntim/ga/TextFileSearchTest.java";
        iv = new InputValidator();
    }

    @Test()
    public void twoArgs() throws Exception {
        String[] arguments = new String[2];
        arguments[0] = mainSourceFilePath;
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
        assertTrue(iv.fileExists(mainSourceFilePath));
    }

    @Test
    public void fileDoesNotExist() throws Exception {
        assertFalse(iv.fileExists(invalidSourceFilePath));
    }

    @Test
    public void validFile() throws Exception {
        String[] arguments = new String[2];
        arguments[0] = mainSourceFilePath;
        arguments[1] = searchForThisText;
        TextFileSearch.main(arguments);
    }

    @Test(expected = RuntimeException.class)
    public void invalidFile() throws Exception {
        String[] arguments = new String[2];
        arguments[0] = invalidSourceFilePath;
        arguments[1] = searchForThisText;
        TextFileSearch.main(arguments);
    }

    @Test
    public void nullString() throws Exception {
        assertTrue(iv.hasBlankSearchCriteria(null));
    }

    @Test
    public void emptyString() throws Exception {
        assertTrue(iv.hasBlankSearchCriteria(""));
    }

    @Test(expected = RuntimeException.class)
    public void nullSearchString() throws Exception {
        String[] arguments = new String[2];
        arguments[0] = mainSourceFilePath;
        arguments[1] = null;
        TextFileSearch.main(arguments);
    }

    @Test(expected = RuntimeException.class)
    public void emptySearchString() throws Exception {
        String[] arguments = new String[2];
        arguments[0] = mainSourceFilePath;
        arguments[1] = "";
        TextFileSearch.main(arguments);
    }

    @Test
    public void readFile() throws Exception {
        String fileContents = TextFileSearch.readFile(testSourceFilePath);
        assertTrue(fileContents.indexOf("public class TextFileSearchTest") > -1);
    }

    @Test
    public void readFileWithWrongContents() throws Exception {
        String fileContents = TextFileSearch.readFile(mainSourceFilePath);
        assertEquals(-1, fileContents.indexOf("public class TextFileSearchTest"));
    }
}
