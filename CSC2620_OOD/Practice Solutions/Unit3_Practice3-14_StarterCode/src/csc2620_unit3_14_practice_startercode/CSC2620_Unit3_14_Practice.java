package csc2620_unit3_14_practice_startercode;

import javax.swing.JFrame;

/**
 *
 * @author stuetzlec
 */
public class CSC2620_Unit3_14_Practice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUIDemo guiDemo = new GUIDemo();
        // What to do when the window closes:
        guiDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Size of the window, in pixels
        guiDemo.setSize(800, 600);
        // Make the window "visible"
        guiDemo.setVisible(true);
    }
    
}
