package csc2620_comboboxexample;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class GUIDemo extends JFrame {
  private JComboBox stringBox; // Contains strings to show
  private JLabel label; // Label to display selected string

  private static final String[] labels =
     { "Hello!", "Hey?", "What's up?", "Hi!" };

  public GUIDemo() {
    super("Combo Box Testing");
    setLayout(new FlowLayout());

    // Create a new combo box, and provide an array of labels
    stringBox = new JComboBox(labels);
    stringBox.setMaximumRowCount(3);

    stringBox.addItemListener(new ItemListener() // The anon class
        {
          // handle the JComboBox event
          public void itemStateChanged(ItemEvent event) {
            // determine if this was selected
            if (event.getStateChange() == ItemEvent.SELECTED)
              label.setText(labels[stringBox.getSelectedIndex()]);
          } // end of itemStateChanged
        } // end of ItemListener
        );
    
    // Now, add the box
    this.add( stringBox );
    label = new JLabel( labels[ 0 ] );
    this.add( label );
  } // end of GUIDemo
} // end of class GUIDemo