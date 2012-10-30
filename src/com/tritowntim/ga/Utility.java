package com.tritowntim.ga;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tim Dussinger
 */
public class Utility {   
    
    static SimpleDateFormat millisecs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    static public void log(String text) {
        System.out.println( millisecs.format(new Date())  + " " + text);
    }
}
