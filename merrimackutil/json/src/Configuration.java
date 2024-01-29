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
 * Represents a simple server configuration.
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

        if (!(obj instanceof JSONObject))
            throw new InvalidObjectException("JSONObject expected.");
        
        config = (JSONObject) obj;

        // Get the port.
        if (config.containsKey("port"))
            portNumber = config.getInt("port");
        else 
            throw new InvalidObjectException("Missing port field -- invalid configuration object.");
        
        // Get the log file. 
        if (config.containsKey("log"))
            logFile = config.getString("log");
        else 
            throw new InvalidObjectException("Missing log field -- invalid configuration object.");
        

        // Get the data directory.
        if (config.containsKey("datadir"))
            dataDir = config.getString("datadir");
        else 
            throw new InvalidObjectException("Missing datadir field -- invalid configuration object.");


        // This is optional, perhaps you want to allow fields you don't read to 
        // maintain forwad or backwards compatability.
        if (config.size() > 3)
            throw new InvalidObjectException("Superflous fields -- invalid configuration object.");

    }
    
    /**
     * Convert this object to JSON. In this case we 
     * want it to be nicely formatted JSON.
     */
    public String serialize() 
    {
        return toJSONType().getFormattedJSON();
    }
    
    /**
     * Convert this object to a JSON type.
     */
    public JSONType toJSONType() 
    {
        JSONObject obj = new JSONObject();
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
     * 
     */
    @Override 
    public String toString()
    {
        return "Port: " + portNumber + "\nlog: " + logFile + "\ndataDir: " + dataDir;
    }


}
