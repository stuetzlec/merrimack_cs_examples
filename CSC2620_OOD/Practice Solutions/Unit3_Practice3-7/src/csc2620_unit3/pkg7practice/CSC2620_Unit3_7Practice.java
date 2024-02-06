package csc2620_unit3.pkg7practice;

import javax.swing.JFrame;

/**
 *
 * @author stuetzlec
 */
public class CSC2620_Unit3_7Practice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUIDemo guiDemo = new GUIDemo("CSC 2620 3.7 Practice");
        // What to do when the window closes:
        guiDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Size of the window, in pixels
        guiDemo.setSize(1000, 720);
        // Make the window "visible"
        guiDemo.setVisible(true);
    }
    
}
