// Taken from: https://medium.com/@ssaurel/learn-to-make-a-mvc-application-with-swing-and-java-8-3cd24cf7cb10

package csc2620_unit6_mvcexample;

/**
 *
 * @author Chris
 */
public class CSC2620_Unit6_MVCExample {

    public static void main(String[] args) {
        
        // Assemble all the pieces of the MVC
        Model m = new Model("Chris", "Stuetzle");
        View v = new View("MVC with Saurel");
        Controller c = new Controller(m, v);
        
        c.initController();
    }
}
