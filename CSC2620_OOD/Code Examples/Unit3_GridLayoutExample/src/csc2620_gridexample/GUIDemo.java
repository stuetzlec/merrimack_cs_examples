package csc2620_gridexample;

import java.awt.*;
import javax.swing.*;

public class GUIDemo extends JFrame {
    // 5 Labels
    private final JLabel[] labels = {new JLabel("This is Label1"),
       new JLabel("This is Label2"), new JLabel("This is Label3"),
       new JLabel("This is Label4"), new JLabel("This is Label5")};

    // Constructor
    public GUIDemo() {
        super("GridLayout Demo"); // The name of the window
        // Set the current layout of the JFrame
        // Create a JPanel with a 2x2 layout
        JPanel tinyPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        setLayout(new GridLayout(3, 2, 5, 5) ); // Sets the layout
        // Add four labels to the panel;
        tinyPanel.add(new JLabel("inside the panel! 0,0"));
        tinyPanel.add(new JLabel("inside the panel! 0,1"));
        tinyPanel.add(new JLabel("inside the panel! 1,0"));
        tinyPanel.add(new JLabel("inside the panel! 1,1"));
        // Set the background color of the panel to be red
        tinyPanel.setBackground(Color.red);
        // Add the panel to the JFrame
        this.add(tinyPanel);
        // Now add the labels to the rest of the JFrame sections
        for (int i = 0; i < labels.length; i++) {
            add(labels[i]);
        }
    } // end of GUIDemo
} // end of GUIDemo

