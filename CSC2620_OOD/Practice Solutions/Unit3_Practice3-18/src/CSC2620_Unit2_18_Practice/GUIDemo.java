package CSC2620_Unit2_18_Practice;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUIDemo extends JFrame {

    private JList colorList; // Holds colors
    private JList copyList; // Copy color names into new list
    private JButton copyButton; // Button to commence copying
    // Make arrays for the color names and the colors
    private static final String[] names = {"Black", "Blue", "Cyan", "Gray"};
    private static final Color[] colors = 
        {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY};

    public GUIDemo() {
        super("Multiple-Selection List Test");
        setLayout(new FlowLayout());
        colorList = new JList(names); // Make a new list
        colorList.setVisibleRowCount(3);
        colorList.setFixedCellWidth(100);
        // Now, we want to allow multiple selections:
        colorList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.add(new JScrollPane(colorList));
        copyButton = new JButton("Copy >>>");
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Place the selected values in the copy list
                try {
                    copyList.setListData(colorList.getSelectedValuesList().toArray());
                } catch (NullPointerException e) {
                    copyList.setSelectedIndex(0);
                    changeColor();
                }
            } // end of actionPerformed
        } // end of ActionListener
        ); // end of AddActionListener
        // Now, add the copy button
        this.add(copyButton);
        // Create the copy list
        copyList = new JList();
        copyList.setVisibleRowCount(3);
        copyList.setFixedCellWidth(150);
        copyList.setFixedCellHeight(15);
        copyList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.add(new JScrollPane(copyList)); // Add the list with scroll pane

        copyList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // First, get the index of the selected
                changeColor();
            }
        });
        
        // Add a quit button
        JButton quit = new JButton("Quit");
        quit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(quit);
    } // end of GUIDemo

    private void changeColor() {
        String colorSelected = (String) copyList.getSelectedValue();
        getContentPane().setBackground(
                colors[Arrays.binarySearch(names, colorSelected)]
        );
    }
}
