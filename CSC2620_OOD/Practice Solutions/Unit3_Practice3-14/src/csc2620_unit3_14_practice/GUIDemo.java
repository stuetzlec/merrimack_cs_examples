package csc2620_unit3_14_practice;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This provides a good example of using border layout as well as incorporating
 * existing classes into a GUI-driven application.
 *
 * @author stuetzlec
 */
class GUIDemo extends JFrame {

    private final Bank bank;
    private final JButton addAccountButton;
    private final ArrayList<JRadioButton> accountButtons = new ArrayList();
    private final ButtonGroup allAccounts = new ButtonGroup();
    private final JTextField amt = new JTextField();
    private final JPanel accountsPanel;
    private final JPanel optionPanel;
    private final JTextArea accountInfo;
    private final JButton goButton = new JButton("Go!");
    private final JRadioButton withdrawButton;
    private final JRadioButton depositButton;
    private final JButton printAccountsButton = new JButton("Print Accounts");

    GUIDemo() {
        super("Bank Example (Practice 3.14)");
        this.setLayout(new BorderLayout());
        bank = new Bank(100);

        // Add an account button (Practice 3.14 #3)
        addAccountButton = new JButton("Add Account");
        addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAccount();
            }
        });
        this.add(addAccountButton, BorderLayout.WEST);

        // Create a panel for the accountButtons radio buttons
        accountsPanel = new JPanel();
        accountsPanel.setLayout(new GridLayout(100, 1, 5, 5));
        accountsPanel.add(new JLabel("ACCOUNTS"));
        JScrollPane newScroll = new JScrollPane(accountsPanel);
        newScroll.setPreferredSize(new Dimension(200, 1000));
        this.add(newScroll, BorderLayout.EAST);

        // Add the withdraw/deposit radio buttons
        //   Practice 3.14 #6
        optionPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        withdrawButton = new JRadioButton("Widthdraw");
        depositButton = new JRadioButton("Deposit");
        ButtonGroup wd = new ButtonGroup();
        wd.add(withdrawButton);
        wd.add(depositButton);
        optionPanel.add(withdrawButton);
        optionPanel.add(depositButton);
        // Add the amount to withdraw/deposit
        //  Practice 3.14 #5
        optionPanel.add(new JLabel("Amount to Withdraw/Deposit:"));
        optionPanel.add(amt);
        optionPanel.add(goButton);
        goButton.addActionListener(new AccountHandler());
        this.add(optionPanel, BorderLayout.NORTH);

        // Add the text area with all the accountButtons
        accountInfo = new JTextArea();
        accountInfo.setPreferredSize(new Dimension(400, 400));
        accountInfo.setEditable(false);
        this.add(accountInfo, BorderLayout.CENTER);

        addQuitButton();
    }

    /**
     * A class to handle the deposits and withdrawals Practice 3.14 #6
     */
    private class AccountHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the account we're using
            BankAccount acct = null;
            for (JRadioButton account : accountButtons) {
                if (account.isSelected()) {
                    acct = bank.find(Integer.parseInt(account.getText()));
                }
            }
            try {
                if (withdrawButton.isSelected()) {
                    acct.withdraw(Double.parseDouble(amt.getText()));
                } else {
                    acct.deposit(Double.parseDouble(amt.getText()));
                }
            } // If there's not enough money in the account, report that
            catch (InsufficientFundsException exc) {
                // Error message into the account text area for convenience
                accountInfo.setText(exc.getMessage()
                        + "\n\n\n" + accountInfo.getText());
            } // Not selecting an account will result in a NullPointerException
            catch (NullPointerException npe) {
                accountInfo.setText("ERROR:No Account Selected\n\n\n"
                        + accountInfo.getText());
            }
            updateGUI();
        }
    }

    /**
     * This method adds an account to the bank
     */
    private void addAccount() {
        double initialBalance = Double.parseDouble(
                JOptionPane.showInputDialog("What is the initial balance?"));
        String acctNum = Integer.toString(bank.getNumAccounts());
        bank.add(new BankAccount(initialBalance, bank.getNumAccounts()));

        // Create a new button and add it to the button group and GUI
        //   Practice 3.14 #4
        JRadioButton newButton = new JRadioButton(acctNum);
        allAccounts.add(newButton);
        accountButtons.add(newButton);
        accountsPanel.add(newButton);

        updateGUI();
    }

    /**
     * A helper method to create a Quit button
     */
    private void addQuitButton() {
        // Add a quit button
        JButton quit = new JButton("Quit");
        quit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        this.add(quit, BorderLayout.SOUTH);
    }

    /**
     * A helper method to reset the text in the 
     *   text area, and validate
     */
    private void updateGUI() {
        // Repopulate the text area
        accountInfo.setText(bank.toString());

        validate();
    }

}
