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
import java.io.InvalidObjectException;

import merrimackutil.json.JSONSerializable;
import merrimackutil.json.types.JSONObject;
import merrimackutil.json.types.JSONType;

/**
 * This class is the internal representation of a JSON configuration file. The key thing to notice about 
 * this file is that it implements the JSONSerializable interface which allows this class to be used with 
 * the JsonIO static class which simplifies working with JSON files and objects. Moreover you will notice
 * that there is a constructor that calls deserialize(). While such a constructor is not required, most 
 * find it helpful as it is a common pattern to construct a new instance of an object using a JSONObject.
 * 
 * Please be sure to read the entire walkthrough to understand the example. The full java API documentation 
 * for merrimaackutil is available at https://cs.merrimack.edu/merrimackutil. The latest jar file for 
 * merrimackutil is available at https://github.com/kisselz/merrimackutil. Your instructor may request 
 * you utilize a specific tagged version. The tag format is YYYY.n where n is a incrementing number.
 *  
 * @author Zach Kissel
 */
public class Configuration implements JSONSerializable 
{
    private int portNumber;     // The port number.
    private String logFile;     // The log file name. 
    private String dataDir;     // The data directory.
    
    /**
     * Construct a configuration from JSON data.
     * @param type the JSON data.
     */
    public Configuration(JSONObject cdata) throws InvalidObjectException
    {
        deserialize(cdata);
    }

    /**
     * Convert JSONType to this object.
     * @param obj the object to desrialize.
     */
    public void deserialize(JSONType obj) throws InvalidObjectException 
    {
        JSONObject config = null;
        String [] keys = {"port", "log", "datadir"};

        if (!obj.isObject())
            throw new InvalidObjectException("JSONObject expected.");
        
        config = (JSONObject) obj;

        // Check if all of the required keys are present. If they are 
        // not, InvalidObjectException is thrown. The exception contains the 
        // list of missing keys.
        config.checkValidity(keys);

        // Get the port.
        portNumber = config.getInt("port");
        
        // Get the log file. 
        logFile = config.getString("log");

        // Get the data directory.
        dataDir = config.getString("datadir");
       
        // This is optional, perhaps you want to allow fields you don't read to 
        // maintain forwad or backwards compatability.
        if (config.size() > 3)
            throw new InvalidObjectException("Superflous fields -- invalid configuration object.");

    }
      
    /**
     * Convert this object to a JSON type.
     */
    public JSONType toJSONType() 
    {
        // The JSONObject and JSONArray (not used here) are the heart of the 
        // JSON subsystem. The JSONObject class inherits from HashMap<String, Object> 
        // and the JSONArray class inherits from ArrayList<Object>. This means all of the 
        // operations of the super class are available to you.
        JSONObject obj = new JSONObject();

        // Since JSONObject is based on HashMap, we set the key to the name of the 
        // JSON key and the value to be the value we want to associate with the key.
        // If your value should be a JSONArray or JSONObject you must construct that 
        // before adding the key-value pair to the JSONObject below.
        obj.put("port", portNumber);
        obj.put("log", logFile);
        obj.put("datadir", dataDir);

        return obj;
    }

    /**
     * Get the port number.
     * @return the port number.
     */
    public int getPortNumber() 
    {
        return portNumber;
    }

    /**
     * Get the log file name.
     * @return the log file name.
     */
    public String getLogFile() 
    {
        return logFile;
    }

    /**
     * Get the data directory
     * @return the data directory name.
     */
    public String getDataDir() 
    {
        return dataDir;
    }

    /**
     * Get a string representation of the object. This is *not* JSON.
     */
    @Override 
    public String toString()
    {
        return "Port: " + portNumber + "\nlog: " + logFile + "\ndataDir: " + dataDir;
    }


}
