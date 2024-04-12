package unit8_regex_examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unit8_RegEx_Examples {

    public static void main(String[] args) {
        /*String g = "Gluck, it's a weird word, I know";
        
        System.out.println(g.matches("Gluck"));
        System.out.println(g.matches("^Gluck(.)*"));
        
        String[] splitArray = g.split(",\\s+");
        System.out.println(splitArray[1]);
        System.out.println(splitArray[2]);
        
        g = g.replaceAll(",\\s+", "#");
        System.out.println(g);
         */

        matcherTest();

    }

    public static void matcherTest() {
        /*
        // String to be scanned to find the pattern.
        String line = "This is a weighty search string";
        // The regex to use (what substrings of line does it match?)
        String pattern = "([aeiou]){2}";
        // Create a Pattern object from the regex - must be compiled
        Pattern r = Pattern.compile(pattern);
        // Now create matcher object from the pattern (call .matcher on your pattern)
        Matcher m = r.matcher(line);
        // Find all instances of pattern in line and report them
        while (m.find()) {
            for( int i = 0 ; i < m.groupCount() ; i++ ) {
                System.out.println("Found value: " + m.group(i));
            }
            // The 0 group uses the entire regular expression...see below
            
        }
        */
        // String to be scanned to find the pattern.
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
        
    }

}


/* 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unit8_RegEx_Examples {

   public static void main( String args[] ) {
      // String to be scanned to find the pattern.
      String line = "This order was placed for QT3000! OK?";
      String pattern = "((.*)(\\d{3}))(.*)";

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher m = r.matcher(line);
      while (m.find( )) {
           for( int i = 0 ; i <= m.groupCount() ; i++ ) {
                System.out.println("Found value: " + m.group(i));
            }
      }
   }
}
*/