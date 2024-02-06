package csc2620_unit2_11_practice;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUIDemo extends JFrame {

    private final JTextArea textArea1;
    private final JTextArea textArea2;
    private final JButton copyJButton;
    private final JButton switchEditable;
    private final JButton exitButton;
    private final JButton saveButton;
    private final JButton addNewTextAreaButton;
    private ArrayList<JTextArea> areas = new ArrayList();
    private final JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 5, 5));
    private JPanel extraTextAreasPanel = new JPanel(new FlowLayout());

    // Constructor
    public GUIDemo() {
        super("JTextArea Demo"); // The name of the window
        Box box = Box.createHorizontalBox();  // Create a "box" with FloyLayout
        String demo = "This is a demo string that \n"
                + " will be copied from one text area to \n"
                + " the other. \n";

        // Create the first text area
        textArea1 = new JTextArea(demo, 10, 15); // rows columns visible
        box.add(new JScrollPane(textArea1)); // Add the textarea in a scrollpane

        // Create the copy button
        copyJButton = new JButton("Copy >>>");
        buttonPanel.add(copyJButton);
        // Inner method to implement copy funtionality for copy button
        //   This is an "anonymous class" because it doesn't have a name
        copyJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea2.setText(textArea1.getSelectedText());
            }
        });

        // Add a "Switch Editable" button (3.11 #1)
        switchEditable = new JButton("<< Editable >>");
        buttonPanel.add(switchEditable);
        switchEditable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // First, switch the editable values of each
                textArea1.setEditable(!textArea1.isEditable());
                textArea2.setEditable(!textArea2.isEditable());
            }
        });
        
        // Add a "Save" button (Practice 3.11 #2)
        saveButton = new JButton("Save");
        buttonPanel.add(saveButton);
        saveButton.addActionListener( new SaveHandler() );
        
        // Add new text areas (Practice 3.11 #3)
        addNewTextAreaButton = new JButton("Add New Text Area");
        buttonPanel.add(addNewTextAreaButton);
        addNewTextAreaButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extraTextAreasPanel.add(new JTextArea(10,10));
                // Call "validate" whenever you change the components on the
                //   GUI
                validate();
            }
        });

        // An exit button is conevenient to have:
        exitButton = new JButton("Quit");
        buttonPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add the buttons to the GUI
        box.add(buttonPanel);

        // Set up textArea2
        textArea2 = new JTextArea("", 10, 15);
        textArea2.setEditable(false); // Cant's edit the second text area
        box.add(new JScrollPane(textArea2));
        
        // Add the area for extra text areas
        box.add(extraTextAreasPanel);

        // Add the box to the JFrame
        this.add(box);
    } // end of GUIDemo
    
    private class SaveHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea editableTextArea = getEditableTextArea();
            // Get the filename to save to (Practice 3.11 #2a)
            File file = null;
            try {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(GUIDemo.this);
                file = fc.getSelectedFile();
                // Write the text to the file (Practice 3.11 #2b)
                PrintWriter writer = new PrintWriter(file, "UTF-8");
                writer.write(editableTextArea.getText());
                // Cannot forget to close
                writer.close();
                
                // Clear the text in the text area (Practice 3.11 #2c)
                editableTextArea.setText("");
            } 
            catch( NullPointerException ex ){
                System.err.println("No file selected.");
            }
            // Catch two different kinds of exceptions
            catch (FileNotFoundException | UnsupportedEncodingException ex) {
                System.err.println(ex.getMessage() + 
                        "\nFile not written: " + file.getName());
            }
        }
    }
    
    /**
     * A helper method to determine which of the two text areas is editable
     *   and return a reference to it.
     * @return A reference to whichever of the two text areas is editable.
     */
    private JTextArea getEditableTextArea() {
        if( textArea1.isEditable() )
            return textArea1;
        return textArea2;
    }
}
