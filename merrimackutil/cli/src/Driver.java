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
 * The main class demonstrating the use of the CLI options parser. Command line options 
 * are a common way of deploying command line tools (e.g., Linux, Windows cmd.exe, and Mac Terminal). 
 * merrimackutil endeavors to make this a simple process. You should plan to write two methods:
 * 
 *  1. a method that displays how to use the command (called the usage). The method usage() below 
 *     does this. 
 *  2. a method to process the command line arguments using the OptionParser of merrimackutil. In this 
 *     file that is called processArgs().
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
    // Fields that are potentially set by the processArgs method.
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
        OptionParser parser;    // Class that can parse the args array.

        // Flags that tell us what option is requested. These are sometimes 
        // made to be fields of the class if we need to reference them elsewhere.
        boolean doHelp = false;
        boolean doConfig = false;
        boolean doPort = false;

        // Build teh long options. These are all of the options your program should 
        // support. The first argument specifies the long verison (e.g., --help) 
        // the second boolean determins if the option has an argument, and the third 
        // argument is the short option (e.g., -h).
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

        // NOTE: Alternatively you could compress lines 71 and 67 by
        // using:
        // parser.setLongAndShortOpts(opts);


        // Loop over all of the options until the parser has read 
        // the last option. OptIdx is the index of in the args array
        // fed to the parser on line 73.
        while (parser.getOptIdx() != args.length)
        {
            // Get the current option from the parser. These options are 
            // read in order. The arguement is {@code true} if you only 
            // support long options (e.g., --help) and {@code false} otherwise.
            // The returned value is a Tuple the first field is the short 
            // opt character (e.g., h in -h) and the second field is the argmuments 
            // value, if the option has an arguement.
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
                case '?':       // Returned if the option is unknown.
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