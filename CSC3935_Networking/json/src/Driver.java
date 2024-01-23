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

import merrimackutil.json.JsonIO;
import merrimackutil.json.types.JSONObject;

/**
 * The main class. 
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
            obj = JsonIO.readObject(new File(args[0]));
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Could not open file: " + e);
            System.exit(1);
        }
        

        config = new Configuration(obj);
        System.out.println(config);

        System.out.println("As pretty JSON");
        System.out.println(config.serialize());

        
    }
}