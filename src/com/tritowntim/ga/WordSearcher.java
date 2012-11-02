package com.tritowntim.ga;

import java.util.ArrayList;

/**
 *
 * @author Tim Dussinger
 */
public class WordSearcher {
    
    
    /*
     * 
     * build huge honking string out of file
     *  find all instances of search string via indexOf()
     *  for each instance except last instance, count spaces between each instance
     *  
     *  replace within string...
     *  // REGEX to return characters and spaces only?
     *  handle punctuation first?  ( >>> use tests for all this <<< )
     *          replace quotes?
     *              periods?
     *              commas?
     *              hard returns? 
     * 
     * 
     *  for string in between each string...
     *    substr = make string of text in between 
     *      replace hyphens with spaces
     *      replace double spaces with spaces
     *      trim leading, trailing spaces
     *    count spaces in between
     * 
     *  calculate average
     * 
     * 
     * 
     */
    
    public int countInstances(String fileContents, String criteria, boolean enableLogs) {
        ArrayList<Integer> instances = new ArrayList<Integer>();
        int i = 0;
        while (i > -1) {
            i = fileContents.indexOf(criteria.toLowerCase(), i);
            if (i > -1) {
                instances.add(new Integer(i));
                if (enableLogs) {
                    Utility.log("instance " + instances.size() + " = position " + instances.get(instances.size()-1) );
                }
                i = i + criteria.length();                
            }
        }
        Utility.log("found " + instances.size() + " instance" + (instances.size() == 1 ? "" : "s") + " of '" + criteria + "' within file");
        return instances.size();
    }
}
