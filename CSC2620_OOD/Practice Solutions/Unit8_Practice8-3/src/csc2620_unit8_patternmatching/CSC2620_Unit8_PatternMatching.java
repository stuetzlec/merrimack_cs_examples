
package csc2620_unit8_patternmatching;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class for demonstrating String pattern matching in Java
 * @author stuetzlec
 */
public class CSC2620_Unit8_PatternMatching {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // The matches method, Section 8.3
       // String searchString = "This is the string that I am going to search over.";
       // System.out.println(searchString.matches(searchString));
       // System.out.println(searchString.matches("This(.)*")); // True
       // System.out.println(searchString.matches("string that(.)*")); // False
        
        //String phoneNumber = "(555) 555-5555";
        //String notPhoneNumber = "555_555-5555";
        //String pattern = "\\(\\d{3}\\) \\d{3}-\\d{4}"; // A regular expression for a phone number
        //System.out.println(phoneNumber.matches(pattern));
        //System.out.println(notPhoneNumber.matches(pattern));
        
        // Using the "matcher" approach:
       // matcherTest();
        
        // Practice 8.3:
        System.out.println( indexOf("This is a heavy search string", "[aeiou]{2}") );
    }
    
    
    
    /**
     * A method to use regular expressions to find the location of a regex
     *   in a search string. The answer to Practice 9.2
     * @param searchString The String to search in for regex
     * @param regex The regular expression to find the first instance of in searchString
     */
    private static int indexOf(String searchString, String regex) {
        
        return searchString.split(regex)[0].length();
    }
}
   
