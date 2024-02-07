package csc2620_radiobuttonexample;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class GUIDemo extends JFrame {
    private JTextField textField1;
    private Font plain, bold, italic, boldItalic;
    private JRadioButton plainButton, boldButton, italicButton,
                         boldItalicButton;
    private ButtonGroup radioGroup;
  
    // Constructor
    public GUIDemo() {
      super("JRadioButton Demo"); // The name of the window
      setLayout(new FlowLayout()); // A type of layout
  
      // Add a text field...the two parameters to this method are
      // the 'default text', and the width of the text input
      textField1 = new JTextField("Watch the Font Change", 20);
      this.add(textField1);
      
      // Create the radio buttons, making one `true' so selected
      plainButton = new JRadioButton( "Plain", true ); 
      boldButton = new JRadioButton( "Bold", false );
      italicButton = new JRadioButton( "Italic", false );
      boldItalicButton = new JRadioButton( "Bold Italic", false );
      // Add them to the GUI
      this.add( plainButton );
      this.add( boldButton );
      this.add( italicButton );
      this.add( boldItalicButton );
      
      // Create a relationship between the buttons
      radioGroup = new ButtonGroup();
      radioGroup.add( plainButton );
      radioGroup.add( boldButton );
      radioGroup.add( italicButton );
      radioGroup.add( boldItalicButton );
      
      // Create a series of font objects to use for the group
      plain = new Font( "Serif", Font.PLAIN, 14 );
      bold = new Font( "Serif", Font.BOLD, 14 );
      italic = new Font( "Serif", Font.ITALIC, 14 );
      boldItalic = new Font( "Serif", Font.BOLD + Font.ITALIC, 14 );
      // Set the original font
      textField1.setFont( plain );
      
      // Finally, register the events
      plainButton.addItemListener(new RadioButtonHandler(plain));
      boldButton.addItemListener(new RadioButtonHandler(bold));
      italicButton.addItemListener(new RadioButtonHandler(italic));
      boldItalicButton.addItemListener( 
          new RadioButtonHandler(boldItalic));    
    } // end of GUIDemo
    
    // Private class to handle events (not ActionListener this time)
    private class RadioButtonHandler implements ItemListener {
      // Need a private field for the font associated with the listener
      private Font font;
  
      // Implement a constructor to set the font
      public RadioButtonHandler(Font f) {
        font = f;
      } // constructor for RadioButtonHandler
  
      // MUST implement the method itemStateChanged:
      public void itemStateChanged(ItemEvent event) {
        textField1.setFont(font);
      } // end of itemStateChanged
  
    } // end of class CheckBoxHandler
  } // end of GUIDemo