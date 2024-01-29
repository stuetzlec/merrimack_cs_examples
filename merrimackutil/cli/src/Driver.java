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
import merrimackutil.cli.LongOption;
import merrimackutil.cli.OptionParser;
import merrimackutil.util.Tuple;

/**
 * The main class. 
 * @author Zach Kissel
 */
public class Driver
{
    private static String configFile = "config.json";
    private static int port = -1;

    /**
     * Prints the usage to the screen and exits. 
     */
    public static void usage()
    {
        System.out.println("usage:");
        System.out.println("  example --config <config> --port <port>");
        System.out.println("  example --port <port>");
        System.out.println("  example --help");
        System.out.println("options:");
        System.out.println("  -c, --config\t\tConfig file to use.");
        System.out.println("  -p, --port\t\tAuthor to get quote from.");
        System.out.println("  -h, --help\t\tDisplay the help.");
        System.exit(1);
    }

    
     /**
     * Processes the command line arugments.
     * @param args the command line arguments.
     */
    public static void processArgs(String[] args)
    {
        OptionParser parser;
        boolean doHelp = false;
        boolean doConfig = false;
        boolean doPort = false;

        LongOption[] opts = new LongOption[3];
        opts[0] = new LongOption("help", false, 'h');
        opts[1] = new LongOption("config", true, 'c');
        opts[2] = new LongOption("port", true, 'p');


        Tuple<Character, String> currOpt;

        parser = new OptionParser(args);
        parser.setLongOpts(opts);

        // The options are h for help, c: meaning config takes an argument, and 
        // p: meaning port takes an argument. 
        parser.setOptString("hc:p:");


        while (parser.getOptIdx() != args.length)
        {
            currOpt = parser.getLongOpt(false);

            switch (currOpt.getFirst())
            {
                case 'h':
                    doHelp = true;
                break;
                case 'c':
                    doConfig = true;
                    configFile = currOpt.getSecond();
                break;
                case 'p':
                    doPort = true;
                    port = Integer.parseInt(currOpt.getSecond());
                break;
                case '?':
                    System.out.println("Unknown option: " + currOpt.getSecond());
                    usage();
                break;
            }
        }

        // Verify that that this options are not conflicting.
        // Shouldn't have any options with help.
        if ((doConfig && doHelp) || (doPort && doHelp))
            usage();
        
        // If we have help by itself, just print the usage.
        if (doHelp)
            usage();
        
        // Specifying a port is required.
        if (!doPort)
            usage();
    }

    /**
     * This is the main method. 
     * @param args the command line arguments.
     */
    public static void main(String[] args)
    {
        if (args.length > 4)
            usage();
        processArgs(args);

        System.out.println("Config file: " + configFile + " Port: " + port);  
    }
}