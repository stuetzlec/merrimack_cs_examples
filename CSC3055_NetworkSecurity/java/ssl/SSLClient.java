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
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class SSLClient {
  public static void main (String[] args) throws IOException
  {
    Scanner recv;
    PrintWriter send;

    // Create an SSL socket.
    SSLSocketFactory fac = (SSLSocketFactory)SSLSocketFactory.getDefault();
    SSLSocket sock = (SSLSocket)fac.createSocket("www.google.com", 443);

    // Requires JDK 11 or higher.
    sock.setEnabledProtocols(new String[]{"TLSv1.3"});

    // Start up the SSL handshake.
    sock.startHandshake();

    // Bind the streams.
    send = new PrintWriter(sock.getOutputStream(), true);
    recv = new Scanner(sock.getInputStream());

    // Send a HTTP get message to google.com
    // The \r means carriage return. HTTP requires lines to end with
    // a carriage return line feed (i.e., \r\n).
    String request = "GET / HTTP/1.1\r\nHost: www.google.com\r\nConnection: close\r\n";
    System.out.println("Request\n" + request + "\r");
    send.println(request + "\r");

    // Read the response lines.
    while (recv.hasNextLine())
    {
      System.out.println(recv.nextLine());
    }

    // Close the connection.
    sock.close();
  }
}
