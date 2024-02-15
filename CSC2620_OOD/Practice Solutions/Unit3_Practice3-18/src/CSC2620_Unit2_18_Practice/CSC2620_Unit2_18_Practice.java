package CSC2620_Unit2_18_Practice;

import javax.swing.JFrame;

public class CSC2620_Unit2_18_Practice {

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
