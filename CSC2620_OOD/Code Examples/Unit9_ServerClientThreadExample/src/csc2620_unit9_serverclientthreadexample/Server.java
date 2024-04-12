package csc2620_unit9_serverclientthreadexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author stuetzlec
 */
public class Server extends Thread {

    private Socket connectionToChatWindows = null;
    private ServerSocket server = null;
    // Keep track of the threads used for the chat windows
    ArrayList<ChatThread> threads = new ArrayList();
    private int port;

    // Constructor with port 
    public Server(int _port) {
        this.port = _port;

        // Attempt to build a server socket to the port
        try {
            server = new ServerSocket(this.port);
        } catch (IOException ex) {

        }
    }

    /**
     * Server's Run method simply spawns a new thread whenever a client connects
     * to the server.
     */
    @Override
    public void run() {
        while (true) {
            try {
                // Every time a connection is made, spawn a new client thread
                connectionToChatWindows = server.accept();
                // Spawn a new thread for the new chat window 
                ChatThread newThread = new ChatThread(this.port);
                newThread.start();
                threads.add(newThread);
            } catch (IOException ex) {
                System.err.println("Connection to client can't be established");
            } catch (NullPointerException ex) {
                ;
            }
        }
    }

    /**
     * This method posts the current chat to all chat windows, necessary because
     * the Server class knows about all the windows but the individual windows
     * do not.
     */
    private void postToChatWindows(String line) {
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).post(line);
        }
    }

    /**
     * A class to run a separate thread per chat window that connects to the
     * Server.
     */
    public class ChatThread extends Thread {

        // Also investigate ObjectInput/OutputStreams because they are very handy
        //   if you're trying to pass more information than just text through
        //   the sockets
        private BufferedReader in = null;
        private PrintWriter out = null;

        public ChatThread(int p) {

            try {
                // Try to establish a connection to server
                //   Should be in run to allow the other chats to open while this
                //   connection is established asynchronously
                in = new BufferedReader(new InputStreamReader(connectionToChatWindows.getInputStream()));
                out = new PrintWriter(connectionToChatWindows.getOutputStream());
            } catch (IOException ex) {
                System.err.println("Error with I/O at server.");
            }
        }

        @Override
        public void run() {
            // Reads message from client continuously, and when one is received
            //   it adds the new text to the "chat window" string that controls
            //   the entire chat window for each chat client.
            while (true) {
                String line = "";
                try {
                    line = in.readLine();
                } catch (IOException ex) {

                }
                postToChatWindows(line);
            }
        }

        /**
         * This method simply posts the newly read line to the output stream.
         *
         * @param line The text to submit to the output stream
         */
        public void post(String line) {
            out.println(line);
            out.flush();
        }

    }
}
