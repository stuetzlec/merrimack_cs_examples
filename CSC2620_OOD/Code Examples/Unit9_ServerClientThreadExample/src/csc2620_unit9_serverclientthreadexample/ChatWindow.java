package csc2620_unit9_serverclientthreadexample;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author stuetzlec
 */
public class ChatWindow extends JFrame implements Runnable {

    private JTextField chatField;
    private JTextArea textWindow;

    // For the chat initialize the socket to connect to the server
    private Socket connectionToServer = null;
    // Also investigate ObjectInput/OutputStreams because they are very handy
    //   if you're trying to pass more information than just text through
    //   the sockets
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String address;
    private int port;
    private String username;

    public ChatWindow(String a, int p, String un) {
        super("Chat Example: " + un);
        this.setLayout(new BorderLayout());

        chatField = new JTextField();
        this.add(chatField, BorderLayout.PAGE_END);
        chatField.addKeyListener(new KeyHandler());
        textWindow = new JTextArea();
        textWindow.setEditable(false);
        this.add(new JScrollPane(textWindow), BorderLayout.CENTER);

        this.address = a;
        this.port = p;
        this.username = un;
    }

    @Override
    public void run() {
        // Try to establish a connection to server
        //   Should be in run to allow the other chats to open while this
        //   connection is established asynchronously
        try {
            connectionToServer = new Socket(address, port);
            System.out.println(this.username + " connected");
            // takes in from terminal 
            in = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));
            out = new PrintWriter(connectionToServer.getOutputStream());
        } catch (UnknownHostException u) {
            System.err.println("Can't find the host:\n  "
                    + address + "\n  " + port);
        } catch (IOException i) {
            System.err.println("No input");
        }

        // Keep looking at the input stream and wait for a new chat to come through
        while (true) {
            try {
                String chat = in.readLine();
                textWindow.setText( textWindow.getText() + chat + "\n" );
            } catch (IOException ex) {
                System.err.println("Could not retrieve additional chat.");
            }
        }
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Send the text in the field to the server
                // string to read message from in 
                out.println(username + ": " + chatField.getText());
                // NECESSARY:
                out.flush();
                // Clear the text in the text field
                chatField.setText("");
            }
        }
    }

}
