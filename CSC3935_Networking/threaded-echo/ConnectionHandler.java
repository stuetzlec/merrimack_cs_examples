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
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

/**
 * Handles a single connection.
 * @author Zach Kissel
 */
public class ConnectionHandler implements Runnable
{
    private Socket sock;

    /**
     * Creates a new conneciton handler 
     * @param sock the socket associated with the connection.
     */
    public ConnectionHandler(Socket sock)
    {
        this.sock = sock;
    }

    /**
     * How to handle the connection
     */
    public void run() 
    {
        try
        {
            // Setup the streams for use.
            Scanner recv = new Scanner(sock.getInputStream());
            PrintWriter send = new PrintWriter(sock.getOutputStream(), true);

            // Get the line from the client.
            String line = recv.nextLine();

            // Echo the line back.
            send.println(line);

            // Close the connection.
            sock.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }    
    }
}