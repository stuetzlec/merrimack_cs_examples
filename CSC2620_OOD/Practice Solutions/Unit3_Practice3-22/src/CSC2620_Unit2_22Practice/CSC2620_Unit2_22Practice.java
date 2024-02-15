package CSC2620_Unit2_22Practice;

import javax.swing.JFrame;

public class CSC2620_Unit2_22Practice {

    public static void main(String[] args) {
        GUIDemo guiDemo = new GUIDemo();
        
        // What to do when the window closes:
        guiDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Size of the window, in pixels
        guiDemo.setSize(1000, 640);
        // Make the window "visible"
        guiDemo.setVisible(true);
    } // end of main
    
}
