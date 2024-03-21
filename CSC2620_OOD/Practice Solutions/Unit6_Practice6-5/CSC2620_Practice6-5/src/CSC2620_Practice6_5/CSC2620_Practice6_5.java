package CSC2620_Practice6_5;

import java.io.FileNotFoundException;
import javax.swing.JFrame;

/**
 *
 * @author Chris
 */
public class CSC2620_Practice6_5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Initialize the model and the viewcontrollers
        Model model = new Model();
        ViewController view = new ViewController( model );
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(1400,1000);
        view.setVisible(true);
        
        
    }
    
}
