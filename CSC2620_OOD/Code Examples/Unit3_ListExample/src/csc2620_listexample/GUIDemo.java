package csc2620_listexample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUIDemo extends JFrame {
  private JList colorList; // Holds colors
  private JList copyList; // Copy color names into new list
  private JButton copyButton; // Button to commence copying

  // Make arrays for the color names and the colors
  private static String[] names = {"Black","Blue","Cyan","Gray"};

  public GUIDemo() {
    super("Multiple-Selection List Test");
    setLayout(new FlowLayout());

    colorList = new JList(names); // Make a new list
    colorList.setVisibleRowCount(3);
    colorList.setFixedCellWidth(100);
    // Now, we want to allow multiple selections:
    colorList.
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    this.add(new JScrollPane(colorList));

    copyButton = new JButton("Copy >>>");
    copyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        // Place the selected values in the copy list
        copyList.
            setListData(colorList.getSelectedValuesList().toArray());
      } // end of actionPerformed
    } // end of ActionListener
        ); // end of AddActionListener
    // Now, add the copy button
    this.add(copyButton);

    // Create the copy list
    copyList = new JList();
    copyList.setVisibleRowCount(3);
    copyList.setFixedCellWidth(100);
    copyList.setFixedCellHeight(15);
    copyList.
       setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    this.add(new JScrollPane(copyList)); // Add list with scroll pane
  } // end of GUIDemo
} // end of class GUIDemo