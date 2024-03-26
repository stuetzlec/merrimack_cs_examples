package csc2620_unit7_example;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author stuetzlec
 */
public class CSC2620_Unit7_Example {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // Print some numbers
        for (int i = 0; i < 3; i++) {
            Printer printer = new Printer();
            // Don't call 'run' because if you do, it will execute sequentially
            //  like a normal method call
            printer.start();
        }
        
        // Just here to get the user to hit "enter" before continuing
        Scanner in = new Scanner(System.in);
        System.out.println("Press ENTER to continue:");
        in.nextLine();
        
        // Sum some numbers
        Integer[] numbers = new Integer[1000000];
        Random rng = new Random(37);
        Integer sumInMain = 0;
        for( int i = 0 ; i < numbers.length ; i++ ){
            numbers[i] = rng.nextInt(30);
            sumInMain += numbers[i];
        }
        Summer[] s = new Summer[10];
        Thread[] threads = new Thread[10];
        for( int t = 0 ; t < 10 ; t++ ) {
            s[t] = new Summer(numbers, t * 100000, (t+1) * 100000);
            threads[t] = new Thread(s[t]);
            threads[t].start();
        }
        // Now, wait until they're all finished
        for( int t = 0 ; t < 10 ; t++ ){
            threads[t].join();
        }
        System.out.println("The sum in main is " + sumInMain);
        printSum(s);
        
    }
    
    private static void printSum(Summer[] s) {
        int sum = 0;
        for( int i = 0 ; i < s.length ; i++ ) {
            sum += s[i].getSum();
        }
        System.out.println("The sum is " + sum);
    }

}
