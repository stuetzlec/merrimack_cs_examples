/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaMVCModels;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author AfzaalAhmad
 */
public class HelloWorldModel {
    public String getData() throws FileNotFoundException, IOException {
        
        if(!(new File("file.txt").isFile())) {
            // Create -- Make sure file exists -- the file before continuing
            Files.createFile(Paths.get("file.txt"));
        }
        
        String data;
        // We will be using a try-with-resource block
        try (BufferedReader reader = new BufferedReader(
                new FileReader("file.txt"))) {
            // Access the data from the file
            // Create a new StringBuilder
            StringBuilder string = new StringBuilder();
            
            // Read line-by-line
            String line = reader.readLine();
            string.append("<html>");
            // While there comes a new line, execute this
            while(line != null) {
                // Add these lines to the String builder
                string.append(line);
                string.append("<br />");
                // Read the next line
                line = reader.readLine();
            }
            string.append("</html>");
            data = string.toString();
        } catch (Exception er) {
            // Since there was an error, you probably want to notify the user
            // For that error. So return the error.
            data = er.getMessage();
        }
        // Return the string read from the file
        return data;
    }
    
    public boolean writeData(String data) throws IOException, FileNotFoundException
    {
        // Save the data to the File
        try (BufferedWriter writer = new BufferedWriter(
                                        new FileWriter("file.txt"))) {
            // Write the data to the File
            writer.write(data);
            // Return indicating the data was written
            return true;
        } catch (Exception er) {
            return false;
        }
    }
}
