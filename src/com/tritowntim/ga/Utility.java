package com.tritowntim.ga;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author Tim Dussinger
 */
public class Utility {
    
    static private Level logLevel = Level.OFF;
    
    static SimpleDateFormat millisecs = new SimpleDateFormat("H:mm:ss.SSS");
    
    static public void log(Level level, String text) {
        if (level.intValue() >= logLevel.intValue() ) {
            System.out.println( millisecs.format(new Date())  + " " + text);            
        }
    }

    public static Level getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(Level logLevel) {
        Utility.logLevel = logLevel;
    } 
}
