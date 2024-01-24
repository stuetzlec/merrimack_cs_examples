package polymorph_example;

import java.util.Arrays;

/**
 *
 * @author stuetzlec
 */
public class CSC2620_Unit4_15_Practice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Automobile[] a = new Automobile[4];
        a[0] = new Car(50, 10);
        a[1] = new Truck(95);
        a[2] = new Car(42, 15);
        a[3] = new Truck(92);
        Arrays.sort( a );
        
        for( Automobile aut : a ) {
            System.out.println(aut);
        }
    }
}