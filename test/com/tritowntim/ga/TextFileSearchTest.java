    package com.tritowntim.ga;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.logging.Level;
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
    private WordSearcher wordSearcher;
    private String warAndPeaceText;

    @Before
    public void setup() throws IOException {
        Utility.setLogLevel(Level.FINE);
        mainSourceFilePath = System.getProperty("user.dir") + "/src/com/tritowntim/ga/TextFileSearch.java";
        invalidSourceFilePath = System.getProperty("user.dir") + "/src/com/tritowntim/ga/TextFileSearch-does-not-exist.java";
        testSourceFilePath = System.getProperty("user.dir") + "/test/com/tritowntim/ga/TextFileSearchTest.java";
        inputValidator = new InputValidator();
        fileReader = new FileReader();
        wordSearcher = new WordSearcher();
        warAndPeaceText = fileReader.readFile(warAndPeaceFilePath);
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

        String fileContents = fileReader.readFile(testSourceFilePath);
        assertTrue(fileContents.indexOf("public class TextFileSearchTest".toLowerCase()) > -1);
    }

    @Test
    public void readFileWithWrongContents() throws Exception {
        String fileContents = fileReader.readFile(mainSourceFilePath);
        assertEquals(-1, fileContents.indexOf("public class TextFileSearchTest"));
    }

    @Test
    public void countInstances() throws Exception {
        assertEquals(4, wordSearcher.findInstances(warAndPeaceText, "poor girl").size());
    }

    @Test
    public void countInstancesCaseInsensitive() throws Exception {
        assertEquals(4, wordSearcher.findInstances(warAndPeaceText, "PooR GirL").size());
    }

    @Test
    public void countZeroInstances() throws Exception {
        assertEquals(0, wordSearcher.findInstances(warAndPeaceText, "Please send me an email").size());
    }

    @Test
    public void countHundredsOfInstances() throws Exception {
        assertEquals(633, wordSearcher.findInstances(warAndPeaceText, "nicholas").size());
    }
    
    @Test
    public void findInstances() throws Exception {
        assertEquals(4, wordSearcher.findInstances(warAndPeaceText, "poor girl").size());
    }
    
    @Test
    public void countAverage() throws Exception {
        String fileContents = "everybody should help out everybody else to benefit everybody in the area";        
        ArrayList<Integer> instances = wordSearcher.findInstances(fileContents, "everybody") ;
        assertEquals(new BigDecimal(3), wordSearcher.calcAvgWordsBtwnInstances(instances, fileContents, "everybody"));
    }
    
    @Test
    public void countAverageDecimal() throws Exception {
        String fileContents = "everybody should definitely help out everybody else to benefit everybody in the area that everybody owns";        
        ArrayList<Integer> instances = wordSearcher.findInstances(fileContents, "everybody") ;        
        assertEquals(new BigDecimal(11).divide(new BigDecimal(3), MathContext.DECIMAL32), wordSearcher.calcAvgWordsBtwnInstances(instances, fileContents, "everybody"));
    }  
}
