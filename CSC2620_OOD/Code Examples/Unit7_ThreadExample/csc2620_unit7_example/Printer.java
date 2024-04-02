package csc2620_unit7_example;

//package csc2620_unit10_example;

/**
 * You can either implement Runnable or inherit directly from Thread, which
 *   is ALSO Runnable and will force this class to implement the Run method.
 * @author stuetzlec
 */
public class Printer extends Thread {

    private int ID;
    private static int numThreads = 0;

    public Printer() {
        ID = numThreads++;
    }

    /*
    *  The run method - notice that it prints the ID thousands of times...
    *    but is the output always together? No, because of the threading.
    */
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            System.out.println("I'm in run: " + ID);
        }
    }

}
