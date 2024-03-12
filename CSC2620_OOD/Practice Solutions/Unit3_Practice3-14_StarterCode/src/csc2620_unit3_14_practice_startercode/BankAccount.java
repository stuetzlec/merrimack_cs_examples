package csc2620_unit3_14_practice_startercode;

/**
 * A class to represent a single bank account.
 *
 * @author cs302
 */
public class BankAccount {

    private double balance;
    private int accountNumber;

    /**
     * Constructor
     *
     * @param initialBalance the initial balance of the BankAccount
     * @param _accountNumber The account number to associate with this
     * BankAccount. Must be a 5 digit integer.
     */
    public BankAccount(double initialBalance, int _accountNumber) 
            throws IllegalArgumentException {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("initialBalance can't be negative. It is: " + initialBalance);
        }

        balance = initialBalance;
        accountNumber = _accountNumber;

    }

    /**
     * Deposits money into the BankAccount
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("amount = " + amount 
                    + ", but it should be positive");
        }
        balance += amount;
    }

    /**
     * Withdraws money from the BankAccount
     *
     * @param amount the amount to withdraw
     * @throws csc2620_unit3_14_practice_startercode.InsufficientFundsException
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException(amount + 
                    " is too much money to withdraw from account " 
                    + accountNumber + " which has " + balance);
        }
        balance -= amount;
    }

    /**
     * Gets the current balance of the BankAccount
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the account number of the BankAccount
     *
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns a string representation this BankAccount in the following format,
     * e.g. 12345 $100.52 where 12345 is the account number, and 100.52 is the
     * balance.
     * @return The account number plus the amount in the account
     */
    @Override
    public String toString() {
        return "" + accountNumber + " $" + balance;
    }
}
