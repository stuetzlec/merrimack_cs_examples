package CSC2620_Practice6_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JCheckBox;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Chris
 */
public class Model {

    private HashMap<Character, ArrayList<String>> words = new HashMap();
    private HashMap<Character, Integer> histogram;

    public Model() throws FileNotFoundException {
        Scanner inF = new Scanner(new File("words.txt"));

        // Initialize the arraylists
        for (Character c = 'a'; c <= 'z'; ++c) {
            words.put(c, new ArrayList());
        }

        String word;
        while (inF.hasNextLine()) {
            word = inF.nextLine();
            if (words.containsKey(word.charAt(0))) {
                words.get(word.charAt(0)).add(word);
            }

        }

        histogram = new HashMap(); // ArrayList with capacity 26

        // Build a histogram out of the words
        for (Character c = 'a'; c <= 'z'; ++c) {
            histogram.put(c, 0);
            // Populate the histogram
            for (String s : words.get(c)) {
                if (histogram.containsKey(s.charAt(0))) {
                    histogram.put(s.charAt(0), histogram.get(s.charAt(0)) + 1);
                }
            }
        }

    }

    public String getWordsWithLetters(JCheckBox[] boxes) {
        String s = "";
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i].isSelected()) {
                for (String word : words.get(boxes[i].getText().charAt(0))) {
                    s += word + "\n";
                }
            }
        }

        return s;
    }
    
    public double[] getHistogramValues(){
        double[] a = new double[26];
        Integer[] c = new Integer[26];
        histogram.values().toArray(c);
        for( int i = 0 ; i < c.length ; i++ ) {
            a[i] = (int) c[i];
        }
        
        for(int i = 0 ; i < 26 ; i++ ) {
            System.out.print(i + ": " + a[i] + "    ");
        }
        System.out.println();
        return a;
    }
    
    /**
     * @param boxes
     * @return
     */
    public CategoryDataset createDataset( JCheckBox[] boxes ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] letters = new String[26];
        for( int i = 0 ; i < 26 ; i++ ) {
            letters[i] = boxes[i].getText();
//            System.out.println("Adding: " + histogram.get(boxes[i].getText().charAt(0)));
            dataset.addValue(histogram.get(boxes[i].getText().charAt(0)), "Number", boxes[i].getText() );
        }         

      return dataset; 
   }
   
}
