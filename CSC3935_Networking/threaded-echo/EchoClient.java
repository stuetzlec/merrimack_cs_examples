/*
 *   Copyright (C) 2022 -- 2023  Zachary A. Kissel
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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class EchoClient {
  public static void main (String[] args)
  {
    Scanner scan = new Scanner(System.in);
    Socket sock;
    Scanner recv = null;
    PrintWriter send = null;

    try
    {
      // Set up a connection to the echo server running on the same machine.
      sock = new Socket("127.0.0.1", 5000);


      // Set up the streams for the socket.
      recv = new Scanner(sock.getInputStream());
      send = new PrintWriter(sock.getOutputStream(), true);
    }
    catch(UnknownHostException ex)
    {
      System.out.println("Host is unknown.");
      return;
    }
    catch(IOException ioe)
    {
      ioe.printStackTrace();
    }

    // Prompt the user for a string to send.
    System.out.print("Write a short message: ");
    String msg = scan.nextLine();

    // Send the message to the server.
    send.println(msg);

    // Echo the response to the screen.
    String recvMsg = recv.nextLine();
    System.out.println("Server Said: " + recvMsg);
  }
}
