package csc2620;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class GUIDemo extends JFrame implements ItemListener {

    private JPanel cards; //a panel that uses CardLayout
    private static String BUTTONPANEL = "Card with JButtons";
    private static String TEXTPANEL = "Card with JTextField";

    public GUIDemo() {
        // Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPanel = new JPanel(); //use FlowLayout
        String comboBoxItems[] = {BUTTONPANEL, TEXTPANEL};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPanel.add(cb);

        // Create the "cards"
        JPanel card1 = new JPanel();
        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));
        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));
 
        // Create the panel that contains the "cards"
        cards = new JPanel(new CardLayout());
        cards.add(card1, BUTTONPANEL);
        cards.add(card2, TEXTPANEL);
        
        // Add the panels to the frame
        this.add(comboBoxPanel, BorderLayout.PAGE_START);
        this.add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }
}