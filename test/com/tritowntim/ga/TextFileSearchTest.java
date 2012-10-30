package com.tritowntim.ga;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tim Dussinger
 */
public class TextFileSearchTest {
    
    
    @Test()
    public void twoArgs() throws Exception {
        String[] arguments = new String[2];
        arguments[0] = "a";
        arguments[1] = "b";
        TextFileSearch.main(arguments);
    }

    @Test(expected=RuntimeException.class)
    public void zeroArgs() throws Exception {
        TextFileSearch.main(null);
    }

    @Test(expected=RuntimeException.class)
    public void oneArg() throws Exception {
        String[] arguments = new String[1];
        arguments[0] = "a";
        TextFileSearch.main(arguments);
    }

    @Test(expected=RuntimeException.class)
    public void threeArg() throws Exception {
        String[] arguments = new String[3];
        arguments[0] = "a";
        arguments[1] = "b";
        arguments[2] = "c";
        TextFileSearch.main(arguments);
    }
    
    
}
