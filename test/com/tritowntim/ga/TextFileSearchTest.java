package com.tritowntim.ga;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private InputValidator inputValidator;
    private FileReader fileReader;
    private String warAndPeaceFilePath = "/Users/tritowntim/war-and-peace.txt";

    @Before
    public void setup() {
        mainSourceFilePath = System.getProperty("user.dir") + "/src/com/tritowntim/ga/TextFileSearch.java";
        invalidSourceFilePath = System.getProperty("user.dir") + "/src/com/tritowntim/ga/TextFileSearch-does-not-exist.java";
        testSourceFilePath = System.getProperty("user.dir") + "/test/com/tritowntim/ga/TextFileSearchTest.java";
        inputValidator = new InputValidator();
        fileReader = new FileReader();
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
        assertTrue(inputValidator.fileExists(mainSourceFilePath));
    }

    @Test
    public void fileDoesNotExist() throws Exception {
        assertFalse(inputValidator.fileExists(invalidSourceFilePath));
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
        assertTrue(inputValidator.hasBlankSearchCriteria(null));
    }

    @Test
    public void emptyString() throws Exception {
        assertTrue(inputValidator.hasBlankSearchCriteria(""));
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

        String fileContents = fileReader.readFile(testSourceFilePath,false);
        assertTrue(fileContents.indexOf("public class TextFileSearchTest".toLowerCase()) > -1);
    }

    @Test
    public void readFileWithWrongContents() throws Exception {
        String fileContents = fileReader.readFile(mainSourceFilePath,false);
        assertEquals(-1, fileContents.indexOf("public class TextFileSearchTest"));
    }

    @Test
    public void readWarAndPeace() throws Exception {
        String fileContents = fileReader.readFile(warAndPeaceFilePath,false);
    }

    @Test
    public void countInstances() throws Exception {
        String fileContents = fileReader.readFile(warAndPeaceFilePath,false);
        WordSearcher wordSearcher = new WordSearcher();
        assertEquals(4, wordSearcher.countInstances(fileContents, "poor girl", true));
    }

    @Test
    public void countInstancesCaseInsensitive() throws Exception {
        String fileContents = fileReader.readFile(warAndPeaceFilePath,false);
        WordSearcher wordSearcher = new WordSearcher();
        assertEquals(4, wordSearcher.countInstances(fileContents, "PooR GirL", false));
    }

    @Test
    public void countZeroInstances() throws Exception {
        String fileContents = fileReader.readFile(warAndPeaceFilePath,false);
        WordSearcher wordSearcher = new WordSearcher();
        assertEquals(0, wordSearcher.countInstances(fileContents, "Please send me an email", false));
    }

    @Test
    public void countHundredsOfInstances() throws Exception {
        String fileContents = fileReader.readFile(warAndPeaceFilePath,false);
        WordSearcher wordSearcher = new WordSearcher();
        assertEquals(633, wordSearcher.countInstances(fileContents, "nicholas", false));
    }
}
