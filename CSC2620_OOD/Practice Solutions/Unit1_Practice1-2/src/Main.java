import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    private final static int NUMS = 1000000;
    private final static int RANGE = 10000;
    private final static int HT_SIZE = 127; // size of the hash table

    // Define the data structures
    private static ArrayList list;
    private static TreeSet set;
    private static HashMap hashtable;
    private static HashMap wordHT;

    /**
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException{
        // Initialize the data structures
        list = new ArrayList<Integer>();
        set = new TreeSet<Integer>();
        // The hash table takes a bit extra initialization
        hashtable = new HashMap<Integer, LinkedList<Integer> >();
        for( int i = 0 ; i < HT_SIZE ; i++ ){
            hashtable.put(i, new LinkedList<Integer>());
        }
        // Set up the word hash map
        wordHT = new HashMap<Character, LinkedList<String> >();
        for(char i = 'a' ; i <= 'z' ; i++ ){
            wordHT.put(i, new LinkedList<String>());
        }

        // Create an object to generate random numbers
        Random r = new Random(37);

        // Part 2) Create 1000000 random numbers
        for( int i = 0 ; i < NUMS ; i++ ){
            Integer number = r.nextInt(RANGE);
            list.add( number );
            set.add( number );
            ((LinkedList)hashtable.get(hash(number))).add(number);
        }

        // Part 3) Open words.txt and build a hash table out of it
        Scanner sc = new Scanner(new File("files/words.txt"));
        while( sc.hasNext() ){
            String next = sc.next();
            // Make sure that the first character is between a and z
            if( hash_s(next) < 'a' || hash_s(next) > 'z' ) continue;
            ((LinkedList)wordHT.get( hash_s(next) )).add(next);
        }

        // For the last part, ask the user to check
        System.out.println("What word should I look for?");
        sc = new Scanner(System.in);
        String lookfor = sc.next();
        // Check to see if the word is there
        if( ((LinkedList)wordHT.get(hash_s(lookfor))) != null && 
                ((LinkedList)wordHT.get(hash_s(lookfor))).indexOf(lookfor) >= 0 ) {
            System.out.println("Yes, found it!");
        }
        else {
            System.out.println("Did not find it!");
        }


    }

    /**
     * @param num
     * @return num modded by 127
     */
    private static Integer hash( int num ){
        return num % HT_SIZE;
    }

    /**
     * @param s
     * @return the first letter of s
     */
    private static char hash_s(String s){
        return s.charAt(0);
    }
}