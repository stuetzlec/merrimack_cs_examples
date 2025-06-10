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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InvalidObjectException;

import merrimackutil.json.InvalidJSONException;
import merrimackutil.json.JsonIO;
import merrimackutil.json.types.JSONObject;

/**
 * The main class demonstrating the use of the JSON subsystem. The merrimackutil JSON subsystem is 
 * designed to be easy to use, highly portable, and common to all students. While there are other 
 * JSON libraries they may employ refelection which marries your software to a particular version of 
 * the JVM (complicating group projects). While others encourage poor coding habbits. While others 
 * require the violation of the encapsulation principle of OOP. In order to 
 * unify under one common source of JSON support, we recommend merrimackutil.
 * 
 * This example demonstrates how to work with a JSON configuration file using merrimackutil. The example 
 * highlights the common philosophy of merrimackutil's JSON subsystem. The key points are:
 * 
 * 1. Every JSON object should have a corresponding internal object representation that implements the 
 *    JSONSerializable interface. Object serialization is the process of converting an object from its 
 *    internal representation to a external represntation. This external representation can be stored to 
 *    disk or sent over a network.
 * 
 * 2. The JsonIO static object provides methods for reading JSON objects from file or from a String 
 *    thus producing JSONObject objects. 
 * 
 * 3. The JsonIO static object provides methods for writing any class that implements the JSONSerializable 
 *    interface to disk or to some other PrintWriter object.
 * 
 * Please be sure to read the entire walkthrough to understand the example. The full java API documentation 
 * for merrimaackutil is available at https://cs.merrimack.edu/merrimackutil. The latest jar file for 
 * merrimackutil is available at https://github.com/kisselz/merrimackutil. Your instructor may request 
 * you utilize a specific tagged version. The tag format is YYYY.n where n is a incrementing number.
 *  
 * @author Zach Kissel
 */
public class Driver
{
    /**
     * Prints the usage message and exits the program.
     */
    public static void usage()
    {
        System.out.println("json <config>");
        System.exit(1);
    }

    /**
     * This is the main method. 
     * @param args the command line arguments.
     * @throws InvalidObjectException
     */
    public static void main(String[] args) throws InvalidObjectException
    {
        JSONObject obj = null;
        Configuration config = null;

        if (args.length != 1)
            usage();

        try 
        {
            // Use the JsonIO static object to read an object from a file. 
            // The returned value is a JSONObject that can then be deserialized 
            // into an internal representation. Generally this is done using the 
            // constructor of a class that implements JSONSerializable.
            obj = JsonIO.readObject(new File(args[0]));
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Could not open file: " + e);
            System.exit(1);
        }
        catch (InvalidJSONException e)      // JsonIO detected invalid JSON.
        {
            System.out.println(args[0] + " is not valid JSON.");

            // Exception contains the reason why the JSON is invalid.
            System.out.println(e);  
            System.exit(1);
        }
        
        // Given the JSONObject we will construct a new Configuration object,
        // which implements JSONSerializable. The Configuration class has a 
        // constructor that calls deserialize.
        config = new Configuration(obj);
        System.out.println(config);

        // Write out a nicely formatted version of the object in JSON.
        // If you wanted to write this to a file instead, you could use:
        // JsonIO.writeFormattedObject(config, new File("out.json"));
        // If you didn't want to write out a formatted version you could 
        // write: 
        // JsonIO.writeSerializedObject(config, new File("out.json"));
        System.out.println("As pretty JSON");
        System.out.println(config.toJSONType().getFormattedJSON());

        // If you wanted to send this data over the network you could 
        // bind a PrintWriter out to the OutputStream of the socket and 
        // call: 
        // JsonIO.writeSerializedObject(config, out);

        
    }
}