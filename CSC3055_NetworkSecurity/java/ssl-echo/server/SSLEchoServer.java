/*
 *   Copyright (C) 2018 -- 2023  Zachary A. Kissel
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * A simple SSL based echo server.
 *
 * @author Zach Kissel
 */
public class SSLEchoServer {

 static final int PORT_NUM = 5000;

 public static void main(String[] args)
 {
   SSLServerSocketFactory sslFact;
   SSLServerSocket server;

   // Set the keystore and keystore password.
   System.setProperty("javax.net.ssl.keyStore", "keystore.jks");
   System.setProperty("javax.net.ssl.keyStorePassword", "test12345");

   try
   {
     // Get a copy of the deafult factory. This is what ever the
     // configured provider gives.
     sslFact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

     // Set up the server socket using the specified port number.
     server = (SSLServerSocket) sslFact.createServerSocket(PORT_NUM);

     // Set the protocol to 1.3
     server.setEnabledProtocols(new String[] {"TLSv1.3"});

     // Loop forever handing connections.
     while (true)
     {
       // Wait for a connection.
       Socket sock = server.accept();

       System.out.println("Connection received.");

       // Setup the streams for use.
       Scanner recv = new Scanner(sock.getInputStream());
       PrintWriter send = new PrintWriter(sock.getOutputStream(), true);

       // Get the line from the client.
       String line = recv.nextLine();
       System.out.println("Client said: " + line);

       // Echo the line back.
       send.println(line.toUpperCase());

       // Close the connection.
       sock.close();
     }
   }
   catch(IOException ioe)
   {
     ioe.printStackTrace();
   }
 }
}
