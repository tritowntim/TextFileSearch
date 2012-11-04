package com.tritowntim.ga;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.logging.Level;

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
    public int countInstances(String fileContents, String criteria) {
        ArrayList<Integer> instances = findInstances(fileContents, criteria);
        Utility.log(Level.INFO, "found " + instances.size() + " instance" + (instances.size() == 1 ? "" : "s") + " of '" + criteria + "' within file");
        return instances.size();
    }

    public ArrayList<Integer> findInstances(String fileContents, String criteria) {
        ArrayList<Integer> instances = new ArrayList<Integer>();
        int i = 0;
        while (i > -1) {
            i = fileContents.indexOf(criteria.toLowerCase(), i);
            if (i > -1) {
                instances.add(new Integer(i));
                Utility.log(Level.FINE, "instance " + instances.size() + " = position " + instances.get(instances.size() - 1));
                i = i + criteria.length();
            }
        }
        return instances;
    }

    public BigDecimal countAvgWordsBtwnInstances(ArrayList<Integer> instances, String fileContents, String criteria) {
        int total = 0;
        int i = 0;
        int prev = 0;
        for (Integer inst : instances) {
            if (i > 0) {
                int words = fileContents.substring(prev + criteria.length(), inst.intValue()).trim().split(" ").length;
                Utility.log(Level.FINE, "instance " + i + " words in between = " + words);
                total += words;
            }
            prev = inst.intValue();
            i++;
        }
        Utility.log(Level.INFO, "total = " + total);
        Utility.log(Level.INFO, "instance gaps (#instances-1) = " + (instances.size() - 1));
        BigDecimal avg = new BigDecimal(total).divide(new BigDecimal(instances.size() - 1), MathContext.DECIMAL32);
        Utility.log(Level.INFO, "average = " + avg.toString());
        return avg;
    }
}
