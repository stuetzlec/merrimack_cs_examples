package CSC2620_Unit2_22Practice;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class GUIDemo extends JFrame implements ListSelectionListener {

    // Store all the pets dynamically
    private final ArrayList<Pet> pets;
    private final JPanel cards;
    private final JPanel card1;
    private final JPanel card2;

    // Text fields for pet information input (and a submit button)
    private final JTextField speciesInput;
    private final JTextField ageInput;
    private final JTextField nameInput;
    private final JButton inputButton;

    // The other components of the GUI, the list of names and the pet info box
    private final JList petNames;
    private final JTextArea petInfo;
    private final JTextArea allInfo;

    // Buttons to swap the cards
    private final JButton swap1;
    private final JButton swap2;
    private int curCard = 0;
    private final String[] cardNames = {"Card1", "Card2"};

    public GUIDemo() {
        super("Pet Exercise");
        setLayout(new GridLayout(1, 1));

        // Setup the card layout
        cards = new JPanel(new CardLayout());
        card1 = new JPanel(new GridBagLayout());
        card2 = new JPanel(new FlowLayout());
        JPanel column1 = new JPanel(new GridBagLayout());
        JPanel column2 = new JPanel(new BorderLayout());
        JPanel column3 = new JPanel(new GridBagLayout());
        JLabel nameLabel = new JLabel("Name: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel speciesLabel = new JLabel("Species: ");
        inputButton = new JButton("Submit");
        inputButton.addActionListener(new SubmitHandler());
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        nameInput = new JTextField(20);
        ageInput = new JTextField(20);
        speciesInput = new JTextField(20);
        petInfo = new JTextArea();
        allInfo = new JTextArea();
        swap1 = new JButton("Swap to all pet view.");
        swap2 = new JButton("Swap back");

        // Set up the three columns of the card
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        card1.add(column1, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        card1.add(column2, gc);
        gc.gridx = 2;
        gc.gridy = 0;
        card1.add(column3, gc);

        // Column 1 of card 1:        
        column1.setPreferredSize(new Dimension(400, 600));
        gc.weighty = 1;
        // Create column 1 of the left column
        gc.gridx = 0;
        gc.gridy = 0;
        column1.add(nameLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        column1.add(ageLabel, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        column1.add(speciesLabel, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        column1.add(inputButton, gc);

        // Second column
        gc.gridx = 1;
        gc.gridy = 0;
        column1.add(nameInput, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        column1.add(ageInput, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        column1.add(speciesInput, gc);

        // Add an exit button (with simple anonymous action listener)
        //gc.weighty = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 0;
        gc.gridy = 4;
        column1.add(exitButton, gc);

        // Finally, initialize the data structure
        pets = new ArrayList<>();

        // Create and populate the second column
        JLabel listLabel = new JLabel("Pets:");
        listLabel.setFont(new Font("Arial", Font.BOLD, 18));
        column2.add(listLabel, BorderLayout.PAGE_START);
        petNames = new JList(pets.toArray());
        // Make sure it's single selection
        petNames.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        petNames.addListSelectionListener(this);
        // Keep the size of the list the same, regardless of other changes
        JScrollPane jsp = new JScrollPane(petNames);
        Dimension d = jsp.getPreferredSize();
        d.width = 300;
        jsp.setPreferredSize(d);
        column2.add(jsp, BorderLayout.CENTER);

        // Create the third column
        gc.gridx = 0;
        gc.gridy = 0;
        petInfo.setEditable(false);
        petInfo.setPreferredSize(new Dimension(200, 500));
        column3.add(new JScrollPane(petInfo), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        SwapHandler s = new SwapHandler();
        swap1.addActionListener(s);
        column3.add(swap1, gc);

        // Add the three columns to card1
        card1.add(column1);
        card1.add(column2);
        card1.add(column3);

        // Set up card 2
        allInfo.setPreferredSize(new Dimension(400, 500));
        card2.add(new JScrollPane(allInfo), BorderLayout.CENTER);

        swap2.addActionListener(s);
        card2.add(swap2, BorderLayout.EAST);

        cards.add(card1, cardNames[0]);
        cards.add(card2, cardNames[1]);
        this.add(cards);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // FIrst, clear the text area
        petInfo.setText("");
        // When the user clicks a name, show that data in the right column
        List<String> names = petNames.getSelectedValuesList();
        for (String n : names) {
            // Simple linear search for pet object
            Pet curPet = getPetFromName(n);

            // If we found the pet (which we always should), display its info
            if (curPet != null) {
                petInfo.append(curPet.toString());
            }
        }
    }

    /**
     * This method is a linear search through the pets in the Pets array list
     *
     * @param name The name of the pet to search for and return the record of
     * @return A reference to the pet matching the input name
     */
    private Pet getPetFromName(String name) {
        for (Pet p : pets) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * This class is the action handler for the Submit button. A Pet object is
     * generated and added to the list of pets, and the pet input text fields
     * are cleared
     */
    private class SubmitHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String n = null, s = null;
            int a;
            // Try to get the input, the age might cause an issue if blank
            try {
                n = nameInput.getText();
                s = speciesInput.getText();
                a = Integer.parseInt(ageInput.getText());
            } catch (NumberFormatException exc) {
                a = 0;
            }
            // First, create a new pet
            Pet newPet = new Pet(n, a, s);
            // Clear the text from the three inputs
            nameInput.setText("");
            ageInput.setText("");
            speciesInput.setText("");

            // Add the new pet to the list of pets
            pets.add(newPet);
            // Then update the list of pet names since there's a new one
            petNames.setListData(getArrayOfNames());

            // Finally, populate the allInfo box with the info of the pets
            allInfo.append(newPet.toString());

            validate();
        }
    }

    /**
     * This method generates a list of Strings that stores the pets' names
     *
     * @return An array of all of the pets' names
     */
    private String[] getArrayOfNames() {
        String[] arrayOfNames = new String[pets.size()];
        for (int i = 0; i < pets.size(); i++) {
            arrayOfNames[i] = pets.get(i).getName();
        }
        return arrayOfNames;
    }

    /**
     * Inner class to handle the card swapping
     */
    private class SwapHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) cards.getLayout();
            curCard++;
            curCard %= 2;
            cl.show(cards, cardNames[curCard]);
        }

    }
}
