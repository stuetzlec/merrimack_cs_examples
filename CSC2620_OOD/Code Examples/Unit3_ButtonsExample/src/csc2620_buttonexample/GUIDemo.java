package csc2620_buttonexample;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUIDemo extends JFrame {
    private JTextField textField1;
    private JButton button1;

    // Constructor
    public GUIDemo() {
        super("JButton Demo"); // The name of the window
        setLayout(new FlowLayout()); // A type of layout

        // Text field
        textField1 = new JTextField("Enter name here", 20);
        this.add(textField1);
        // We have to add event handlers
        TextFieldHandler handler = new TextFieldHandler();
        textField1.addActionListener(handler);
        textField1.addFocusListener(handler);

        // Add a button
        button1 = new JButton("Click here!");
        this.add(button1);
        // Add the event handler to the button
        button1.addActionListener(handler);
    } // end of GUIDemo

    // The private class to handle events
    private class TextFieldHandler implements 
               ActionListener, FocusListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // If the button is pressed, get text in textField1
            String string = String.format("textField1: %s",
                   textField1.getText());
            // Show a message to the users with the text input
            JOptionPane.showMessageDialog(GUIDemo.this, string);
        } // end of actionPerformed

        // If the text field gains focus, remove the text
        @Override
        public void focusGained(FocusEvent event) {
            ((JTextField) event.getSource()).setText("");
        }

        // If the textfield loses focus without text, reset
        @Override
        public void focusLost(FocusEvent event) {
          if(((JTextField)event.getSource()).getText().equals("")) 
            ((JTextField) event.getSource()).
              setText("Enter name here");
        }
    } // end of class TextFieldHandler
} // end of class GUIDemo
