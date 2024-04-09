
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
        
        // The matches method, Section 9.1.3
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
        
        // Practice 9.2:
        System.out.println( indexOf("This is a heavy search string", "[aeiou]{2}") );
    }
    
    /**
     * A method demonstrating the use of matcher, described in section 6.2
     */
    public static void matcherTest( ) {
      // String to be scanned to find the pattern.
      String line = "This is a weighty search string";
      // The regex to use (what substrings of line does it match?)
      String pattern = "[aeiou]{2}";  

      // Create a Pattern object from the regex - must be compiled
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object from the pattern (call .matcher on your pattern)
      Matcher m = r.matcher(line);
      
      // Find all instances of pattern in line and report them
      while (m.find( )) {
          // The 0 group uses the entire regular expression...see below
          System.out.println("Found value: " + m.group(0) );
      }
      
      // Note: You can also create a regular expression with parenthetical
      //   groups (group(1), group(2), etc.), and find each one individually
      // (see https://www.tutorialspoint.com/java/java_regular_expressions.htm
   }
    
    
    
    /**
     * A method to use regular expressions to find the location of a regex
     *   in a search string. The answer to Practice 9.2
     * @param searchString The String to search in for regex
     * @param regex The regular expression to find the first instance of in searchString
     */
    private static int indexOf(String searchString, String regex) {
        // Generate a random string that doesn't exist in searchString
        Random rng = new Random();
        String randString = "0";
        while( searchString.contains(randString) ) {
            randString += Integer.toString(rng.nextInt());
        }
        
        // Now we know that randString exists nowhere in searchString;
        searchString = searchString.replaceFirst(regex, randString);
        
        // Is it a problem that we've re-assigned searchString?
        return searchString.indexOf(randString);
        
        // Alternative Method (thanks to Alexia F.)
        //return searchString.split(regex)[0].length();
        // Or use matcher to get the match and use indexOf to find it.
    }
}
   
