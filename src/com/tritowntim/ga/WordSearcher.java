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
     *      handle punctuation first?
     *          replace quotes?
     *              periods?
     *              commas?
     *              hard returns?
     *  calculate average
     * 
     * 
     * 
     */
    
    public int countInstances(String fileContents, String criteria) {
        ArrayList<Integer> instances = new ArrayList<Integer>();
        int i = 0;
        while (i > -1) {
            i = fileContents.indexOf(criteria.toLowerCase(), i);
            if (i > -1) {
                instances.add(new Integer(i));
                Utility.log("instance " + instances.size() + " = position " + instances.get(instances.size()-1) );
                i = i + criteria.length();                
            }
        } 
        return instances.size();
    }
    
}
