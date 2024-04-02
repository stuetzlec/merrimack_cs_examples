package csc2620_thread_time_example;

import java.util.Scanner;

/**
 *
 * @author stuetzlec
 */
public class CSC2620_ThreadTimeExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TimeThread t = new TimeThread();
        t.start();
        
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Press enter");
            in.nextLine();
        }
    }
}
