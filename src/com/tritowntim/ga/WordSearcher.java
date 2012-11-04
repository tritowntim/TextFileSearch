package com.tritowntim.ga;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Class to search String of file contents for given String search criteria.
 * 
 * @author Tim Dussinger
 */
public class WordSearcher {

    /**
     * Get the list of positions of instances of the search criteria in the file contents. 
     * 
     * @param fileContents Text to be searched.
     * @param criteria Criteria for searches.
     * @return List of starting positions for each instance of the search criteria in the text file (output from findInstances).
     */
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

    /**
     * Calculate the average words between each instance of the search criteria in the text file.
     * 
     * @param instances List of starting positions for each instance of the search criteria in the text file (output from findInstances).
     * @param fileContents Text to be searched.
     * @param criteria Criteria for searches.
     * @return Average number of words between each instance of the 
     */
    public BigDecimal calcAvgWordsBtwnInstances(ArrayList<Integer> instances, String fileContents, String criteria) {
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
