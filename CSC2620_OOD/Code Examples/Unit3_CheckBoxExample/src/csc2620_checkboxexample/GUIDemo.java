package csc2620_checkboxexample;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class GUIDemo extends JFrame {
    private JTextField textField1;
    private JCheckBox boldJCheckBox;
    private JCheckBox italicJCheckBox;

    // Constructor
    public GUIDemo() {
        super("JCheckBox Demo"); // The name of the window
        setLayout(new FlowLayout()); // A type of layout

        // Add a text field...the two parameters to this method are
        // the 'default text', and the width of the text input
        textField1 = new JTextField("Watch the Font Change", 20);
        // Set the default font
        textField1.setFont(new Font("Serif", Font.PLAIN, 14));
        this.add(textField1);

        // Add the check boxes (with labels)
        boldJCheckBox = new JCheckBox("Bold");
        italicJCheckBox = new JCheckBox("Italic");
        this.add(boldJCheckBox);
        this.add(italicJCheckBox);

        // We have to add event handlers
        CheckBoxHandler handler = new CheckBoxHandler();
        boldJCheckBox.addItemListener(handler);
        italicJCheckBox.addItemListener(handler);
    } // end of GUIDemo

    // The private class to handle events (not an ActionListener)
    private class CheckBoxHandler implements ItemListener {
        // MUST implement the method itemStateChanged:
        public void itemStateChanged(ItemEvent event) {
            // Create a new Font object
            Font font = null;

            // Set the font object accordingly
            if (boldJCheckBox.isSelected() && italicJCheckBox.isSelected())
                font = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
            else if (boldJCheckBox.isSelected())
                font = new Font("Serif", Font.BOLD, 14);
            else if (italicJCheckBox.isSelected())
                font = new Font("Serif", Font.ITALIC, 14);
            else
                font = new Font("Serif", Font.PLAIN, 14);

            // Set the font
            textField1.setFont(font);
        } // end of itemStateChanged

    } // end of class CheckBoxHandler
} // end of class GUIDemo
