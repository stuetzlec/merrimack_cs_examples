package csc2620_unit9_serverclientthreadexample;

import javax.swing.JFrame;

/**
 *
 * @author stuetzlec
 */
public class CSC2620_Unit9_ServerClientThreadExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Start the server and each of the chat windows
        Server s = new Server(5000);
        s.start();
        
        ChatWindow c1 = new ChatWindow("127.0.0.1", 5000, "Alice");
        c1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c1.setSize(600,400);
        c1.setVisible(true);
        Thread t1 = new Thread(c1);
        t1.start();
        
        ChatWindow c2 = new ChatWindow("127.0.0.1", 5000, "Bob");
        c2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c2.setSize(600,400);
        c2.setVisible(true);
        Thread t2 = new Thread(c2);
        t2.start();
        
        ChatWindow c3 = new ChatWindow("127.0.0.1", 5000, "Charlie");
        c3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c3.setSize(600,400);
        c3.setVisible(true);
        Thread t3 = new Thread(c3);
        t3.start();
    }
    
}
