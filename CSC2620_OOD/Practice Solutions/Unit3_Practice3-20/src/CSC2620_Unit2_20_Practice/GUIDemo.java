package CSC2620_Unit2_20_Practice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUIDemo extends JFrame implements ItemListener, ListSelectionListener {

    private final JPanel cards; // a panel that uses CardLayout
    private final JList list1;  // The two lists for the two cards
    private final JList list2;
    private final static String CARD1NAME = "Card with Selection List";
    private final static String CARD2NAME = "Card with View List";

    public GUIDemo() {
        // Put the JComboBox in a JPanel to get a nicer look.
        JPanel radioButonSelectorPanel = new JPanel(); //use FlowLayout
        // Create the radio button group for this 
        JRadioButton list1RadioButton = new JRadioButton(CARD1NAME, true);
        JRadioButton list2RadioButton = new JRadioButton(CARD2NAME, false);
        ButtonGroup group = new ButtonGroup();
        group.add(list1RadioButton);
        group.add(list2RadioButton);
        list1RadioButton.addItemListener(this);
        list2RadioButton.addItemListener(this);
        radioButonSelectorPanel.add(list1RadioButton);
        radioButonSelectorPanel.add(list2RadioButton);

        // Populate the list with strings
        String[] list1Pop = {CARD1NAME, CARD2NAME, 
            "Here are more words.", "Even a few more!"};

        // Create the "cards"
        JPanel card1 = new JPanel();
        card1.setLayout(new FlowLayout());
        list1 = new JList(list1Pop);
        list1.addListSelectionListener(this);
        card1.add(new JScrollPane(list1));

        JPanel card2 = new JPanel();
        list2 = new JList();
        card2.setLayout(new FlowLayout());
        card2.add(new JScrollPane(list2));

        // Create the panel that contains the "cards"
        cards = new JPanel(new CardLayout());
        cards.add(card1, CARD1NAME);
        cards.add(card2, CARD2NAME);
        // Add the panels to the frame
        this.add(radioButonSelectorPanel, BorderLayout.PAGE_START);
        
        // Add a quit button
        JButton quit = new JButton("Quit");
        quit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        radioButonSelectorPanel.add(quit);
        this.add(cards, BorderLayout.CENTER);
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        // First, get the source, the radio button that generated the event
        JRadioButton source = (JRadioButton) evt.getSource();
        // If the source is the one that was selected, switch the card
        // You should have this because both selected and unselecting a radio
        //   button generates an ItemStateChanged event
        if (source.isSelected()) {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, source.getText());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        list2.setListData(list1.getSelectedValuesList().toArray());
    }
}
