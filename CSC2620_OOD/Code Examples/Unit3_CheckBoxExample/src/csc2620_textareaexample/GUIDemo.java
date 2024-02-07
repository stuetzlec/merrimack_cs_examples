package csc2620_textareaexample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUIDemo extends JFrame {
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton copyJButton;
    
    // Constructor
    public GUIDemo() {
        super("JTextArea Demo"); // The name of the window
        // Create a "box" with FlowLayout
        Box box = Box.createHorizontalBox();  
        String demo = "This is a demo string that \n" + 
                " will be copied from one text area to \n" + 
                " the other. \n";
        
        // Create text area w/ # of visible of rows and columns
        textArea1 = new JTextArea( demo, 10, 15 );
        // Add the text area in a scroll pane
        box.add( new JScrollPane( textArea1 ) ); 
        
        // Create the copy button
        copyJButton = new JButton( "Copy >>>" );
        box.add( copyJButton );
        // Inner method to implement funtionality for copy button
        //   This is an "anonymous class" - it doesn't have a name
        copyJButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea2.setText( textArea1.getSelectedText() );
            }
        });
        
        // Set up textArea2
        textArea2 = new JTextArea( "", 10, 15 );
        // Can't edit the second text area
        textArea2.setEditable( false ); 
        box.add( new JScrollPane( textArea2 ) );
        
        // Add the box to the JFrame
        this.add( box );
    } // end of GUIDemo
}

