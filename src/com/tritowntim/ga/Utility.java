package com.tritowntim.ga;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * Class for generic utility functionality.
 *
 * @author Tim Dussinger
 */
public class Utility {
    
    static private Level logLevel = Level.OFF;
    
    static SimpleDateFormat millisecs = new SimpleDateFormat("H:mm:ss.SSS");
    
    /**
     * Log to file if lots meet logging level.
     * 
     * @param level Importance/specificity of log
     * @param text Actual text to be logged
     */
    static public void log(Level level, String text) {
        if (level.intValue() >= logLevel.intValue() ) {
            System.out.println( millisecs.format(new Date())  + " " + text);            
        }
    }

    /**
     * Get the overall logging level for the project.
     * 
     * @return
     */
    public static Level getLogLevel() {
        return logLevel;
    }

    /**
     * Set the overall logging level for the project: Level.INFO or Level.FINE
     * 
     * @param logLevel
     */
    public static void setLogLevel(Level logLevel) {
        Utility.logLevel = logLevel;
    } 
}
