/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit9_objectstreamswithsockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author chatGPT
 */

public class LoopingSocketServer {
    public static void main(String[] args) throws Exception {
        int portNumber = 5000;
        ServerSocket serverSocket = new ServerSocket(portNumber);

        while (true) {
            // Causes exception because the server is not writing with writeObject()...
            //   How can we fix?
            Socket clientSocket = serverSocket.accept();
            new ClientHandler(clientSocket).start();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

            String str = (String) ois.readObject();
            System.out.println(str + " is the message.");
            if (str.equals("initialize")) {
                oos.writeObject("Hello from the server");
            }

            while ((str = (String) ois.readObject()) != null) {
                System.out.println("Received message: " + str);
                oos.writeObject("Message received: " + str);

                if (str.equals("bye")) {
                    oos.writeObject("bye bye");
                    break;
                }
            }

            ois.close();
            oos.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

